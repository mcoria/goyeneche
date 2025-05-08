package net.chesstango.uci.protocol.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Fast chess: https://en.wikipedia.org/wiki/Fast_chess
 *
 * @author Mauricio Coria
 */
@Getter
@Setter
@Accessors(chain = true)
public final class ReqGoFast extends ReqGo {

    private int wTime;

    private int wInc;

    private int bTime;

    private int bInc;

    @Override
    public void execute(ReqGoExecutor reqGoExecutor) {
        reqGoExecutor.go(this);
    }

    @Override
    public String toString() {
        return String.format("go wtime %d btime %d winc %d binc %d", wTime, bTime, wInc, bInc);
    }
}
