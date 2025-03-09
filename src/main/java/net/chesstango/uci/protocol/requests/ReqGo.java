package net.chesstango.uci.protocol.requests;

import net.chesstango.uci.protocol.UCIEngine;
import net.chesstango.uci.protocol.UCIRequest;

/**
 * @author Mauricio Coria
 */
public abstract class ReqGo implements UCIRequest {

    public abstract void go(ReqGoExecutor reqGoExecutor);

    @Override
    public final MessageType getMessageType() {
        return MessageType.Request;
    }

    @Override
    public final UCIRequestType getRequestType() {
        return UCIRequestType.GO;
    }

    @Override
    public final void execute(UCIEngine executor) {
        executor.do_go(this);
    }
}
