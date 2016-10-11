package net.chiappone.util.file;

import java.io.File;
import java.util.TimerTask;

/**
 * Monitors a file's last modified date. Example usage:<br><br>
 * <pre>
 * TimerTask task = new FileModifiedMonitor( propertyFile ) {
 *
 * 	protected void onChange() {
 *
 * 		// do something, file has been modified
 *    }
 * };
 * new Timer().schedule( task, new Date(), 5000 );
 * </pre>
 *
 * @author Kurtis Chiappone
 */
public abstract class FileModifiedMonitor extends TimerTask {

    private File file = null;
    private long timestamp;

    public FileModifiedMonitor( File file ) {

        this.file = file;
        this.timestamp = file.lastModified();
    }

    public File getFile() {

        return file;
    }

    public void setFile( File file ) {

        this.file = file;
    }

    public long getTimestamp() {

        return timestamp;
    }

    public void setTimestamp( long timestamp ) {

        this.timestamp = timestamp;
    }

    /**
     * This method will be called when the file is changed. Override it.
     */
    protected abstract void onChange();

    @Override public final void run() {

        long lastModified = getFile().lastModified();

        if ( getTimestamp() != lastModified ) {
            setTimestamp( lastModified );
            onChange();
        }
    }

}
