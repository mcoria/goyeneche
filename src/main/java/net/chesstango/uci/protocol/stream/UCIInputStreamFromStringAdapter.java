package net.chesstango.uci.protocol.stream;

import net.chesstango.uci.protocol.internal.UCIDecoder;
import net.chesstango.uci.protocol.UCICommand;

import java.util.function.Supplier;

/**
 * @author Mauricio Coria
 */
public class UCIInputStreamFromStringAdapter implements UCIInputStream {
    private final UCIDecoder uciDecoder = new UCIDecoder();
    private final Supplier<String> reader;

    public UCIInputStreamFromStringAdapter(Supplier<String> reader) {
        this.reader = reader;
    }

    @Override
    public UCICommand get() {
        String line = reader.get();
        return line == null ? null : uciDecoder.parseMessage(line);
    }

}
