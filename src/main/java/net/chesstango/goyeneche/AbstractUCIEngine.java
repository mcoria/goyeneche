package net.chesstango.goyeneche;

import net.chesstango.goyeneche.stream.UCIOutputStream;
import net.chesstango.goyeneche.stream.UCIOutputStreamEngineExecutor;

import java.util.function.Consumer;


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
 * @author Mauricio Coria
 * @see UCIService
 * @see UCIEngine
 */
public abstract class AbstractUCIEngine implements UCIService, UCIEngine {

    private final UCIOutputStreamEngineExecutor engineExecutor;

    private Consumer<UCICommand> output;

    public AbstractUCIEngine() {
        this.engineExecutor = new UCIOutputStreamEngineExecutor(this);
    }

    @Override
    public void setUCIOutputStream(UCIOutputStream output) {
        this.output = output;
    }

    @Override
    public void accept(UCICommand command) {
        synchronized (this) {
            engineExecutor.accept(command);
        }
    }

    protected void reply(UCICommand command) {
        output.accept(command);
    }
}
