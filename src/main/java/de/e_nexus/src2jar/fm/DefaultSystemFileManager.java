/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.fm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;

import de.e_nexus.src2jar.stream.CompileJarOutputStream;

/**
 * The {@link DefaultSystemFileManager} is a {@link JavaFileManager} having
 * System-Level classloader.
 * 
 * <p>
 * Note that any class loaded by further {@link ClassLoader Classloaders} are
 * not respected.
 * 
 * <p>
 * This implementation is using a genuine {@link JavaFileManager} to cache
 * directory-listing requests.
 * 
 * @author Peter Rader
 *
 */
public class DefaultSystemFileManager implements DiscriminatorJavaFileManagerAdapter {

	/**
	 * The filemanager to use.
	 * <p>
	 * Never <code>null</code>
	 */
	private final StandardJavaFileManager fileManager;

	/**
	 * The cache for list-request to the genuine {@link JavaFileManager}.
	 */
	private static Map<String, ListCache> listingCache = new LinkedHashMap<>();

	/**
	 * Cache entry to cache the list-requests of the genuine
	 * {@link JavaFileManager}.
	 * 
	 * @author Peter Rader
	 *
	 */
	public class ListCache {

		/**
		 * The location, only {@link StandardLocation#CLASS_PATH} and
		 * {@link StandardLocation#PLATFORM_CLASS_PATH} is used.
		 */
		private final Location location;
		/**
		 * The package to list for.
		 */
		private final String packageName;
		/**
		 * The kinds to use.
		 */
		private final Set<Kind> kinds;
		private Iterable<JavaFileObject> cache;
		private final String key;

		public ListCache(Location location, String packageName, Set<Kind> kinds) {
			this.location = location;
			this.packageName = packageName;
			this.kinds = kinds;
			this.key = location.getName() + "-" + packageName + "-" + kinds;
		}

		public Iterable<JavaFileObject> getList() {
			if (cache == null) {
				synchronized (this) {
					if (cache == null) {
						try {
							cache = fileManager.list(location, packageName, kinds, false);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return cache;
		}
	}

	/**
	 * Creates the {@link DefaultSystemFileManager} using the filemanager.
	 * 
	 * @param fileManager
	 *            The filemanager to use.
	 */
	public DefaultSystemFileManager(StandardJavaFileManager fileManager) {
		this.fileManager = fileManager;
	}

	/**
	 * Always use the {@link ClassLoader#getSystemClassLoader() system's
	 * classloader}.
	 */
	public final ClassLoader getClassLoader(Location location) {
		return ClassLoader.getSystemClassLoader();
	}

	/**
	 * List the classes in a package.
	 * <p>
	 * Notice that the results are cached a second time if there are results to
	 * cache. If no results are cached because there are no results, dont cache the
	 * empty list in order to ignore run-time compilations.
	 */
	public Iterable<JavaFileObject> list(Location location, String packageName, Set<Kind> kinds, boolean recurse)
			throws IOException {
		if (recurse) {
			throw new IllegalStateException("Not implemented!");
		}
		ListCache cache = new ListCache(location, packageName, kinds);
		if (listingCache.containsKey(cache.key)) {
			System.out.println("FOUND");
			return listingCache.get(cache.key).getList();
		}
		Iterable<JavaFileObject> list = cache.getList();
		if (list.iterator().hasNext()) {
			listingCache.put(cache.key, cache);
		}
		return list;
	}

	public String inferBinaryName(Location location, JavaFileObject file) {
		return fileManager.inferBinaryName(location, file);
	}

	public Map<JavaSource, JavaTarget> sourceToTarget = new LinkedHashMap<>();
	/**
	 * Marks the point where compiled classes are available.
	 * <p>
	 * If the compilation has not yet ended, {@link #closed} is <code>false</code> ,
	 * but if the compilation has ended, {@link #closed} is <code>true</code>.
	 * <p>
	 * This is independend of the success of fail of the compilation.
	 */
	private boolean closed = false;

	/**
	 * Returns the Source to compile.
	 * <p>
	 * The the result-value is exactly the same what you can use to get the compiled
	 * class using {@link #getCompiled(JavaSource)}.
	 */
	public JavaFileObject getJavaFileForOutput(Location location, String className, Kind kind, FileObject sibling)
			throws IOException {
		JavaTarget javaTarget = new JavaTarget();
		sourceToTarget.put((JavaSource) sibling, javaTarget);
		return javaTarget;
	}

	/**
	 * NO-OP. Because some classes might cross-refer, flush is not provided by the
	 * {@link DefaultSystemFileManager}.
	 */
	public void flush() throws IOException {
	}

	/**
	 * Marks the {@link DefaultSystemFileManager} as closed.
	 * <p>
	 * This method is called from the Compiler only in order to mark a compilation
	 * as successfull.
	 * <p>
	 * Only if the compiler or the {@link CompileJarOutputStream} called the
	 * close-method the compiled java classes are available by
	 * {@link #getCompiled(JavaSource)}.
	 * <p>
	 * If no class is compiled the compiler must call this method anyway.
	 */
	public void close() throws IOException {
		closed = true;
	}

	/**
	 * Returns the bytecode of the compiled java source.
	 * 
	 * @param source
	 *            The java sourcecode object.
	 * @return The bytes of the bytecode or <code>null</code> if the compiler has
	 *         not compiled the classes yet.
	 */
	public byte[] getCompiled(final JavaSource source) {
		if (!closed) {
			return null;
		}
		try {
			return sourceToTarget.get(source).openOutputStream().toByteArray();
		} catch (IOException e) {
			throw new RuntimeException("Unexpected to " + ByteArrayOutputStream.class.getCanonicalName()
					+ " to throw a " + IOException.class.getSimpleName()
					+ "! This is a bug, please report it to the bug-tracking system.", e);
		}
	}

	/**
	 * Returns <code>false</code> always, a in-memory class has no location.
	 */
	public boolean hasLocation(Location location) {
		return false;
	}
}
