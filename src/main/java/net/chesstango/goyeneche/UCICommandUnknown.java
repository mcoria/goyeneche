package net.chesstango.goyeneche;

import lombok.Getter;

/**
 * @author Mauricio Coria
 */
public class UCICommandUnknown implements UCICommand {

    @Getter
    private final String text;

    public UCICommandUnknown(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
