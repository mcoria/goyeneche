package net.chesstango.goyeneche.requests;

import lombok.Getter;
import net.chesstango.goyeneche.UCIEngine;

/**
 * @author Mauricio Coria
 */
public final class ReqSetOption implements UCIRequest {

    @Getter
    private final String id;

    @Getter
    private final String value;

    ReqSetOption(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public void execute(UCIEngine executor) {
        executor.do_setOption(this);
    }

    @Override
    public String toString() {
        if (value == null) {
            return String.format("setoption name %s", id);
        }
        return value.isEmpty()
                ? String.format("setoption name %s value <empty>", id)
                : String.format("setoption name %s value %s", id, value);
    }
}
