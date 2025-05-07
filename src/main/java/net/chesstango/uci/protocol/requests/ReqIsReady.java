package net.chesstango.uci.protocol.requests;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.UCIEngine;

/**
 * @author Mauricio Coria
 */
public class ReqIsReady implements UCIRequest {

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
