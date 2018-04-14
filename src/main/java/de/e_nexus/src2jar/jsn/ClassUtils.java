/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.jsn;

import java.io.File;
import java.util.zip.ZipEntry;

/**
 * Utils for classes.
 * 
 * @author Peter Rader
 *
 */
public class ClassUtils {

	/**
	 * Default java sourcecode extension. Always
	 * {@value #JAVA_SOURCE_FILE_EXTENSION}.
	 */
	private static final String JAVA_SOURCE_FILE_EXTENSION = ".java";

	/**
	 * Get the Fullqualified (aka canonical) classname of a java sourcecode absolute
	 * filename.
	 * 
	 * <p>
	 * Does minimal repair operations to the filename in order to be fast
	 * <table border="1">
	 * <tr>
	 * <th>Input</th>
	 * <th>Output
	 * <tr>
	 * <td>com/util/StringUtil.java</td>
	 * <td>com.util.StringUtil
	 * </table>
	 * 
	 * 
	 * @param sourceFilename
	 *            The sourcecode filename.
	 * @return The Fullqualified classname of the java-source.
	 */
	public static String toClassname(String sourceFilename) {
		sourceFilename = sourceFilename.replace('/', '.');
		sourceFilename = sourceFilename.replace(File.separatorChar, '.');
		while (sourceFilename.charAt(0) == '.') {
			sourceFilename = sourceFilename.substring(1);
		}
		sourceFilename = sourceFilename.replace('-', '_');
		return sourceFilename.substring(0, sourceFilename.length() - JAVA_SOURCE_FILE_EXTENSION.length());
	}

	/**
	 * Get the simple classname of the absolute filename.
	 * <p>
	 * Does minimal repair operations to the filename in order to be fast
	 * 
	 * <table border="1">
	 * <tr>
	 * <th>Input</th>
	 * <th>Output
	 * <tr>
	 * <td>com/util/StringUtil.java</td>
	 * <td>StringUtil
	 * </table>
	 * 
	 * @param filename
	 *            The absolute filename.
	 * @return
	 */
	public static String toSimplename(String filename) {
		String classname = toClassname(filename);
		return classname.substring(classname.lastIndexOf(".") + 1);
	}

	/**
	 * Replace a sourcecode-java-entry with its bytecode-class-entry.
	 * 
	 * @param zipEntry
	 *            The zip entry of the java-sourcecode-file.
	 * @return The {@link ZipEntry} of the class-bytecode-file.
	 */
	public static ZipEntry convertClass(ZipEntry zipEntry) {
		String name = zipEntry.getName();
		if (name.endsWith(".java"))
			return new ZipEntry(name.substring(0, name.length() - 5) + ".class");
		else
			throw new RuntimeException("Name must end with .java!");
	}
}
