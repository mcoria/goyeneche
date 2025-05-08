package net.chesstango.uci.protocol.internal.antlr4;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.requests.*;

import java.util.ArrayList;
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
        UciParser.UciContext uciCtx = ctx.uci();
        UciParser.IsreadyContext isReadyCtx = ctx.isready();
        UciParser.UcinewgameContext uciNewGameCtx = ctx.ucinewgame();
        UciParser.PositionContext positionCtx = ctx.position();
        UciParser.StopContext stopCtx = ctx.stop();
        UciParser.QuitContext quitCtx = ctx.quit();

        if (uciCtx != null) {
            return visitUci(uciCtx);
        } else if (isReadyCtx != null) {
            return visitIsready(isReadyCtx);
        } else if (uciNewGameCtx != null) {
            return visitUcinewgame(uciNewGameCtx);
        } else if (stopCtx != null) {
            return visitStop(stopCtx);
        } else if (quitCtx != null) {
            return visitQuit(quitCtx);
        } else if (positionCtx != null) {
            return visitPosition(positionCtx);
        }

        throw new UnsupportedOperationException("Unsupported command: " + ctx.getText());
    }

    @Override
    public ReqUci visitUci(UciParser.UciContext ctx) {
        return UCIRequest.uci();
    }

    @Override
    public ReqIsReady visitIsready(UciParser.IsreadyContext ctx) {
        return UCIRequest.isready();
    }

    @Override
    public ReqUciNewGame visitUcinewgame(UciParser.UcinewgameContext ctx) {
        return UCIRequest.ucinewgame();
    }

    @Override
    public ReqStop visitStop(UciParser.StopContext ctx) {
        return UCIRequest.stop();
    }

    @Override
    public ReqQuit visitQuit(UciParser.QuitContext ctx) {
        return UCIRequest.quit();
    }

    @Override
    public ReqPosition visitPosition(UciParser.PositionContext ctx) {
        UciParser.StartposContext startPosCtx = ctx.startpos();
        UciParser.FenContext fenCtx = ctx.fen();

        List<String> moves = decodeMovesCtx(ctx.move());

        if (startPosCtx != null) {
            return UCIRequest.position(moves);
        } else if (fenCtx != null) {
            return UCIRequest.positionFEN(fenCtx.getText(), moves);
        }
        return null;
    }

    private List<String> decodeMovesCtx(List<UciParser.MoveContext> movesCtx) {
        if (movesCtx == null) {
            return Collections.emptyList();
        }
        List<String> moves = new ArrayList<>();
        for (UciParser.MoveContext moveCtx : movesCtx) {
            moves.add(moveCtx.getText());
        }
        return moves;
    }
}
