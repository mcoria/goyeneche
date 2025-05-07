package net.chesstango.uci.protocol.requests;

import net.chesstango.uci.protocol.UCIEngine;

/**
 * @author Mauricio Coria
 */
public abstract class ReqGo implements UCIRequest {


    /**
     * Executes the specific behavior of this `ReqGo` request based on the provided executor.
     *
     * <p>This method delegates the implementation-specific details of the `go` command
     * to the given {@link ReqGoExecutor}, enabling flexibility in handling variations
     * of the `ReqGo` command.</p>
     *
     * @param reqGoExecutor The executor responsible for processing the `ReqGo` command.
     */
    public abstract void execute(ReqGoExecutor reqGoExecutor);

    @Override
    public final void execute(UCIEngine executor) {
        executor.do_go(this);
    }
}
