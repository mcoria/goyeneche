package net.chesstango.uci.protocol;

import net.chesstango.uci.protocol.stream.UCIOutputStream;

import java.util.function.Consumer;


/**
 * Represents a service that facilitates communication using the Universal Chess Interface (UCI) protocol.
 *
 * <p>
 * This interface is designed to handle the exchange of messages between a chess engine and a GUI, or vice versa,
 * depending on the implementation. It acts as the bridge for processing UCI commands, communicating responses,
 * and managing the lifecycle of the service.
 * </p>
 *
 * <p>
 * Typical implementations could represent a chess engine that processes UCI commands from a GUI (Graphical User Interface)
 * or a GUI controller that interacts with a chess engine. By consuming {@code UCICommand} objects, the service interprets
 * and acts upon the received instructions while allowing responses to be sent back via an output stream.
 * </p>
 *
 * <h2>Responsibilities</h2>
 * <ul>
 *   <li><b>Message Processing:</b> Implements the {@link java.util.function.Consumer Consumer} interface to handle
 *   UCI protocol messages ({@code UCICommand}) sent to the service.</li>
 *
 *   <li><b>Resource Management:</b> Offers lifecycle methods, {@link #open()} and {@link #close()}, which are used to
 *   initialize or release required resources (e.g., connections, streams, or engine processes).</li>
 *
 *   <li><b>Response Handling:</b> Provides a method to direct responses to an output stream using
 *   {@link #setOutputStream(UCIOutputStream)}. This allows flexibility in redirecting responses to a specific target.</li>
 * </ul>
 *
 * <h2>Usage Scenarios</h2>
 * <p>
 * This interface can be implemented in various ways:
 * </p>
 * <ol>
 *   <li><b>Chess Engine:</b> A chess engine can implement {@code UCIService} to process commands such as
 *   {@code ucinewgame}, {@code position}, or {@code go}, and to send responses like {@code bestmove} and {@code info}
 *   back to the GUI.</li>
 *
 *   <li><b>GUI Controller:</b> A GUI controller can implement {@code UCIService} to interact with a chess engine by sending
 *   commands and receiving responses, enabling the GUI to present move suggestions, evaluations, or other engine-provided details.</li>
 * </ol>
 *
 * <h2>Interface Methods</h2>
 * <ul>
 *   <li>{@link #open()}: Prepares the service for use and initializes any required resources.</li>
 *   <li>{@link #close()}: Cleans up and releases resources previously initialized by the service.</li>
 *   <li>{@link #setOutputStream(UCIOutputStream)}: Configures the output stream used to send commands from the service.</li>
 *   <li>{@link java.util.function.Consumer#accept(Object)}: Processes individual UCI protocol commands passed to the service.</li>
 * </ul>
 *
 * <h2>Design Considerations</h2>
 * <p>
 * The {@code UCIService} interface is intentionally minimalistic and abstract, focusing solely on the core operations
 * required for UCI protocol communication. This abstraction allows implementations to be tailored for both chess engines
 * and GUI controllers, enhancing reusability and adaptability in UCI-based chess systems.
 * </p>
 *
 * @author Mauricio Coria
 */

public interface UCIService extends Consumer<UCICommand> {
    void open();

    void close();

    void setOutputStream(UCIOutputStream output);
}
