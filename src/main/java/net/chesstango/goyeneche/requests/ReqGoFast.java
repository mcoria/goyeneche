package net.chesstango.goyeneche.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Fast chess: https://en.wikipedia.org/wiki/Fast_chess
 *
 * @author Mauricio Coria
 */
@Getter
@Accessors(chain = true)
public final class ReqGoFast extends ReqGo {

    private final int wTime;

    private final int wInc;

    private final int bTime;

    private final int bInc;

    ReqGoFast(int wTime, int wInc, int bTime, int bInc) {
        this.wTime = wTime;
        this.wInc = wInc;
        this.bTime = bTime;
        this.bInc = bInc;
    }

    @Override
    public void execute(ReqGoExecutor reqGoExecutor) {
        reqGoExecutor.go(this);
    }

    @Override
    public String toString() {
        return String.format("go wtime %d btime %d winc %d binc %d", wTime, bTime, wInc, bInc);
    }
}
