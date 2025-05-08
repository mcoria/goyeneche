package net.chesstango.goyeneche;

import net.chesstango.goyeneche.requests.*;

/**
 * The UCIEngine interface defines the contract for handling UCI commands as part of the
 * Universal Chess Interface (UCI) protocol. Implementations of this interface are
 * responsible for processing various commands and executing the corresponding operations.
 * Each command is represented by a specific class that implements the UCIRequest interface.
 *
 *  @author Mauricio Coria
 */
public interface UCIEngine {
    /**
     * Handles the "uci" command as part of the Universal Chess Interface (UCI) protocol.
     * This command is sent by the GUI to initialize communication with the chess engine,
     * requiring the engine to identify itself and confirm support for the UCI protocol.
     *
     * @param reqUci The request object representing the "uci" command.
     */
    void do_uci(ReqUci reqUci);

    /**
     * Handles the "setoption" command as part of the Universal Chess Interface (UCI) protocol.
     * This command is sent by the GUI to configure a specific option in the chess engine.
     * The option is identified by its name (id) and may have an associated value.
     *
     * @param reqSetOption The request object representing the "setoption" command. It contains
     *                     the name of the option to be configured (id) and the value to set, if applicable.
     */
    void do_setOption(ReqSetOption reqSetOption);

    /**
     * Handles the "isready" command as part of the Universal Chess Interface (UCI) protocol.
     * This command is sent by the GUI to query the chess engine's readiness state.
     * The engine should respond with "readyok" once it is prepared to receive further commands.
     *
     * @param reqIsReady The request object representing the "isready" command.
     */
    void do_isReady(ReqIsReady reqIsReady);

    /**
     * Handles the "ucinewgame" command as part of the Universal Chess Interface (UCI) protocol.
     * This command is sent by the GUI to notify the chess engine that a new game has started.
     * The engine should reset its internal state as necessary to ensure it operates correctly
     * for the new game.
     *
     * @param reqUciNewGame The request object representing the "ucinewgame" command. It informs
     *                      the engine to reinitialize itself for handling a new game session.
     */
    void do_newGame(ReqUciNewGame reqUciNewGame);

    /**
     * Handles the "position" command as part of the Universal Chess Interface (UCI) protocol.
     * This command sets up a specific chessboard state, either starting from the default initial position
     * or from a given FEN (Forsyth-Edwards Notation) string, along with any moves to be applied to the position.
     *
     * @param reqPosition The request object containing the required information to set up the position.
     *                    It includes the type of position (starting position or FEN), the FEN string (if provided),
     *                    and a list of moves in UCI format to apply to the position.
     */
    void do_position(ReqPosition reqPosition);

    /**
     * Handles the "go" command as part of the Universal Chess Interface (UCI) protocol.
     * This command is sent by the GUI to instruct the chess engine to begin searching
     * for the best move in the current position. The parameters of this command can
     * include various search constraints, such as time limits, depth limits, or specific
     * search strategies.
     *
     * @param reqGo The request object representing the "go" command. It contains
     *              the parameters for configuring the engine's search, such as limits on
     *              time or depth, move ordering, or other search-related constraints.
     */
    void do_go(ReqGo reqGo);

    /**
     * Handles the "stop" command as part of the Universal Chess Interface (UCI) protocol.
     * This command is sent by the GUI to instruct the chess engine to stop its current search
     * or processing immediately. The engine should halt its computations and return control
     * promptly to handle subsequent commands.
     *
     * @param reqStop The request object representing the "stop" command.
     *                It indicates that the engine should cease its ongoing operations.
     */
    void do_stop(ReqStop reqStop);

    /**
     * Handles the "quit" command as part of the Universal Chess Interface (UCI) protocol.
     * This command is sent by the GUI to instruct the chess engine to terminate its execution.
     * Upon receiving this command, the engine should release any resources and prepare for a safe shutdown.
     *
     * @param reqQuit The request object representing the "quit" command.
     */
    void do_quit(ReqQuit reqQuit);
}
