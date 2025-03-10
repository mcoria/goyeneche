package net.chesstango.uci.protocol;

import net.chesstango.uci.protocol.requests.ReqGo;
import net.chesstango.uci.protocol.requests.ReqGoExecutor;
import net.chesstango.uci.protocol.requests.ReqIsReady;
import net.chesstango.uci.protocol.requests.ReqUci;
import net.chesstango.uci.protocol.requests.go.ReqGoDepth;
import net.chesstango.uci.protocol.requests.go.ReqGoFast;
import net.chesstango.uci.protocol.responses.RspBestMove;
import net.chesstango.uci.protocol.responses.RspId;
import net.chesstango.uci.protocol.responses.RspReadyOk;
import net.chesstango.uci.protocol.responses.RspUciOk;
import net.chesstango.uci.protocol.stream.UCIActiveStreamReader;
import net.chesstango.uci.protocol.stream.UCIInputStreamFromStringAdapter;
import net.chesstango.uci.protocol.stream.UCIOutputStreamToStringAdapter;
import net.chesstango.uci.protocol.stream.strings.StringConsumer;
import net.chesstango.uci.protocol.stream.strings.StringSupplier;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.function.Consumer;


/**
 * The EngineSkeleton class implements an abstract UCI engine (Universal Chess Interface).
 * It demonstrates the basic structure of a UCI chess engine, handling communication
 * with a chess GUI via input/output streams.
 *
 * <p>The class includes implementations for standard UCI commands and a framework
 * to handle them efficiently, making it a "skeleton" or template for building
 * a more feature-complete chess engine.</p>
 *
 * <p>Override methods like {@link #do_uci(ReqUci)} or {@link #do_isReady(ReqIsReady)}
 * as needed to provide additional functionality.</p>
 *
 * @author Mauricio Coria
 * @see AbstractUCIEngine
 */
public class EngineSkeleton extends AbstractUCIEngine {

    /**
     * The main method serves as the entry point for the chess engine,
     * enabling interaction with a GUI through the UCI (Universal Chess Interface) protocol.
     *
     * <p>This method initializes the engine, configures stream adapters for input and output,
     * and starts a loop to continuously listen for GUI commands, ensuring the engine's responsiveness.</p>
     */
    /**
     * Entry point for running the EngineSkeleton application.
     *
     * <p>This method initializes the engine, sets up communication with the GUI,
     * and starts a loop to process UCI commands. The program will continue
     * running until it receives a termination command, as specified by the UCI protocol.</p>
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {

        // Create an instance of the EngineSkeleton to handle UCI commands.
        EngineSkeleton engineSkeleton = new EngineSkeleton();

        // Configure the engine's output stream to forward responses to the GUI via standard output,
        // using a StringConsumer for protocol-compliant communication.
        engineSkeleton.setOutputStream(new UCIOutputStreamToStringAdapter(new StringConsumer(new OutputStreamWriter(System.out))));

        // Open the engine to prepare it for communication with the GUI.
        engineSkeleton.open();

        // Initialize and set up the UCIActiveStreamReader to read UCI commands from standard input.
        UCIActiveStreamReader uciActiveStreamReader = createUciActiveStreamReader(engineSkeleton);

        // Start processing UCI commands in the active reader loop.
        uciActiveStreamReader.run();

        // Close the engine after completing the communication.
        engineSkeleton.close();
    }


    /**
     * Creates and configures an instance of {@link UCIActiveStreamReader} to handle the input and output
     * streams required for UCI protocol communication between the chess engine and GUI.
     *
     * <p>The input stream is sourced from standard input ({@link System#in}), which is adapted for compatibility
     * using a {@link UCIInputStreamFromStringAdapter} and a {@link StringSupplier}. The output stream is configured
     * to direct messages to the {@code accept} method of the given {@link EngineSkeleton}, enabling it to process
     * received UCI commands.</p>
     *
     * @param uciCommandConsumer The consumer instance that will handle the UCI commands.
     * @return A configured {@link UCIActiveStreamReader} instance ready to read UCI communication.
     */
    private static UCIActiveStreamReader createUciActiveStreamReader(Consumer<UCICommand> uciCommandConsumer) {
        UCIActiveStreamReader uciActiveStreamReader = new UCIActiveStreamReader();

        // Configures the active stream reader to read input from the standard input (System.in)
        // by adapting it to the UCIInputStream interface through a StringSupplier.
        uciActiveStreamReader.setInputStream(new UCIInputStreamFromStringAdapter(new StringSupplier(new InputStreamReader(System.in))));

        // Directs the output of the active stream reader to be handled by the engineSkeleton's accept method,
        // enabling the engine to process UCI commands received from the input.
        uciActiveStreamReader.setOutputStream(uciCommandConsumer::accept);

        // Return the configured UCIActiveStreamReader instance to handle communication with the GUI.
        return uciActiveStreamReader;
    }

    /**
     * Handles the `uci` command, sending the engine's name and author to the GUI.
     *
     * @param reqUci The UCI request command instance.
     */
    @Override
    public void do_uci(ReqUci reqUci) {
        replyResponse(new RspId(RspId.RspIdType.NAME, "Skeleton 1.0"));
        replyResponse(new RspId(RspId.RspIdType.AUTHOR, "John Doe"));
        replyResponse(new RspUciOk());
    }

    /**
     * Handles the `isready` command by replying with "readyok", indicating the engine is ready.
     *
     * @param cmdIsReady The UCI request command instance.
     */
    @Override
    public void do_isReady(ReqIsReady cmdIsReady) {
        replyResponse(new RspReadyOk());
    }


    /**
     * Handles the `go` command, instructing the engine to start searching for the best move.
     *
     * <p>This implementation immediately responds with a static best move, "c2c4",
     * without performing an actual search. In a realistic implementation, this method
     * would initiate the search process and return the computed best move.</p>
     *
     * <p>To add flexibility for different types of `go` commands (e.g., depth-limited, time-limited),
     * the {@link ReqGoExecutor} interface can be leveraged. By passing a concrete implementation of
     * `ReqGoExecutor` to the `execute` method of the given `ReqGo` instance, behavior can be tailored
     * dynamically based on the specific subclass of `ReqGo` (e.g., {@link ReqGoDepth}, {@link ReqGoFast}).</p>
     *
     * <p>Example:
     * <pre>{@code
     *     ReqGoExecutor reqGoExecutor = new MyCustomReqGoExecutor();
     *     reqGo.execute(reqGoExecutor);
     * }</pre>
     * This allows the engine to dynamically process different kinds of `go` commands without
     * hardcoding them in this method.</p>
     *
     * @param reqGo The UCI go command instance, containing parameters for the search, such as depth or time.
     */
    @Override
    public void do_go(ReqGo reqGo) {
        replyResponse(new RspBestMove("c2c4"));
    }
}
