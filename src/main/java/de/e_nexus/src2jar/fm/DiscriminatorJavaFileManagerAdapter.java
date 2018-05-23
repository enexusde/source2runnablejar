/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.fm;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardJavaFileManager;

/**
 * The default java file manager.
 * 
 * @author Peter Rader
 */
public interface DiscriminatorJavaFileManagerAdapter extends StandardJavaFileManager {
	/**
	 * We do not need to implement this method because this object exists only for
	 * represent a compiled object.
	 * <p>
	 * The result would always be a {@link Kind#CLASS}, but we throw a
	 * {@link IllegalStateException} at this place because the behave of the VM must
	 * have been changed to an incompatible state who might require this method.
	 */
	default int isSupportedOption(String option) {
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

	default Iterable<? extends JavaFileObject> getJavaFileObjectsFromFiles(Iterable<? extends File> files) {
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

	default boolean isSameFile(FileObject a, FileObject b) {
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

	default Iterable<? extends JavaFileObject> getJavaFileObjectsFromStrings(Iterable<String> names) {
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

	default Iterable<? extends JavaFileObject> getJavaFileObjects(String... names) {
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

	default void setLocation(Location location, Iterable<? extends File> path) throws IOException {
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

	default boolean handleOption(String current, Iterator<String> remaining) {
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

	default Iterable<? extends File> getLocation(Location location) {
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

	default FileObject getFileForInput(Location location, String packageName, String relativeName) throws IOException {
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

	default FileObject getFileForOutput(Location location, String packageName, String relativeName, FileObject sibling)
			throws IOException {
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

	default JavaFileObject getJavaFileForInput(Location location, String className, Kind kind) throws IOException {
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

	default Iterable<? extends JavaFileObject> getJavaFileObjects(File... files) {
		throw new IllegalStateException("Not implemented!");
	}
}
