package net.chesstango.uci.protocol.stream;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.stream.strings.StringConsumer;

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
