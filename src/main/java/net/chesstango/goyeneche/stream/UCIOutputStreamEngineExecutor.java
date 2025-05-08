package net.chesstango.goyeneche.stream;

import net.chesstango.goyeneche.UCICommand;
import net.chesstango.goyeneche.UCIEngine;
import net.chesstango.goyeneche.requests.UCIRequest;

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
