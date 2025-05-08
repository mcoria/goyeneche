package net.chesstango.goyeneche;

import net.chesstango.goyeneche.responses.*;

/**
 * The UCIGui interface defines the contract for handling UCI responses as part of the
 * Universal Chess Interface (UCI) protocol. Implementations of this interface are
 * responsible for processing various responses from the chess engine.
 * Each response is represented by a specific class that implements the UCIResponse interface.
 *
 *  @author Mauricio Coria
 */
public interface UCIGui {
    /**
     * Handles the "uciok" response as part of the Universal Chess Interface (UCI) protocol.
     * This response is sent by the chess engine to confirm that it successfully understands
     * and supports the UCI protocol. The GUI must wait for this response before continuing
     * communication with the engine.
     *
     * @param rspUciOk The response object representing the "uciok" signal from the chess engine.
     *                 It indicates that the engine is ready and acknowledges the UCI protocol.
     */
    void do_uciOk(RspUciOk rspUciOk);

    /**
     * Handles the "option" response as part of the Universal Chess Interface (UCI) protocol.
     * This response provides details about a specific configurable option exposed by the chess engine.
     * The GUI can use this information to display the option to the user and/or allow adjustments
     * to the engine's settings based on the option's properties.
     *
     * @param rspOption The response object containing information about the option. It includes:
     *                  the name of the option, its type (e.g., CHECK, STRING, COMBO, BUTTON, SPIN),
     *                  the default value, and optional minimum or maximum values, if applicable.
     */
    void do_option(RspOption rspOption);

    /**
     * Processes an "id" response as part of the Universal Chess Interface (UCI) protocol.
     * This response provides identification information from the chess engine,
     * including its name or author. Multiple "id" responses may be sent by the engine,
     * each specifying either the name or author of the engine.
     *
     * @param rspId The response object representing the "id" command. It includes:
     *              - The type of identification (`RspIdType`), which could be either NAME or AUTHOR.
     *              - The text content containing the actual identification data (e.g., the engine's name or author's name).
     */
    void do_id(RspId rspId);

    /**
     * Handles the "readyok" response as part of the Universal Chess Interface (UCI) protocol.
     * This response is sent by the chess engine after receiving an "isready" command from the GUI,
     * indicating that the engine is fully initialized and ready to receive further commands.
     *
     * @param rspReadyOk The response object representing the "readyok" signal from the chess engine.
     *                   It confirms the engine's readiness to process subsequent commands.
     */
    void do_readyOk(RspReadyOk rspReadyOk);

    /**
     * Handles the "bestmove" response as part of the Universal Chess Interface (UCI) protocol.
     * This response is sent by the chess engine to indicate the best move it has calculated
     * from the current position. Optionally, it may include a "ponder" move, which is the move
     * the engine expects the opponent to play next.
     *
     * @param rspBestMove The response object representing the "bestmove" command. It contains:
     *                    - The best move in UCI format (e.g., "e2e4").
     *                    - An optional pondering move in UCI format to prepare for the opponent's response.
     */
    void do_bestMove(RspBestMove rspBestMove);

    /**
     * Processes the "info" response as part of the Universal Chess Interface (UCI) protocol.
     * This response is used by the chess engine to communicate intermediate information
     * during the search process. The information provided may include details such as
     * the depth of the search, the score of a position, the current line of moves being considered,
     * and other relevant data that can be displayed in the GUI for user feedback.
     *
     * @param rspInfo The response object containing information provided by the chess engine.
     *                It encapsulates various details about the engine's ongoing computations or state.
     */
    void do_info(RspInfo rspInfo);
}
