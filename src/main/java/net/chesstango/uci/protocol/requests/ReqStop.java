package net.chesstango.uci.protocol.requests;

import net.chesstango.uci.protocol.UCIEngine;

/**
 * @author Mauricio Coria
 */
public final class ReqStop implements UCIRequest {

    static final ReqStop INSTANCE = new ReqStop();

    @Override
    public void execute(UCIEngine executor) {
        executor.do_stop(this);
    }

    @Override
    public String toString() {
        return "stop";
    }
}
