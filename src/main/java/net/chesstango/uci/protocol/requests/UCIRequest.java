package net.chesstango.uci.protocol.requests;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.UCIEngine;

import java.util.List;

/**
 * The UCIRequest interface represents a command request sent to a chess engine
 * as part of the Universal Chess Interface (UCI) protocol. Implementations of this
 * interface encapsulate specific commands defined by the protocol and provide
 * the necessary information and functionality to process these commands.
 * <p>
 * Implementing classes must specify the type of request they represent and
 * provide an implementation to execute their corresponding operation with
 * a UCIEngine instance.
 *
 * @author Mauricio Coria
 */
public interface UCIRequest extends UCICommand {

    /**
     * Executes the request using the provided UCIEngine instance.
     *
     * @param executor The UCIEngine instance that will execute the request.
     */
    void execute(UCIEngine executor);

    static ReqUci uci() {
        return ReqUci.INSTANCE;
    }

    static ReqIsReady isready() {
        return ReqIsReady.INSTANCE;
    }

    static ReqUciNewGame ucinewgame() {
        return ReqUciNewGame.INSTANCE;
    }

    static ReqPosition position(String fen, List<String> moves) {
        return new ReqPosition(fen, moves);
    }

    static ReqPosition position(List<String> moves) {
        return new ReqPosition(moves);
    }

    static ReqQuit quit() {
        return ReqQuit.INSTANCE;
    }

    static ReqStop stop() {
        return ReqStop.INSTANCE;
    }
}
