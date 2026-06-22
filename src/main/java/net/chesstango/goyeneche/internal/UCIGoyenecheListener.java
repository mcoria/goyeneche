package net.chesstango.goyeneche.internal;

import lombok.Getter;
import net.chesstango.goyeneche.UCICommand;
import net.chesstango.goyeneche.internal.antlr4.UCIBaseListener;
import net.chesstango.goyeneche.internal.antlr4.UCIParser;
import net.chesstango.goyeneche.requests.UCIRequest;
import net.chesstango.goyeneche.responses.UCIResponse;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Mauricio Coria
 */
public class UCIGoyenecheListener extends UCIBaseListener {

    @Getter
    private UCICommand command;

    private String setOptionName;
    private String setOptionValue;

    private String fen;
    private List<String> moves;

    private String optionName;
    private String ponderMove;

    private enum OptionType {BUTTON, STRING, SPIN}

    private OptionType optionType;

    private String optionDefaultValue;
    private String optionMinValue;
    private String optionMaxValue;

    private String bestMove;

    private int wTime = 0;

    private int bTime = 0;

    private int wInc = 0;

    private int bInc = 0;

    private Integer movesToGo = null;

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
    public void enterSetoption_name(UCIParser.Setoption_nameContext ctx) {
        setOptionName = ctx.getText();
    }

    @Override
    public void enterSetoption_value(UCIParser.Setoption_valueContext ctx) {
        if (ctx.STRING_EMPTY() != null) {
            setOptionValue = "";
        } else {
            setOptionValue = ctx.getText();
        }
    }

    @Override
    public void exitSetoption(UCIParser.SetoptionContext ctx) {
        command = UCIRequest.setOption(setOptionName, setOptionValue);
    }

    @Override
    public void enterGo_infinite(UCIParser.Go_infiniteContext ctx) {
        command = UCIRequest.goInfinite();
    }

    @Override
    public void exitGo(UCIParser.GoContext ctx) {
        if (command == null) {
            if (wTime > 0 || bTime > 0 || wInc > 0 || bInc > 0 || movesToGo != null) {
                command = UCIRequest.goFast(wTime, wInc, bTime, bInc, movesToGo);
            } else {
                command = UCIRequest.goInfinite();
            }
        }
    }

    @Override
    public void enterGo_depth(UCIParser.Go_depthContext ctx) {
        String depth = ctx.INTEGER().getText();
        command = UCIRequest.goDepth(Integer.parseInt(depth));
    }

    @Override
    public void enterGo_movetime(UCIParser.Go_movetimeContext ctx) {
        String time = ctx.INTEGER().getText();
        command = UCIRequest.goTime(Integer.parseInt(time));
    }

    @Override
    public void enterGo_wtime(UCIParser.Go_wtimeContext ctx) {
        wTime = Integer.parseInt(ctx.INTEGER().getText());
    }

    @Override
    public void enterGo_btime(UCIParser.Go_btimeContext ctx) {
        bTime = Integer.parseInt(ctx.INTEGER().getText());
    }

    @Override
    public void enterGo_winc(UCIParser.Go_wincContext ctx) {
        wInc = Integer.parseInt(ctx.INTEGER().getText());
    }

    @Override
    public void enterGo_binc(UCIParser.Go_bincContext ctx) {
        bInc = Integer.parseInt(ctx.INTEGER().getText());
    }

    @Override
    public void enterGo_movestogo(UCIParser.Go_movestogoContext ctx) {
        movesToGo = Integer.parseInt(ctx.INTEGER().getText());
    }

    @Override
    public void enterFen(UCIParser.FenContext ctx) {
        this.fen = String.format("%s %s %s %s %s %s",
                ctx.STRING(0).getText(),
                ctx.STRING(1).getText(),
                ctx.STRING(2).getText(),
                ctx.STRING(3).getText(),
                ctx.INTEGER(0).getText(),
                ctx.INTEGER(1).getText()
        );
    }

    @Override
    public void enterPosition(UCIParser.PositionContext ctx) {
        moves = new LinkedList<>();
    }

    @Override
    public void exitPosition(UCIParser.PositionContext ctx) {
        command = fen == null
                ? UCIRequest.position(moves)
                : UCIRequest.position(fen, moves);
    }

    @Override
    public void enterMove(UCIParser.MoveContext ctx) {
        moves.add(ctx.getText());
    }

    /**
     * RESPONSES
     */

    @Override
    public void enterUciok(UCIParser.UciokContext ctx) {
        command = UCIResponse.uciok();
    }

    @Override
    public void enterReadyok(UCIParser.ReadyokContext ctx) {
        command = UCIResponse.readyok();
    }

    @Override
    public void enterName(UCIParser.NameContext ctx) {
        command = UCIResponse.idName(getOriginalText(ctx));
    }

    @Override
    public void enterAuthor(UCIParser.AuthorContext ctx) {
        command = UCIResponse.idAuthor(getOriginalText(ctx));
    }

    @Override
    public void enterOptionname(UCIParser.OptionnameContext ctx) {
        optionName = getOriginalText(ctx);
    }

    @Override
    public void enterOptiontype_button(UCIParser.Optiontype_buttonContext ctx) {
        optionType = OptionType.BUTTON;
    }

    @Override
    public void enterOptiontype_string(UCIParser.Optiontype_stringContext ctx) {
        optionType = OptionType.STRING;
        optionDefaultValue = ctx.STRING().getText();
    }

    @Override
    public void enterOptiontype_spin(UCIParser.Optiontype_spinContext ctx) {
        optionType = OptionType.SPIN;
        optionDefaultValue = ctx.INTEGER(0).getText();
        optionMinValue = ctx.INTEGER(1).getText();
        optionMaxValue = ctx.INTEGER(2).getText();
    }

    @Override
    public void exitOption(UCIParser.OptionContext ctx) {
        if (optionType == OptionType.BUTTON) {
            command = UCIResponse.createButtonOption(optionName);
        } else if (optionType == OptionType.STRING) {
            command = UCIResponse.createStringOption(optionName, optionDefaultValue);
        } else if (optionType == OptionType.SPIN) {
            command = UCIResponse.createSpingOption(optionName, optionDefaultValue, optionMinValue, optionMaxValue);
        }
    }

    @Override
    public void enterBest_move(UCIParser.Best_moveContext ctx) {
        bestMove = ctx.getText();
    }

    @Override
    public void enterPoder_move(UCIParser.Poder_moveContext ctx) {
        this.ponderMove = ctx.getText();
    }

    @Override
    public void exitBestmove(UCIParser.BestmoveContext ctx) {
        if (bestMove != null && ponderMove != null) {
            command = UCIResponse.bestMove(bestMove, ponderMove);
        } else if (bestMove != null) {
            command = UCIResponse.bestMove(bestMove);
        }
    }

    private String getOriginalText(ParserRuleContext ctx) {
        // 1. Get the start and stop token indices from the context
        int startIndex = ctx.start.getStartIndex();
        int stopIndex = ctx.stop.getStopIndex();

        // 2. Define the interval using these indices
        Interval interval = new Interval(startIndex, stopIndex);

        // 3. Access the CharStream from the start token
        CharStream input = ctx.start.getInputStream();

        // 4. Retrieve the original text for the defined interval
        return input.getText(interval);

    }

}
