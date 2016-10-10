package net.chiappone.util;

import java.io.*;

/**
 * @author Kurtis Chiappone
 * @date 10/9/2016
 */
public class FileUtil {

	/**
	 * @param src
	 * @param dest
	 * @throws IOException
	 */
	public static void copy( InputStream src, File dest ) throws IOException {

		OutputStream out = new FileOutputStream( dest );

		byte[] buf = new byte[ 1024 ];
		int len;

		while ( ( len = src.read( buf ) ) > 0 ) {

			out.write( buf, 0, len );

		}

		src.close();
		out.close();

	}

	/**
	 * @param src
	 * @param dest
	 * @throws IOException
	 */
	public static void copy( File src, File dest ) throws IOException {

		InputStream in = new FileInputStream( src );
		copy( in, dest );

	}

	/**
	 * Deletes a file or directory. If the given file is a directory, this
	 * method will recursively delete child directories/files.
	 *
	 * @param f
	 */
	@SuppressWarnings( "ResultOfMethodCallIgnored" )
	public static void delete( File f ) {

		if ( f.exists() ) {

			// First delete all children

			File[] children = f.listFiles();

			if ( children != null && children.length > 0 ) {

				for ( File child : children ) {

					if ( child.isDirectory() ) {

						delete( child );

					} else {

						child.delete();

					}

				}

			}

			// Then delete the given file

			f.delete();

		}

	}

}
