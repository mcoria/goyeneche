package net.chesstango.uci.protocol.internal.antlr4;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.requests.ReqPosition;
import net.chesstango.uci.protocol.requests.UCIRequest;
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
    public UCICommand visitRequest(UciParser.RequestContext ctx) {
        Token firstToken = ctx.getStart();

        String firstTokenText = firstToken.getText();

        if ("uci".equals(firstTokenText)) {
            return UCIRequest.uci();
        } else if ("isready".equals(firstTokenText)) {
            return UCIRequest.isready();
        } else if ("ucinewgame".equals(firstTokenText)) {
            return UCIRequest.ucinewgame();
        } else if ("position".equals(firstTokenText)) {
            return visitPosition(ctx.position());
        } else if ("stop".equals(firstTokenText)) {
            return UCIRequest.stop();
        } else if ("quit".equals(firstTokenText)) {
            return UCIRequest.quit();
        }

        throw new UnsupportedOperationException("Unsupported command: " + ctx.getText());
    }

    @Override
    public UCICommand visitResponse(UciParser.ResponseContext ctx) {
        Token firstToken = ctx.getStart();

        String firstTokenText = firstToken.getText();

        if ("uciok".equals(firstTokenText)) {
            return UCIResponse.uciok();
        } else if ("readyok".equals(firstTokenText)) {
            return UCIResponse.readyok();
        }

        throw new UnsupportedOperationException("Unsupported command: " + ctx.getText());
    }


    @Override
    public ReqPosition visitPosition(UciParser.PositionContext positionparams) {
        Token firstToken = positionparams.getStart();

        String firstTokenText = firstToken.getText();

        List<String> moves = decodeMoves(positionparams.moves());

        if ("startpos".equals(firstTokenText)) {
            return UCIRequest.position(moves);
        } else if ("fen".equals(firstTokenText)) {
            UciParser.FenContext fenCtx = positionparams.fen();
            Token fenStart = fenCtx.getStart();
            Token fenStop = fenCtx.getStop();
            String fenText = fenStart.getInputStream().getText(Interval.of(fenStart.getStartIndex(), fenStop.getStopIndex()));
            return UCIRequest.position(fenText, moves);
        }

        throw new UnsupportedOperationException("Unsupported positionparams: " + positionparams.getText());
    }


    private List<String> decodeMoves(UciParser.MovesContext movesCtx) {
        if (movesCtx == null) {
            return Collections.emptyList();
        }

        return movesCtx.STRING()
                .stream()
                .map(TerminalNode::getText)
                .toList();
    }
}
