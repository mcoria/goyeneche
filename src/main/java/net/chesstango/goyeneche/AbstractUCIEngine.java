package net.chesstango.goyeneche;

import net.chesstango.goyeneche.requests.*;
import net.chesstango.goyeneche.responses.UCIResponse;
import net.chesstango.goyeneche.stream.UCIOutputStream;
import net.chesstango.goyeneche.stream.UCIOutputStreamEngineExecutor;


/**
 * Represents an abstract implementation of a Universal Chess Interface (UCI) engine.
 *
 * <p>This class serves as a base implementation for UCI-based engines, providing
 * a framework to handle communication with a graphical user interface (GUI).
 * Subclasses can override specific methods to implement engine logic for
 * responding to UCI commands such as `uci`, `isready`, `position`, and others.
 *
 * <p>The class manages an {@link UCIOutputStreamEngineExecutor} for command execution
 * and provides a mechanism for processing responses through an output stream.</p>
 *
 * @see UCIService
 * @see UCIEngine
 *
 * @author Mauricio Coria
 */
public class AbstractUCIEngine implements UCIService, UCIEngine {

    private final UCIOutputStreamEngineExecutor engineExecutor;

    private UCIOutputStream outputStream;

    public AbstractUCIEngine() {
        engineExecutor = new UCIOutputStreamEngineExecutor(this);
    }


    @Override
    public void open() {
    }

    @Override
    public void close() {
    }

    @Override
    public void setOutputStream(UCIOutputStream output) {
        this.outputStream = output;
    }

    @Override
    public void accept(UCICommand command) {
        synchronized (this) {
            engineExecutor.accept(command);
        }
    }

    public void replyResponse(UCIResponse response) {
        outputStream.accept(response);
    }
}
