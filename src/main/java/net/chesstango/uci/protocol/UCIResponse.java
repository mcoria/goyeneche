package net.chesstango.uci.protocol;

/**
 * The UCIResponse interface represents response commands sent from the chess engine to the GUI
 * as part of the Universal Chess Interface (UCI) protocol.
 * <p>
 * A UCIResponse is categorized by its type, which indicates the nature of the response.
 * Implementations of this interface must define the specific behavior and representation
 * of each response type.
 * <p>
 * Response types include:
 * - ID: Provides identification details such as the engine name or author.
 * - UCIOK: Indicates the engine has successfully recognized the UCI command.
 * - READYOK: Indicates the engine is ready to process further commands.
 * - INFO: Provides informational updates about the engine's analysis.
 * - BESTMOVE: Indicates the best move calculated by the engine, and optionally, a ponder move.
 * <p>
 * Each response can be executed on a UCIGui instance to process or handle the response appropriately.
 * The concrete implementation of this interface determines the specific execution logic.
 *
 * @author Mauricio Coria
 */
public interface UCIResponse extends UCICommand {
    /**
     * Executes the response using the provided UCIGui instance.
     *
     * @param executor The UCIGui instance that will execute the response.
     */
    void execute(UCIGui executor);
}
