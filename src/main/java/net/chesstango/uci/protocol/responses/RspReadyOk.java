package net.chesstango.uci.protocol.responses;

import net.chesstango.uci.protocol.UCIGui;

/**
 * @author Mauricio Coria
 */
public class RspReadyOk implements UCIResponse {

    @Override
    public void execute(UCIGui executor) {
        executor.do_readyOk(this);
    }

    @Override
    public String toString() {
        return "readyok";
    }
}
