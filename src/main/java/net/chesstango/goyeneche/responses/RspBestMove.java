package net.chesstango.goyeneche.responses;

import lombok.Getter;
import net.chesstango.goyeneche.UCIGui;

/**
 * @author Mauricio Coria
 */
@Getter
public final class RspBestMove implements UCIResponse {

    private final String bestMove;
    private final String ponderMove;

    RspBestMove(String bestMove) {
        this.bestMove = bestMove;
        this.ponderMove = null;
    }

    RspBestMove(String bestMove, String ponderMove) {
        this.bestMove = bestMove;
        this.ponderMove = ponderMove;
    }

    @Override
    public void execute(UCIGui executor) {
        executor.do_bestMove(this);
    }

    @Override
    public String toString() {
        return "bestmove " + bestMove + (ponderMove == null ? "" : " ponder " + ponderMove);
    }

}
