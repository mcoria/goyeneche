package net.chesstango.uci.protocol.requests;

import net.chesstango.uci.protocol.requests.go.ReqGoFast;
import net.chesstango.uci.protocol.requests.go.ReqGoDepth;
import net.chesstango.uci.protocol.requests.go.ReqGoInfinite;
import net.chesstango.uci.protocol.requests.go.ReqGoTime;

/**
 * @author Mauricio Coria
 */
public interface ReqGoExecutor {
    void go(ReqGoInfinite cmdGoInfinite);

    void go(ReqGoDepth cmdGoDepth);

    void go(ReqGoTime cmdGoTime);

    void go(ReqGoFast cmdGoFast);
}
