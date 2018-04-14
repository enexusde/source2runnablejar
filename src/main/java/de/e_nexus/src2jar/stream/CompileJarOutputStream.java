/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import de.e_nexus.src2jar.fm.DefaultSystemFileManager;
import de.e_nexus.src2jar.fm.JavaSource;
import de.e_nexus.src2jar.jsn.ClassUtils;

public class CompileJarOutputStream extends JarOutputStream {

	public final Map<ZipEntry, ByteArrayOutputStream> cache = new LinkedHashMap<>();
	public final Map<ZipEntry, String> names = new LinkedHashMap<>();

	public ZipEntry currentFile = null;

	public CompileJarOutputStream(OutputStream out) throws IOException {
		super(out);
	}

	@Override
	public void write(byte[] b) throws IOException {
		if (currentFile == null) {
			super.write(b);
		} else {
			cache.get(currentFile).write(b);
		}
	}

	@Override
	public synchronized void write(byte[] b, int off, int len) throws IOException {
		if (currentFile == null) {
			super.write(b, off, len);
		} else {
			cache.get(currentFile).write(b, off, len);
		}
	}

	@Override
	public void write(int b) throws IOException {
		if (currentFile == null) {
			super.write(b);
		} else {
			cache.get(currentFile).write(b);
		}
	}

	@Override
	public void closeEntry() throws IOException {
		currentFile = null;
	}

	@Override
	public void putNextEntry(ZipEntry ze) throws IOException {
		if (currentFile != null) {
			throw new IOException(currentFile + " not closed yet, call closeEntry() first.");
		}
		cache.put(ze, new ByteArrayOutputStream());
		names.put(ze, ClassUtils.toSimplename(ze.getName()));
		currentFile = ze;
	}

	/**
	 * Start the compilation process and close the underlying stream.
	 */
	@Override
	public void close() throws IOException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		DefaultSystemFileManager fileManager = new DefaultSystemFileManager(
				compiler.getStandardFileManager(null, null, null));
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
			super.putNextEntry(ClassUtils.convertClass(zipEntry));
			super.write(compiled);
			super.closeEntry();
		}
		super.close();
	}
}
