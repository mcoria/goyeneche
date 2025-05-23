package net.chesstango.goyeneche.responses;

import net.chesstango.goyeneche.UCIGui;

/**
 * @author Mauricio Coria
 */
public final class RspUciOk implements UCIResponse {

    static final RspUciOk INSTANCE = new RspUciOk();

    private RspUciOk() {}

    @Override
    public void execute(UCIGui executor) {
        executor.do_uciOk(this);
    }

    @Override
    public String toString() {
        return "uciok";
    }

}
