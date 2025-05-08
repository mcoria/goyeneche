package net.chesstango.uci.protocol.internal.antlr4;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.requests.ReqGo;
import net.chesstango.uci.protocol.requests.ReqPosition;
import net.chesstango.uci.protocol.requests.ReqSetOption;
import net.chesstango.uci.protocol.requests.UCIRequest;
import net.chesstango.uci.protocol.responses.RspId;
import net.chesstango.uci.protocol.responses.RspOption;
import net.chesstango.uci.protocol.responses.UCIResponse;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Collections;
import java.util.List;

/**
 * @author Mauricio Coria
 */
public class TangoUciVisitor extends UciBaseVisitor<UCICommand> {

    @Override
    public UCICommand visitCommand(UciParser.CommandContext ctx) {
        UciParser.RequestContext requestCtx = ctx.request();
        UciParser.ResponseContext responseCtx = ctx.response();
        return requestCtx != null ? visitRequest(requestCtx) : responseCtx != null ? visitResponse(responseCtx) : null;
    }

    @Override
    public UCIRequest visitRequest(UciParser.RequestContext ctx) {
        Token firstToken = ctx.getStart();
        String firstTokenText = firstToken.getText();

        if ("uci".equals(firstTokenText)) {
            return UCIRequest.uci();
        } else if ("isready".equals(firstTokenText)) {
            return UCIRequest.isready();
        } else if ("setoption".equals(firstTokenText)) {
            return visitSetoption(ctx.setoption());
        } else if ("ucinewgame".equals(firstTokenText)) {
            return UCIRequest.ucinewgame();
        } else if ("position".equals(firstTokenText)) {
            return visitPosition(ctx.position());
        } else if ("go".equals(firstTokenText)) {
            return visitGo(ctx.go());
        } else if ("stop".equals(firstTokenText)) {
            return UCIRequest.stop();
        } else if ("quit".equals(firstTokenText)) {
            return UCIRequest.quit();
        }

        throw new UnsupportedOperationException("Unsupported command: " + firstTokenText);
    }

    @Override
    public ReqSetOption visitSetoption(UciParser.SetoptionContext ctx) {
        String optionName = ctx.optionname().STRING().getText();
        if (ctx.STRING() != null) {
            String optionValue = ctx.STRING().getText();
            return UCIRequest.setOption(optionName, optionValue);
        }
        return UCIRequest.setOption(optionName);
    }

    @Override
    public ReqPosition visitPosition(UciParser.PositionContext positionparams) {
        Token firstToken = positionparams.getStart();
        String firstTokenText = firstToken.getText();

        List<String> moves = decodeMoves(positionparams.move());

        if ("startpos".equals(firstTokenText)) {
            return UCIRequest.position(moves);
        } else if ("fen".equals(firstTokenText)) {
            UciParser.FenContext fenCtx = positionparams.fen();
            Token fenStart = fenCtx.getStart();
            Token fenStop = fenCtx.getStop();
            String fenText = fenStart.getInputStream().getText(Interval.of(fenStart.getStartIndex(), fenStop.getStopIndex()));
            return UCIRequest.position(fenText, moves);
        }

        throw new UnsupportedOperationException("Unsupported command: " + firstTokenText);
    }

    @Override
    public ReqGo visitGo(UciParser.GoContext goCtx) {
        if (goCtx == null) {
            return UCIRequest.go();
        }

        Token firstToken = goCtx.getStart();
        String firstTokenText = firstToken.getText();

        if ("infinite".equals(firstTokenText)) {
            return UCIRequest.go();
        } else if ("depth".equals(firstTokenText)) {
            int depth = Integer.parseInt(goCtx.depthparam().STRING().getText());
            return UCIRequest.goDepth(depth);
        } else if ("movetime".equals(firstTokenText)) {
            int depth = Integer.parseInt(goCtx.movetime().STRING().getText());
            return UCIRequest.goTime(depth);
        }

        throw new UnsupportedOperationException("Unsupported go command: " + firstTokenText);
    }

    @Override
    public UCIResponse visitResponse(UciParser.ResponseContext ctx) {
        Token firstToken = ctx.getStart();
        String firstTokenText = firstToken.getText();

        if ("id".equals(firstTokenText)) {
            return visitId(ctx.id());
        } else if ("uciok".equals(firstTokenText)) {
            return UCIResponse.uciok();
        } else if ("readyok".equals(firstTokenText)) {
            return UCIResponse.readyok();
        } else if ("bestmove".equals(firstTokenText)) {
            List<UciParser.MoveContext> moves = ctx.move();
            if (moves.size() == 1) {
                return UCIResponse.bestMove(moves.getFirst().STRING().getText());
            } else if (moves.size() == 2) {
                return UCIResponse.bestMove(moves.getFirst().STRING().getText(), moves.get(1).STRING().getText());
            }
        } else if ("option".equals(firstTokenText)) {
            return visitOption(ctx.option());
        }

        throw new UnsupportedOperationException("Unsupported command: " + firstTokenText);
    }


    @Override
    public RspId visitId(UciParser.IdContext idCtx) {
        Token firstToken = idCtx.getStart();
        String firstTokenText = firstToken.getText();

        if ("author".equals(firstTokenText)) {
            UciParser.AuthorContext authorCtx = idCtx.author();
            Token start = authorCtx.getStart();
            Token stop = authorCtx.getStop();
            String text = start.getInputStream().getText(Interval.of(start.getStartIndex(), stop.getStopIndex()));
            return UCIResponse.idAuthor(text);
        } else if ("name".equals(firstTokenText)) {
            UciParser.NameContext nameCtx = idCtx.name();
            Token start = nameCtx.getStart();
            Token stop = nameCtx.getStop();
            String text = start.getInputStream().getText(Interval.of(start.getStartIndex(), stop.getStopIndex()));
            return UCIResponse.idName(text);
        }

        throw new UnsupportedOperationException("Unsupported command: " + firstTokenText);
    }

    @Override
    public RspOption visitOption(UciParser.OptionContext ctx) {
        return visitOptiontype(ctx.optiontype());
    }

    @Override
    public RspOption visitOptiontype(UciParser.OptiontypeContext ctx) {
        Token firstToken = ctx.getStart();
        String firstTokenText = firstToken.getText();

        UciParser.OptionContext optionContext = (UciParser.OptionContext) ctx.getParent();
        String name = optionContext.optionname().STRING().getText();

        if ("string".equals(firstTokenText)) {
            String defaultValue = ctx.STRING().getText();
            return UCIResponse.createStringOption(name, defaultValue);
        }

        throw new UnsupportedOperationException("Unsupported option: " + firstTokenText);
    }

    private List<String> decodeMoves(List<UciParser.MoveContext> moveCtxList) {
        if (moveCtxList == null) {
            return Collections.emptyList();
        }

        return moveCtxList
                .stream()
                .map(UciParser.MoveContext::STRING)
                .map(TerminalNode::getText)
                .toList();
    }
}
