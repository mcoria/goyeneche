package net.chesstango.uci.protocol;

/**
 * @author Mauricio Coria
 */
public class UCICommandUnknown implements UCICommand {

    private final String line;

    public UCICommandUnknown(String line) {
        this.line = line;
    }


    @Override
    public String toString() {
        return line;
    }
}
