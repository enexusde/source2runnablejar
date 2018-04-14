/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.fm;

import java.io.IOException;

/**
 * The default java file object for classes.
 * <p>
 * Some of the methods should not be used.
 * 
 * @author Peter Rader
 *
 */
public interface DefaultJavaTargetFileObject extends UnusedDiscriminatorJavaFileObject {

	/**
	 * NOOP, act like the file has successfully removed.
	 * <p>
	 * In fact its not removed because it is inmemory.
	 * 
	 * @return true Because we act like we removed the file successfully.
	 */
	@Override
	default boolean delete() {
		return true;
	}

	/**
	 * We do not need to implement this method because this object exists only for
	 * represent a compiled object.
	 * <p>
	 * The result would always be a {@link Kind#CLASS}, but we throw a
	 * {@link IllegalStateException} at this place because the behave of the VM must
	 * have been changed to an incompatible state who might require this method.
	 */
	@Override
	default Kind getKind() {
		throw new IllegalStateException("Not implemented!");
	}

	/**
	 * This method is not implemented because we never need to know if a classname
	 * is compatible on a compiled class. We could evaluate the result but we throw
	 * a {@link IllegalStateException} at this place because the behave of the VM
	 * must have been changed to an incompatible state who might require this
	 * method.
	 */
	@Override
	default boolean isNameCompatible(String simpleName, Kind kind) {
		throw new IllegalStateException("Not implemented!");
	}

	/**
	 * This method violates the Interface-Segregation-Principle. We do not need this
	 * method and this method is never called.
	 * 
	 * <p>
	 * The javadoc points out that we could return <code>null</code> if no source
	 * exists but we throw a {@link IllegalStateException} at this place because the
	 * behave of the VM must have been changed to an incompatible state who might
	 * require this method.
	 */
	@Override
	default CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
		throw new IllegalStateException("Not implemented!");
	}

}
