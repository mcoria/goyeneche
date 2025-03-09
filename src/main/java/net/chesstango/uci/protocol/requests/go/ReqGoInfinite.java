package net.chesstango.uci.protocol.requests.go;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.chesstango.uci.protocol.requests.ReqGoExecutor;
import net.chesstango.uci.protocol.requests.ReqGo;

/**
 * @author Mauricio Coria
 */
@Getter
@Setter
@Accessors(chain = true)
public class ReqGoInfinite extends ReqGo {

    @Override
    public void go(ReqGoExecutor reqGoExecutor) {
        reqGoExecutor.go(this);
    }

    @Override
    public String toString() {
        return "go infinite";
    }
}
