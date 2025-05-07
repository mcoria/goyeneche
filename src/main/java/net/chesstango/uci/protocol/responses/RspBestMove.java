package net.chesstango.uci.protocol.responses;

import lombok.Getter;
import net.chesstango.uci.protocol.UCIGui;

/**
 * @author Mauricio Coria
 */
@Getter
public class RspBestMove implements UCIResponse {

    private final String bestMove;
    private final String ponderMove;

    public RspBestMove(String bestMove) {
        this.bestMove = bestMove;
        this.ponderMove = null;
    }

    public RspBestMove(String bestMove, String ponderMove) {
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
