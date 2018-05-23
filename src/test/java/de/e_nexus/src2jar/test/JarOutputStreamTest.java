/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.test;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;

import de.e_nexus.src2jar.stream.CompileJarOutputStream;

public class JarOutputStreamTest {
	@SuppressWarnings("deprecation")
	public void testJar() throws Exception {

		File tmpJar = File.createTempFile("s2j", "test.jar");

		CompileJarOutputStream jos = new CompileJarOutputStream(new FileOutputStream(tmpJar));
		jos.putNextEntry(new ZipEntry("de/Test.java"));
		jos.write("package de; public class Test{public int test(){return 22;}}".getBytes());
		jos.closeEntry();
		jos.close();
		URLClassLoader cl = URLClassLoader.newInstance(new URL[] { tmpJar.toURL() });
		Object k = cl.loadClass("de.Test").newInstance();
		Number n = (Number) k.getClass().getMethod("test").invoke(k);
		assert n.intValue() == 22;
	}

	public static void main(String[] args) throws Exception {
		JarOutputStreamTest jso = new JarOutputStreamTest();
		jso.testJar();
	}
}
