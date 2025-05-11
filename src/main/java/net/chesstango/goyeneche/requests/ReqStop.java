package net.chesstango.goyeneche.requests;

import net.chesstango.goyeneche.UCIEngine;

/**
 * @author Mauricio Coria
 */
public final class ReqStop implements UCIRequest {

    static final ReqStop INSTANCE = new ReqStop();

    private ReqStop() {}

    @Override
    public void execute(UCIEngine executor) {
        executor.do_stop(this);
    }

    @Override
    public String toString() {
        return "stop";
    }
}
