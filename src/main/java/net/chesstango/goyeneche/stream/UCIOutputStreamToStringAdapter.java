package net.chesstango.goyeneche.stream;

import net.chesstango.goyeneche.UCICommand;
import net.chesstango.goyeneche.stream.strings.StringConsumer;

/**
 * @author Mauricio Coria
 */
public class UCIOutputStreamToStringAdapter implements UCIOutputStream {

    private final StringConsumer out;

    public UCIOutputStreamToStringAdapter(StringConsumer out) {
        this.out = out;
    }

    @Override
    public void accept(UCICommand message) {
        out.accept(message == null ? null : message.toString());
    }
}
