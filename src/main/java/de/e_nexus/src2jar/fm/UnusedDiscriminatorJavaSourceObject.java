/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.fm;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Discrimiate unused methods to a in-memory sourcecode-object.
 * 
 * @author Peter Rader
 */
public interface UnusedDiscriminatorJavaSourceObject extends UnusedDiscriminatorJavaFileObject {
	/**
	 * We do not need to implement this method because this object exists only for
	 * represent a compiled object.
	 * <p>
	 * The result would always be a {@link Kind#CLASS}, but we throw a
	 * {@link IllegalStateException} at this place because the behave of the VM must
	 * have been changed to an incompatible state who might require this method.
	 */
	@Override
	default boolean delete() {
		throw new IllegalStateException("Not implemented!");
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
	default OutputStream openOutputStream() throws IOException {
		throw new IllegalStateException("Not implemented!");
	}
}
