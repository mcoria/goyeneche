package net.chesstango.goyeneche.internal;

import lombok.Getter;
import net.chesstango.goyeneche.UCICommand;
import net.chesstango.goyeneche.internal.antlr4.UCIBaseListener;
import net.chesstango.goyeneche.internal.antlr4.UCIParser;
import net.chesstango.goyeneche.requests.UCIRequest;

/**
 * @author Mauricio Coria
 */
public class UCIGoyenecheListener extends UCIBaseListener {

    @Getter
    private UCICommand command;

    @Override
    public void enterUci(UCIParser.UciContext ctx) {
        command = UCIRequest.uci();
    }

    @Override
    public void enterIsready(UCIParser.IsreadyContext ctx) {
        command = UCIRequest.isready();
    }

    @Override
    public void enterUcinewgame(UCIParser.UcinewgameContext ctx) {
        command = UCIRequest.ucinewgame();
    }

    @Override
    public void enterStop(UCIParser.StopContext ctx) {
        command = UCIRequest.stop();
    }

    @Override
    public void enterQuit(UCIParser.QuitContext ctx) {
        command = UCIRequest.quit();
    }


}
