package net.chesstango.uci.protocol.internal.antlr4;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.requests.*;

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
        UciParser.StopContext stopCtx = ctx.stop();
        UciParser.QuitContext quitCtx = ctx.quit();
        return uciCtx != null ? visitUci(uciCtx) : isReadyCtx != null ? visitIsready(isReadyCtx) : uciNewGameCtx != null ? visitUcinewgame(uciNewGameCtx) : stopCtx != null ? visitStop(stopCtx) : quitCtx != null ? visitQuit(quitCtx) : null;
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
}
