package net.chesstango.goyeneche.stream;

import net.chesstango.goyeneche.UCICommand;
import net.chesstango.goyeneche.requests.ReqQuit;

/**
 * @author Mauricio Coria
 */
public class UCIActiveStreamReader implements Runnable {
    protected boolean keepReading;
    protected Thread readingPipeThread;
    protected UCIInputStream input;
    protected UCIOutputStream output;


    public UCIActiveStreamReader() {
        keepReading = true;
    }


    @Override
    public synchronized void run() {
        if (keepReading) {
            UCIOutputStreamSwitch actionOutput = new UCIOutputStreamSwitch(ReqQuit.class::isInstance, this::stopReading);
            actionOutput.setOutputStream(output);

            readingPipeThread = Thread.currentThread();

            try {
                UCICommand message = null;
                while (keepReading && (message = input.get()) != null) {
                    actionOutput.accept(message);
                }
            } finally {
                keepReading = false;
                readingPipeThread = null;
            }
        }
    }

    public synchronized void stopReading() {
        keepReading = false;
        if (readingPipeThread != null && readingPipeThread.isAlive()) {
            readingPipeThread.interrupt();
        }
    }

    public void setInputStream(UCIInputStream input) {
        this.input = input;
    }

    public void setOutputStream(UCIOutputStream output) {
        this.output = output;
    }
}
