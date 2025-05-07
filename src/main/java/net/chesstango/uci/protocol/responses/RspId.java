package net.chesstango.uci.protocol.responses;

import lombok.Getter;
import net.chesstango.uci.protocol.UCIGui;

/**
 * @author Mauricio Coria
 */
public class RspId implements UCIResponse {

    public enum RspIdType {NAME, AUTHOR}

    private final RspIdType type;

    @Getter
    private final String text;

    public RspId(RspIdType type, String text) {
        this.type = type;
        this.text = text;
    }

    public RspIdType getIdType() {
        return type;
    }

    @Override
    public void execute(UCIGui executor) {
        executor.do_id(this);
    }

    @Override
    public String toString() {
        return "id " + (RspIdType.AUTHOR.equals(type) ? "author " : "name ") + getText();
    }

}
