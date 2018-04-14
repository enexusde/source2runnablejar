/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.fm;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.JavaFileObject;

/**
 * File adapter for all files src2jar require.
 * <p>
 * This might be a classfile or a sourcefile.
 * 
 * @author Peter Rader
 *
 */
public interface UnusedDiscriminatorJavaFileObject extends JavaFileObject {

	/**
	 * This method is not used under normal conditions. If any use of this method is
	 * required a bug report must be written in order to compile the sources
	 * successfully.
	 * <p>
	 * Because of the fact that we must implement this method, even if it is not
	 * required, a {@link IllegalStateException} is thrown guiding to write a
	 * bug-report.
	 * <p>
	 * That this method must be implemented might be a
	 * Interface-Segregation-Principle-Problem. If the OOD has changed you should
	 * use a new Version of the library.
	 * 
	 * @deprecated
	 */
	@Override
	default Reader openReader(boolean ignoreEncodingErrors) throws IOException {
		throw new IllegalStateException("Not implemented!");
	}

	/**
	 * This method is not used under normal conditions. If any use of this method is
	 * required a bug report must be written in order to compile the sources
	 * successfully.
	 * <p>
	 * Because of the fact that we must implement this method, even if it is not
	 * required, a {@link IllegalStateException} is thrown guiding to write a
	 * bug-report.
	 * <p>
	 * That this method must be implemented might be a
	 * Interface-Segregation-Principle-Problem. If the OOD has changed you should
	 * use a new Version of the library.
	 * 
	 * @deprecated
	 */

	@Override
	default long getLastModified() {
		throw new IllegalStateException("Not implemented!");
	}

	/**
	 * This method is not used under normal conditions. If any use of this method is
	 * required a bug report must be written in order to compile the sources
	 * successfully.
	 * <p>
	 * Because of the fact that we must implement this method, even if it is not
	 * required, a {@link IllegalStateException} is thrown guiding to write a
	 * bug-report.
	 * <p>
	 * That this method must be implemented might be a
	 * Interface-Segregation-Principle-Problem. If the OOD has changed you should
	 * use a new Version of the library.
	 * 
	 * @deprecated
	 */

	@Override
	default NestingKind getNestingKind() {
		throw new IllegalStateException("Not implemented!");
	}

	/**
	 * This method is not used under normal conditions. If any use of this method is
	 * required a bug report must be written in order to compile the sources
	 * successfully.
	 * <p>
	 * Because of the fact that we must implement this method, even if it is not
	 * required, a {@link IllegalStateException} is thrown guiding to write a
	 * bug-report.
	 * <p>
	 * That this method must be implemented might be a
	 * Interface-Segregation-Principle-Problem. If the OOD has changed you should
	 * use a new Version of the library.
	 * 
	 * @deprecated
	 */

	@Override
	default URI toUri() {
		throw new IllegalStateException("Not implemented!");
	}

	/**
	 * This method is not used under normal conditions. If any use of this method is
	 * required a bug report must be written in order to compile the sources
	 * successfully.
	 * <p>
	 * Because of the fact that we must implement this method, even if it is not
	 * required, a {@link IllegalStateException} is thrown guiding to write a
	 * bug-report.
	 * <p>
	 * That this method must be implemented might be a
	 * Interface-Segregation-Principle-Problem. If the OOD has changed you should
	 * use a new Version of the library.
	 * 
	 * @deprecated
	 */

	@Override
	default String getName() {
		System.out.println(this.getClass());
		throw new IllegalStateException("Not implemented!");
	}

	/**
	 * This method is not used under normal conditions. If any use of this method is
	 * required a bug report must be written in order to compile the sources
	 * successfully.
	 * <p>
	 * Because of the fact that we must implement this method, even if it is not
	 * required, a {@link IllegalStateException} is thrown guiding to write a
	 * bug-report.
	 * <p>
	 * That this method must be implemented might be a
	 * Interface-Segregation-Principle-Problem. If the OOD has changed you should
	 * use a new Version of the library.
	 * 
	 * @deprecated
	 */

	@Override
	default InputStream openInputStream() throws IOException {
		throw new IllegalStateException("Not implemented!");
	}

	/**
	 * This method is not used under normal conditions. If any use of this method is
	 * required a bug report must be written in order to compile the sources
	 * successfully.
	 * <p>
	 * Because of the fact that we must implement this method, even if it is not
	 * required, a {@link IllegalStateException} is thrown guiding to write a
	 * bug-report.
	 * <p>
	 * That this method must be implemented might be a
	 * Interface-Segregation-Principle-Problem. If the OOD has changed you should
	 * use a new Version of the library.
	 * 
	 * @deprecated
	 */

	@Override
	default Modifier getAccessLevel() {
		throw new IllegalStateException("Not implemented!");
	}

	/**
	 * This method is not used under normal conditions. If any use of this method is
	 * required a bug report must be written in order to compile the sources
	 * successfully.
	 * <p>
	 * Because of the fact that we must implement this method, even if it is not
	 * required, a {@link IllegalStateException} is thrown guiding to write a
	 * bug-report.
	 * <p>
	 * That this method must be implemented might be a
	 * Interface-Segregation-Principle-Problem. If the OOD has changed you should
	 * use a new Version of the library.
	 * 
	 * @deprecated
	 */

	@Override
	default Writer openWriter() throws IOException {
		throw new IllegalStateException("Not implemented!");
	}

}
