package net.chiappone.util.file;

import java.io.*;

/**
 * Variety of file utility methods.
 *
 * @author Kurtis Chiappone
 */
public class FileUtil {

    /**
     * Copies source to destination.
     *
     * @param src  source stream
     * @param dest destination file
     * @throws IOException if errors during stream write or close
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
     * Copies a source file to destination.
     *
     * @param src  source file
     * @param dest destination file
     * @throws IOException if errors during stream write or close
     */
    public static void copy( File src, File dest ) throws IOException {

        InputStream in = new FileInputStream( src );
        copy( in, dest );

    }

    /**
     * Deletes a file or directory. If the given file is a directory, this
     * method will recursively delete child directories/files.
     *
     * @param f file to delete
     */
    @SuppressWarnings( "ResultOfMethodCallIgnored" ) public static void delete( File f ) {

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
