package net.chiappone.util.file;

import java.io.File;
import java.util.TimerTask;

/**
 * Monitors a file's last modified date. Example usage:
 * <p>
 * <pre>
 * TimerTask task = new FileModifiedMonitor( propertyFile ) {
 *
 * 	protected void onChange( File file ) {
 *
 * 		// do something, file has been modified
 *    }
 * };
 * new Timer().schedule( task, new Date(), 5000 );
 * </pre>
 *
 * @author Kurtis Chiappone
 * @date 10/9/2016
 */
public abstract class FileModifiedMonitor extends TimerTask {

    private File file = null;
    private long timestamp;

    /**
     * @param file
     */
    public FileModifiedMonitor( File file ) {

        this.file = file;
        this.timestamp = file.lastModified();
    }

    /**
     * @return the file
     */
    public File getFile() {

        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile( File file ) {

        this.file = file;
    }

    /**
     * @return the timestamp
     */
    public long getTimestamp() {

        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp( long timestamp ) {

        this.timestamp = timestamp;
    }

    /**
     * @param file
     */
    protected abstract void onChange( File file );

    /*
     * (non-Javadoc)
     *
     * @see java.util.TimerTask#run()
     */
    @Override public final void run() {

        long lastModified = getFile().lastModified();

        if ( getTimestamp() != lastModified ) {
            setTimestamp( lastModified );
            onChange( getFile() );
        }
    }

}
