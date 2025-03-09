package net.chesstango.uci.protocol;

import net.chesstango.uci.protocol.requests.*;

/**
 * The UCIEngine interface defines the contract for handling UCI commands as part of the
 * Universal Chess Interface (UCI) protocol. Implementations of this interface are
 * responsible for processing various commands and executing the corresponding operations.
 * Each command is represented by a specific class that implements the UCIRequest interface.
 *
 *  @author Mauricio Coria
 */
public interface UCIEngine {
    void do_uci(ReqUci reqUci);

    void do_setOption(ReqSetOption reqSetOption);

    void do_isReady(ReqIsReady reqIsReady);

    void do_newGame(ReqUciNewGame reqUciNewGame);

    void do_position(ReqPosition reqPosition);

    void do_go(ReqGo reqGo);

    void do_stop(ReqStop reqStop);

    void do_quit(ReqQuit reqQuit);
}
