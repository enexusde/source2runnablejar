/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;

import de.e_nexus.src2jar.stream.CompileJarOutputStream;

/**
 * Tests the default java proxy.
 * 
 * @author Peter Rader
 *
 */
public class ProxyTest {

	private static final String MESSAGE = "hm?";

	/**
	 * Tests the usage of a proxy.
	 * 
	 * @throws Exception
	 */
	public void testProxy() throws Exception {
		File jar = File.createTempFile("cjos", ".jar");
		jar.deleteOnExit();

		CompileJarOutputStream stream = new CompileJarOutputStream(new FileOutputStream(jar));
		PrintWriter writer = new PrintWriter(stream);
		stream.putNextEntry(new ZipEntry("de/Timmy.java"));
		writer.println("package de;");
		writer.println("public class Timmy implements Runnable{");
		writer.println("  public void run(){");
		writer.println("    throw new RuntimeException(\"" + MESSAGE + "\");");
		writer.println("  }");
		writer.println("}");
		writer.flush();
		stream.closeEntry();
		stream.close();
		URLClassLoader urlcl = new URLClassLoader(new URL[] { jar.toURI().toURL() });
		@SuppressWarnings("unchecked")
		Class<Runnable> clazz = (Class<Runnable>) urlcl.loadClass("de.Timmy");
		Runnable original = clazz.newInstance();
		Runnable proxy = (Runnable) Proxy.newProxyInstance(null, new Class[] { Runnable.class },
				(instance, method, args) -> method.invoke(original, args));
		urlcl.close();
		Throwable problem = null;
		try {
			proxy.run();
		} catch (UndeclaredThrowableException e) {
			problem = e;
		}
		while (problem.getCause() != null) {
			problem = problem.getCause();
		}
		assert problem.getMessage().equals(MESSAGE);
	}
}
