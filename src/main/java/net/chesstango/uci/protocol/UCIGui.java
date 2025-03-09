package net.chesstango.uci.protocol;

import net.chesstango.uci.protocol.responses.*;

/**
 * The UCIGui interface defines the contract for handling UCI responses as part of the
 * Universal Chess Interface (UCI) protocol. Implementations of this interface are
 * responsible for processing various responses from the chess engine.
 * Each response is represented by a specific class that implements the UCIResponse interface.
 *
 *  @author Mauricio Coria
 */
public interface UCIGui {
    void do_uciOk(RspUciOk rspUciOk);

    void do_id(RspId rspId);

    void do_readyOk(RspReadyOk rspReadyOk);

    void do_bestMove(RspBestMove rspBestMove);

    void do_info(RspInfo rspInfo);
}
