package net.chesstango.goyeneche.stream;

import net.chesstango.goyeneche.UCICommand;
import net.chesstango.goyeneche.UCIGui;
import net.chesstango.goyeneche.responses.UCIResponse;

/**
 * @author Mauricio Coria
 */
public class UCIOutputStreamGuiExecutor implements UCIOutputStream {

    private final UCIGui executor;

    public UCIOutputStreamGuiExecutor(UCIGui executor) {
        this.executor = executor;
    }

    @Override
    public void accept(UCICommand uciCommand) {
        if (uciCommand instanceof UCIResponse response) {
            response.execute(executor);
        }
    }
}
