package net.chesstango.uci.protocol.requests;

import net.chesstango.uci.protocol.UCIEngine;

/**
 * @author Mauricio Coria
 */
public final class ReqUci implements UCIRequest {

    public static ReqUci INSTANCE = new ReqUci();

    private ReqUci() {
    }

    @Override
    public void execute(UCIEngine executor) {
        executor.do_uci(this);
    }

    @Override
    public String toString() {
        return "uci";
    }

}
