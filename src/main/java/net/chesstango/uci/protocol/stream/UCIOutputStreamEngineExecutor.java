package net.chesstango.uci.protocol.stream;

import net.chesstango.uci.protocol.UCIEngine;
import net.chesstango.uci.protocol.UCIMessage;
import net.chesstango.uci.protocol.UCIRequest;

import static net.chesstango.uci.protocol.UCIMessage.MessageType.Request;

/**
 * @author Mauricio Coria
 */
public class UCIOutputStreamEngineExecutor implements UCIOutputStream {

    private final UCIEngine engine;

    public UCIOutputStreamEngineExecutor(UCIEngine engine) {
        this.engine = engine;
    }

    @Override
    public void accept(UCIMessage message) {
        if (Request.equals(message.getMessageType())) {
            ((UCIRequest) message).execute(engine);
        }
    }
}
