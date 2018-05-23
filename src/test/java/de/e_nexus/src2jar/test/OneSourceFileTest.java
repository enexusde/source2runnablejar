/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import de.e_nexus.src2jar.fm.DefaultSystemFileManager;
import de.e_nexus.src2jar.fm.JavaSource;

public class OneSourceFileTest {

	public void testOneSourceFile() throws IOException, ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SecurityException, InterruptedException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		DefaultSystemFileManager fileManager = new DefaultSystemFileManager(
				compiler.getStandardFileManager(null, null, null), Collections.emptySet());
		Iterable<String> options = Arrays.asList("-g:lines");
		JavaSource j = new JavaSource("package t; public class OSTJ{}", "OSTJ");
		JavaSource e = new JavaSource("package t; public class OSTE extends t.OSTJ{}", "OSTE");
		compiler.getTask(null, fileManager, null, options, null, Arrays.asList(j, e)).call();
		fileManager.close();
	}
}
