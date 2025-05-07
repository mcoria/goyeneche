package net.chesstango.uci.protocol.requests;

import net.chesstango.uci.protocol.UCIEngine;

/**
 * @author Mauricio Coria
 */
public final class ReqQuit implements UCIRequest {

    static final ReqQuit INSTANCE = new ReqQuit();

    private ReqQuit() {
    }

    @Override
    public void execute(UCIEngine executor) {
        executor.do_quit(this);
    }

    @Override
    public String toString() {
        return "quit";
    }
}
