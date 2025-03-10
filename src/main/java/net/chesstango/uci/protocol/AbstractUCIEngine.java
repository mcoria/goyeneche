package net.chesstango.uci.protocol;

import net.chesstango.uci.protocol.requests.*;
import net.chesstango.uci.protocol.stream.UCIOutputStream;
import net.chesstango.uci.protocol.stream.UCIOutputStreamEngineExecutor;


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
    public void do_uci(ReqUci reqUci) {

    }

    @Override
    public void do_setOption(ReqSetOption reqSetOption) {

    }

    @Override
    public void do_isReady(ReqIsReady reqIsReady) {

    }

    @Override
    public void do_newGame(ReqUciNewGame reqUciNewGame) {

    }

    @Override
    public void do_position(ReqPosition reqPosition) {

    }

    @Override
    public void do_go(ReqGo reqGo) {

    }

    @Override
    public void do_stop(ReqStop reqStop) {

    }

    @Override
    public void do_quit(ReqQuit reqQuit) {

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
