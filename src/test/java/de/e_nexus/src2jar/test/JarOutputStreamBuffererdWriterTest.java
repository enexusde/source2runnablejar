package de.e_nexus.src2jar.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;

import de.e_nexus.src2jar.stream.CompileJarOutputStream;

public class JarOutputStreamBuffererdWriterTest {

	public void testCompilation() throws Exception {
		File tmpJar = File.createTempFile("s2j", "test.jar");

		CompileJarOutputStream jos = new CompileJarOutputStream(new FileOutputStream(tmpJar));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(jos));
		jos.putNextEntry(new ZipEntry("de/Test.java"));
		pw.write("package de; public class Test{public int test(){return 22;}}");
		pw.flush();
		jos.closeEntry();
		jos.close();
		URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { tmpJar.toURL() });
		Object instance = classLoader.loadClass("de.Test").newInstance();
		Number result = (Number) instance.getClass().getMethod("test").invoke(instance);
		assert result.intValue() == 22;
	}
	
	public void testTwoFilesCompilation() throws Exception {
		File tmpJar = File.createTempFile("s2j", "test.jar");

		CompileJarOutputStream jos = new CompileJarOutputStream(new FileOutputStream(tmpJar));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(jos));
		jos.putNextEntry(new ZipEntry("de/Test.java"));
		pw.write("package de; public class Test{public int test(){return 22;}}");
		pw.flush();
		jos.closeEntry();
		jos.putNextEntry(new ZipEntry("de/Test2.java"));
		pw.write("package de; public class Test2{public int test(){return 22;}}");
		pw.flush();
		jos.closeEntry();
		jos.close();
		URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { tmpJar.toURL() });
		Object instance = classLoader.loadClass("de.Test").newInstance();
		Number result = (Number) instance.getClass().getMethod("test").invoke(instance);
		assert result.intValue() == 22;
	}
}
