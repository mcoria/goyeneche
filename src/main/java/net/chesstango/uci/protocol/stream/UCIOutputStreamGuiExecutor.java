package net.chesstango.uci.protocol.stream;

import net.chesstango.uci.protocol.UCIGui;
import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.UCIResponse;

import static net.chesstango.uci.protocol.UCICommand.MessageType.Response;

/**
 * @author Mauricio Coria
 */
public class UCIOutputStreamGuiExecutor implements UCIOutputStream {

    private final UCIGui executor;

    public UCIOutputStreamGuiExecutor(UCIGui executor) {
        this.executor = executor;
    }

    @Override
    public void accept(UCICommand message) {
        if (Response.equals(message.getMessageType())) {
            ((UCIResponse) message).execute(executor);
        }
    }
}
