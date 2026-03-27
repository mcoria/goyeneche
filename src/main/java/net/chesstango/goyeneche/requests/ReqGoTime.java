package net.chesstango.goyeneche.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @author Mauricio Coria
 */
@Getter
@Setter
@Accessors(chain = true)
public final class ReqGoTime extends ReqGo {

    private int timeOut;

    ReqGoTime(int timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public void execute(ReqGoExecutor reqGoExecutor) {
        reqGoExecutor.go(this);
    }

    @Override
    public String toString() {
        return String.format("go movetime %d", timeOut);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReqGoTime reqGoTime)) return false;
        return timeOut == reqGoTime.timeOut;
    }

    @Override
    public int hashCode() {
        return timeOut;
    }
}
