package net.chesstango.goyeneche.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Mauricio Coria
 */
@Getter
@Setter
@Accessors(chain = true)
public final class ReqGoInfinite extends ReqGo {

    static final ReqGoInfinite INSTANCE = new ReqGoInfinite();

    private ReqGoInfinite() {
    }

    @Override
    public void execute(ReqGoExecutor reqGoExecutor) {
        reqGoExecutor.go(this);
    }

    @Override
    public String toString() {
        return "go infinite";
    }
}
