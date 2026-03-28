package net.chesstango.goyeneche.stream;

import net.chesstango.goyeneche.internal.UCIDecoder;
import net.chesstango.goyeneche.UCICommand;
import net.chesstango.goyeneche.stream.strings.StringSupplier;

/**
 * @author Mauricio Coria
 */
public class UCIInputStreamFromStringAdapter implements UCIInputStream {
    private final UCIDecoder uciDecoder = new UCIDecoder();
    private final StringSupplier reader;

    public UCIInputStreamFromStringAdapter(StringSupplier reader) {
        this.reader = reader;
    }

    @Override
    public UCICommand get() {
        String line = reader.get();
        return line == null ? null : uciDecoder.parseMessage(line);
    }
}
