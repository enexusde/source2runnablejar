/*  ___  ___  _   _ _ __ ___ ___  |‾|_ ___    _ __ _   _ _ __  _ __   __ _|‾|__ |‾| ___ (‾) __ _ _ __ 
 * / __|/ _ \| | | | '__/ __/ _ \ | __/ _ \  | '__| | | | '_ \| '_ \ / _` | '_ \| |/ _ \|‾|/ _` | '__|
 * \__ \ (_) | |_| | | | (_|  __/ | || (_) | | |  | |_| | | | | | | | (_| | |_) | |  __/| | (_| | |
 * |___/\___/ \__,_|_|  \___\___|  \__\___/  |_|   \__,_|_| |_|_| |_|\__,_|_.__/|_|\___|/ |\__,_|_|
 * Copyright Peter Rader 2018 - GNU 3.0                                               |__/
 */
package de.e_nexus.src2jar.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;

/**
 * {@link OutputStream} for compiled jars.
 * 
 * @author Peter Rader
 */
public class CompiledMavenJarOutputStream extends CompileJarOutputStream {

	/**
	 * The default version ({@value CompiledMavenJarOutputStream#DEFAULT_VERSION}
	 * comment to display when dependency is fetched via maven, may
	 * <code>null</code> if never used.
	 */
	private static final String DEFAULT_VERSION = "Src2Jar";

	/**
	 * Constructor to create a stream for maven archives.
	 * 
	 * @param out
	 *            The java-source-code to compile to the maven archive.
	 * @throws IOException
	 */
	public CompiledMavenJarOutputStream(OutputStream out) throws IOException {
		super(out);
	}

	private String mainclass, version, artifactId, groupId, archiver = DEFAULT_VERSION;

	public void close() throws IOException {
		StringBuilder manifest = new StringBuilder("Manifest-Version:1.0\n");
		if (artifactId != null) {
			manifest.append("Implementation-Title: ");
			manifest.append(artifactId);
			manifest.append("\n");
		}
		if (version != null) {
			manifest.append("Implementation-Version: ");
			manifest.append(version);
			manifest.append("\n");
		}
		if (archiver != null) {
			manifest.append("Archiver-Version: ");
			manifest.append(archiver);
			manifest.append("\n");
		}
		if (groupId != null) {
			manifest.append("Implementation-Vendor-Id: ");
			manifest.append(groupId);
			manifest.append("\n");
		}
		if (mainclass != null) {
			manifest.append("Main-Class: ");
			manifest.append(mainclass);
			manifest.append("\n");
		}
		manifest.append("\n");

		internalPlaceEntry(manifest.toString().getBytes(), new ZipEntry("META-INF/MANIFEST.MF"));
		super.close();
	}

	public String getMainclass() {
		return mainclass;
	}

	public void setMainclass(String mainclass) {
		this.mainclass = mainclass;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArchiver() {
		return archiver;
	}

	public void setArchiver(String archiver) {
		this.archiver = archiver;
	}
}
