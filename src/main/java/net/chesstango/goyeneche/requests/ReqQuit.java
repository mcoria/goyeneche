package net.chesstango.goyeneche.requests;

import net.chesstango.goyeneche.UCIEngine;

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
