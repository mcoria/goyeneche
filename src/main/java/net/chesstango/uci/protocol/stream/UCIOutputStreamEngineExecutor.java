package net.chesstango.uci.protocol.stream;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.UCIEngine;
import net.chesstango.uci.protocol.requests.UCIRequest;

/**
 * @author Mauricio Coria
 */
public class UCIOutputStreamEngineExecutor implements UCIOutputStream {

    private final UCIEngine engine;

    public UCIOutputStreamEngineExecutor(UCIEngine engine) {
        this.engine = engine;
    }

    @Override
    public void accept(UCICommand uciCommand) {
        if (uciCommand instanceof UCIRequest request) {
            request.execute(engine);
        }
    }
}
