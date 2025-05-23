package net.chesstango.goyeneche.responses;

import net.chesstango.goyeneche.UCIGui;

/**
 * @author Mauricio Coria
 */
public final class RspReadyOk implements UCIResponse {

    static final RspReadyOk INSTANCE = new RspReadyOk();

    private RspReadyOk() {}

    @Override
    public void execute(UCIGui executor) {
        executor.do_readyOk(this);
    }

    @Override
    public String toString() {
        return "readyok";
    }
}
