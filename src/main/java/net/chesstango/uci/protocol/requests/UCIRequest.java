package net.chesstango.uci.protocol.requests;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.UCIEngine;

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
}
