package net.chesstango.goyeneche.requests;

import net.chesstango.goyeneche.UCIEngine;

/**
 * @author Mauricio Coria
 */
public final class ReqUci implements UCIRequest {

    static ReqUci INSTANCE = new ReqUci();

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
