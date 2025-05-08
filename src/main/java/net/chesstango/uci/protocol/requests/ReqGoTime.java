package net.chesstango.uci.protocol.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Mauricio Coria
 */
@Getter
@Setter
@Accessors(chain = true)
public final class ReqGoTime extends ReqGo {

    private int timeOut;

    @Override
    public void execute(ReqGoExecutor reqGoExecutor) {
        reqGoExecutor.go(this);
    }

    @Override
    public String toString() {
        return String.format("go movetime %d", timeOut);
    }
}
