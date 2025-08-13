package net.chesstango.goyeneche;

import java.io.Serializable;

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
 * between tokens is ignored. Commands are categorized broadly into requests and responses.</p>
 *
 * <p>
 * Key components of the UCI protocol from which this interface derives relevance include:
 * </p>
 * <ul>
 *   <li>Requests from the GUI (e.g., `uci`, `debug`, `isready`, `position`, and others).</li>
 *   <li>Responses from the chess engine (e.g., `uciok`, `readyok`, move outputs, etc.).</li>
 *   <li>Handling of unknown or unexpected commands gracefully by ignoring them.</li>
 * </ul>
 *
 * @author Mauricio Coria
 * @see <a href="http://download.shredderchess.com/div/uci.zip">UCI Protocol Specification</a>
 */

public interface UCICommand extends Serializable {
}
