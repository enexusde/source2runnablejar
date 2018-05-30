/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;

import de.e_nexus.src2jar.stream.CompiledMavenJarOutputStream;

public class MavenJarOutputStreamTest {

	public void testMavenJar() throws IOException {
		File jar = File.createTempFile("s2j", "test.jar");
		File txjar = new File(
				"c:\\Program Files\\apache-tomcat-8.5.27\\temp\\0-release-management\\WEB-INF\\lib\\hibernate-jpa-2.1-api-1.0.0.Final.jar");
		CompiledMavenJarOutputStream os = new CompiledMavenJarOutputStream(new FileOutputStream(jar));
		os.setMainclass("de.Test");
		os.registerEndorsedJar(txjar);
		os.putNextEntry(new JarEntry("de\\MTest.java"));
		os.write(
				"package de;import javax.persistence.Entity; public class MTest{public static void main(String ... args){}}"
						.getBytes());
		os.closeEntry();
		os.close();
		System.out.println("java -jar " + jar.getAbsolutePath() + " de.MTest");
	}
}
