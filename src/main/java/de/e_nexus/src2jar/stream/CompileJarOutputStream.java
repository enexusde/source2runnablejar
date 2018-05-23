/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.stream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import de.e_nexus.src2jar.fm.DefaultSystemFileManager;
import de.e_nexus.src2jar.fm.JavaSource;
import de.e_nexus.src2jar.jsn.ClassUtils;

/**
 * {@link CompileJarOutputStream} a output stream for a {@link JarFile} who can
 * be called in the commandline.
 *
 * @author Peter Rader
 */
public class CompileJarOutputStream extends JarOutputStream {

	/**
	 * The cache of outputstreams to write.
	 */
	public final Map<ZipEntry, ByteArrayOutputStream> cache = new LinkedHashMap<>();

	/**
	 * The filesytem paths inside the jar-file. The key is the intended ZipFile
	 * header. The value is the simplified name of the Class.
	 */
	public final Map<ZipEntry, String> names = new LinkedHashMap<>();
	/**
	 * The jars that are required to be available for the compilation of the Jar.
	 * <p>
	 * You must not place system-jars here that are part of the bootstrap. You must
	 * place classes here that are not part of the JVM but required for compilation.
	 */
	public final Set<File> endorsedJars = new LinkedHashSet<>();

	/**
	 * The current ZipEntry inside the archive.
	 */
	public ZipEntry currentZipFile = null;

	public CompileJarOutputStream(OutputStream out) throws IOException {
		super(out);
	}

	@Override
	public void write(byte[] b) throws IOException {
		if (currentZipFile == null) {
			super.write(b);
		} else {
			cache.get(currentZipFile).write(b);
		}
	}

	@Override
	public synchronized void write(byte[] b, int off, int len) throws IOException {
		if (currentZipFile == null) {
			super.write(b, off, len);
		} else {
			cache.get(currentZipFile).write(b, off, len);
		}
	}

	@Override
	public void write(int b) throws IOException {
		if (currentZipFile == null) {
			super.write(b);
		} else {
			cache.get(currentZipFile).write(b);
		}
	}

	@Override
	public void closeEntry() throws IOException {
		currentZipFile = null;
	}

	@Override
	public void putNextEntry(ZipEntry ze) throws IOException {
		if (currentZipFile != null) {
			throw new IOException(currentZipFile + " not closed yet, call closeEntry() first.");
		}
		cache.put(ze, new ByteArrayOutputStream());
		names.put(ze, ClassUtils.toSimplename(ze.getName()));
		currentZipFile = ze;
	}

	/**
	 * Start the compilation process and close the underlying stream.
	 */
	@Override
	public void close() throws IOException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		DefaultSystemFileManager fileManager = new DefaultSystemFileManager(
				compiler.getStandardFileManager(null, null, null), endorsedJars);
		Map<ZipEntry, JavaSource> sources = new LinkedHashMap<>();
		for (ZipEntry zipEntry : cache.keySet()) {
			ByteArrayOutputStream byteArrayOutputStream = cache.get(zipEntry);
			sources.put(zipEntry, new JavaSource(byteArrayOutputStream.toString(), names.get(zipEntry)));
		}
		compiler.getTask(null, fileManager, null, Arrays.asList("-g:lines"), null, sources.values()).call();
		fileManager.close();
		for (ZipEntry zipEntry : cache.keySet()) {
			JavaSource javaSource = sources.get(zipEntry);
			byte[] compiled = fileManager.getCompiled(javaSource);
			ZipEntry convertedEntry = ClassUtils.convertClass(zipEntry);
			internalPlaceEntry(compiled, convertedEntry);
		}
		fileManager.clearCache();
		super.close();
	}

	/**
	 * For internal use only. Place a binary file without compilation inside of the
	 * JarFile. Take care that any current entry is closed before using this method.
	 * 
	 * @param data
	 *            The content of the file to place inside the jar.
	 * @param entry
	 *            The ZipEntry in the archive to place.
	 * @throws IOException
	 */
	protected void internalPlaceEntry(byte[] data, ZipEntry entry) throws IOException {
		super.putNextEntry(entry);
		super.write(data);
		super.closeEntry();
	}

	/**
	 * Add a Jar to the list of librarys that are required for compilation.
	 * 
	 * @param jar
	 *            The jar-file to use.
	 */
	public void registerEndorsedJar(File jar) {
		if (!jar.exists()) {
			throw new RuntimeException(
					new FileNotFoundException("File " + jar.getAbsolutePath() + " does not exists!"));
		}
		endorsedJars.add(jar);
	}

}
