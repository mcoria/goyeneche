package net.chesstango.uci.protocol.requests;

import lombok.Getter;
import net.chesstango.uci.protocol.UCIEngine;

/**
 * @author Mauricio Coria
 */
public class ReqSetOption implements UCIRequest {

    @Getter
    private final String id;

    @Getter
    private final String value;

    public ReqSetOption(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public void execute(UCIEngine executor) {
        executor.do_setOption(this);
    }

    @Override
    public String toString() {
        return value == null ? String.format("setoption name %s", id) : String.format("setoption name %s value %s", id, value);
    }
}
