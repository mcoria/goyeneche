package net.chesstango.uci.protocol;

/**
 * The UCIRequest interface represents a command request sent to a chess engine
 * as part of the Universal Chess Interface (UCI) protocol. Implementations of this
 * interface encapsulate specific commands defined by the protocol and provide
 * the necessary information and functionality to process these commands.
 * <p>
 * Implementing classes must specify the type of request they represent and
 * provide an implementation to execute their corresponding operation with
 * a UCIEngine instance.
 *
 * @author Mauricio Coria
 */
public interface UCIRequest extends UCICommand {

    enum UCIRequestType {
        UCI, QUIT, ISREADY, STOP, GO, SETOPTION, UCINEWGAME, POSITION
    }

    @Override
    default MessageType getMessageType() {
        return MessageType.Request;
    }

    UCIRequestType getRequestType();

    void execute(UCIEngine executor);
}
