package net.chesstango.uci.protocol.requests;

import net.chesstango.uci.protocol.UCIEngine;

/**
 * @author Mauricio Coria
 */
public class ReqUciNewGame implements UCIRequest {
    public static final ReqUciNewGame INSTANCE = new ReqUciNewGame();

    private ReqUciNewGame() {
    }

    @Override
    public void execute(UCIEngine executor) {
        executor.do_newGame(this);
    }

    @Override
    public String toString() {
        return "ucinewgame";
    }
}
