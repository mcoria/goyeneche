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

    private String optionName;
    private String optionValue;

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


    @Override
    public void enterOptionname(UCIParser.OptionnameContext ctx) {
        optionName = ctx.getText();
    }

    @Override
    public void enterOptionvalue(UCIParser.OptionvalueContext ctx) {
        optionValue = ctx.getText();
    }

    @Override
    public void exitSetoption(UCIParser.SetoptionContext ctx) {
        command = UCIRequest.setOption(optionName, optionValue);
    }

    @Override
    public void enterGo_infinite(UCIParser.Go_infiniteContext ctx) {
        command = UCIRequest.goInfinite();
    }

    @Override
    public void exitGo(UCIParser.GoContext ctx) {
        if (command == null) {
            command = UCIRequest.goInfinite();
        }
    }

    @Override
    public void enterGo_depth(UCIParser.Go_depthContext ctx) {
        String depth = ctx.STRING().getText();
        command = UCIRequest.goDepth(Integer.parseInt(depth));
    }

    @Override
    public void enterGo_movetime(UCIParser.Go_movetimeContext ctx) {
        String time = ctx.STRING().getText();
        command = UCIRequest.goTime(Integer.parseInt(time));
    }

}
