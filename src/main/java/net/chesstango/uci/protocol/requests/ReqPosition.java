package net.chesstango.uci.protocol.requests;

import lombok.Getter;
import net.chesstango.uci.protocol.UCIEngine;

import java.util.List;

/**
 * @author Mauricio Coria
 */
@Getter
public final class ReqPosition implements UCIRequest {

    public enum CmdType {STARTPOS, FEN}

    private final CmdType type;

    /**
     * FEN string. If null, then the position is the starting position.
     */
    private final String fen;

    /**
     * List of moves in UCI format. If null, then no moves are made.
     */
    private final List<String> moves;


    ReqPosition(String fen, List<String> moves) {
        this.type = CmdType.FEN;
        this.fen = fen;
        this.moves = moves;
    }

    ReqPosition(List<String> moves) {
        this.type = CmdType.STARTPOS;
        this.fen = null;
        this.moves = moves;
    }

    @Override
    public void execute(UCIEngine executor) {
        executor.do_position(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("position ");
        if (CmdType.STARTPOS.equals(type)) {
            sb.append("startpos");
        } else {
            sb.append("fen ").append(fen);
        }
        if (moves != null && !moves.isEmpty()) {
            sb.append(" moves");
            for (String move : moves) {
                sb.append(" ");
                sb.append(move);
            }
        }
        return sb.toString();
    }
}
