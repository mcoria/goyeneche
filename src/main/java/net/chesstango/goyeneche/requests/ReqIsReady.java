package net.chesstango.goyeneche.requests;

import net.chesstango.goyeneche.UCIEngine;

/**
 * @author Mauricio Coria
 */
public final class ReqIsReady implements UCIRequest {

    static final ReqIsReady INSTANCE = new ReqIsReady();

    private ReqIsReady() {
    }

    @Override
    public void execute(UCIEngine executor) {
        executor.do_isReady(this);
    }

    @Override
    public String toString() {
        return "isready";
    }
}
