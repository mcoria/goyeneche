package net.chesstango.uci.protocol.requests;

import net.chesstango.uci.protocol.UCIEngine;

/**
 * @author Mauricio Coria
 */
public class ReqIsReady implements UCIRequest {

    @Override
    public void execute(UCIEngine executor) {
        executor.do_isReady(this);
    }

    @Override
    public String toString() {
        return "isready";
    }
}
