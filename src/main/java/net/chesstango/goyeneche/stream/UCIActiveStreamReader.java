package net.chesstango.goyeneche.stream;

import net.chesstango.goyeneche.UCICommand;
import net.chesstango.goyeneche.requests.ReqQuit;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Mauricio Coria
 */
public class UCIActiveStreamReader implements Runnable {
    protected UCIInputStream input;
    protected UCIOutputStream output;

    @Override
    public void run() {
        AtomicBoolean keepReading = new AtomicBoolean(true);
        UCIOutputStreamSwitch actionOutput = new UCIOutputStreamSwitch(ReqQuit.class::isInstance, () -> keepReading.set(false));
        actionOutput.setOutputStream(output);
        UCICommand message = null;
        while (keepReading.get() && (message = input.get()) != null) {
            actionOutput.accept(message);
        }
    }

    public void setInputStream(UCIInputStream input) {
        this.input = input;
    }

    public void setOutputStream(UCIOutputStream output) {
        this.output = output;
    }
}
