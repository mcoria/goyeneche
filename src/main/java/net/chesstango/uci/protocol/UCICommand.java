package net.chesstango.uci.protocol;

/**
 * Represents a command in the Universal Chess Interface (UCI) protocol.
 *
 * <p>The UCI protocol facilitates communication between a chess GUI (Graphical User Interface)
 * and a chess engine, providing a standardized way to exchange information such as commands, responses,
 * and other data. This interface defines the base structure for UCI commands, categorizing them by
 * their type and enabling identification of messages exchanged between the engine and GUI.</p>
 *
 * <p>The protocol operates via text-based commands transmitted through standard input and output,
 * ensuring platform independence. Commands must always end with a line feed (`\n`) and whitespace
 * between tokens is ignored. Commands are categorized broadly into requests and responses as defined
 * in this interface.</p>
 *
 * <p>
 * Key components of the UCI protocol from which this interface derives relevance include:
 * </p>
 * <ul>
 *   <li>Commands from the GUI (e.g., `uci`, `debug`, `isready`, `position`, and others).</li>
 *   <li>Responses or messages from the chess engine (e.g., `uciok`, `readyok`, move outputs, etc.).</li>
 *   <li>Handling of unknown or unexpected commands gracefully by ignoring them.</li>
 * </ul>
 *
 * @see <a href="http://download.shredderchess.com/div/uci.zip">UCI Protocol Specification</a>
 *
 * @author Mauricio Coria
 */

public interface UCICommand {

    /**
     * Enumerates the possible types of messages within the UCI protocol.
     *
     * <ul>
     *   <li>{@code Request} - Represents a command sent from the GUI to the engine
     *       (e.g., initialization commands like {@code uci}, or instructions such as {@code isready}).</li>
     *   <li>{@code Response} - Represents a response or message sent from the engine to the GUI
     *       (e.g., {@code uciok}, {@code readyok}, or search results).</li>
     *   <li>{@code Unknown} - Represents a message type that cannot be categorized as a {@code Request} or {@code Response}.</li>
     * </ul>
     */
    enum MessageType {
        Request,
        Response,
        Unknown,
    }

    /**
     * Retrieves the type of message represented by this command.
     *
     * <p>This method classifies the command into one of the defined categories:
     * {@link MessageType#Request}, {@link MessageType#Response}, or {@link MessageType#Unknown}.
     * Knowing the message type aids in the processing and handling of UCI commands,
     * facilitating proper communication between the GUI and the chess engine.</p>
     *
     * @return the {@link MessageType} of this command.
     */
    MessageType getMessageType();
}
