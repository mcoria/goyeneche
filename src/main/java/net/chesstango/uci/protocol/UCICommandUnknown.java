package net.chesstango.uci.protocol;

import static net.chesstango.uci.protocol.UCICommand.MessageType.Unknown;

/**
 * @author Mauricio Coria
 */
public class UCICommandUnknown implements UCICommand {

    private final String line;

    public UCICommandUnknown(String line) {
        this.line = line;
    }

    @Override
    public MessageType getMessageType() {
        return Unknown;
    }

    @Override
    public String toString() {
        return line;
    }
}
