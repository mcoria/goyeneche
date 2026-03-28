package net.chesstango.goyeneche.stream;

import net.chesstango.goyeneche.UCICommand;

/**
 * @author Mauricio Coria
 */
public class UCIActiveStreamReader implements Runnable {
    protected UCIInputStream input;
    protected UCIOutputStream output;

    @Override
    public void run() {
        UCICommand message = null;
        while ((message = input.get()) != null) {
            output.accept(message);
        }
    }

    public void setInputStream(UCIInputStream input) {
        this.input = input;
    }

    public void setOutputStream(UCIOutputStream output) {
        this.output = output;
    }
}
