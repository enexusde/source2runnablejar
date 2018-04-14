/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.fm;

import java.io.IOException;

import de.e_nexus.src2jar.jsn.ClassUtils;

/**
 * The source to compile.
 * <p>
 * This instance contains the sourcecode to compile. You will have no compiled
 * unit without an according {@link JavaSource}.
 * 
 * <p>
 * Because we do not parse the source, the Classname must be specified by
 * yourself. You could use {@link ClassUtils#toSimplename(String)} to
 * convert a filename to a simple classname.
 * 
 * @author Peter Rader
 *
 */
public final class JavaSource implements UnusedDiscriminatorJavaSourceObject {
	/**
	 * The java sourcecode.
	 */
	private final String sourceCode;
	private final String simpleClassName;

	public JavaSource(String sourceCode, String simpleClassName) {
		this.sourceCode = sourceCode;
		this.simpleClassName = simpleClassName;
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
		return sourceCode;
	}

	@Override
	public Kind getKind() {
		return Kind.SOURCE;
	}

	@Override
	public boolean isNameCompatible(String simpleName, Kind kind) {
		return simpleClassName.equals(simpleName);
	}

	/**
	 * This method is only used if the compilation fails.
	 */
	@Override
	public String getName() {
		// required in case of uncompilable sourcecode.
		return "source2runnablejar/" + simpleClassName;
	}

}