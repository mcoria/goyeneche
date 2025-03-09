package net.chesstango.uci.protocol.requests.go;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.chesstango.uci.protocol.requests.ReqGoExecutor;
import net.chesstango.uci.protocol.requests.ReqGo;

/**
 * Fast chess: https://en.wikipedia.org/wiki/Fast_chess
 *
 * @author Mauricio Coria
 */
@Getter
@Setter
@Accessors(chain = true)
public class ReqGoFast extends ReqGo {

    private int wTime;

    private int wInc;

    private int bTime;

    private int bInc;

    @Override
    public void go(ReqGoExecutor reqGoExecutor) {
        reqGoExecutor.go(this);
    }

    @Override
    public String toString() {
        return String.format("go wtime %d btime %d winc %d binc %d", wTime, bTime, wInc, bInc);
    }
}
