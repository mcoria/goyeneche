package net.chesstango.goyeneche.requests;

import net.chesstango.goyeneche.UCIEngine;

/**
 * @author Mauricio Coria
 */
public final class ReqUciNewGame implements UCIRequest {

    static final ReqUciNewGame INSTANCE = new ReqUciNewGame();

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
