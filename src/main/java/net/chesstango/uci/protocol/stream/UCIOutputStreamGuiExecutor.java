package net.chesstango.uci.protocol.stream;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.UCIGui;
import net.chesstango.uci.protocol.UCIResponse;

/**
 * @author Mauricio Coria
 */
public class UCIOutputStreamGuiExecutor implements UCIOutputStream {

    private final UCIGui executor;

    public UCIOutputStreamGuiExecutor(UCIGui executor) {
        this.executor = executor;
    }

    @Override
    public void accept(UCICommand uciCommand) {
        if (uciCommand instanceof UCIResponse response) {
            response.execute(executor);
        }
    }
}
