package net.chesstango.goyeneche.responses;

import net.chesstango.goyeneche.UCIGui;

/**
 * @author Mauricio Coria
 */
public final class RspInfo implements UCIResponse {

    private final String info;

    RspInfo(String info){
        this.info = info;
    }

    @Override
    public void execute(UCIGui executor) {
        executor.do_info(this);
    }

    @Override
    public String toString() {
        return "info " + info;
    }
}
