package net.chesstango.uci.protocol.internal.antlr4;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.requests.UCIRequest;

/**
 * @author Mauricio Coria
 */
public class TangoUciVisitor extends UciBaseVisitor<UCICommand> {

    @Override
    public UCICommand visitUci(UciParser.UciContext ctx) {
        UciParser.RequestContext request = ctx.request();
        UciParser.ResponseContext response = ctx.response();

        return request != null ? visitRequest(request) : response != null ? visitResponse(response) : null;
    }

    @Override
    public UCICommand visitRequest(UciParser.RequestContext ctx) {
        UciParser.UciRequestContext uciRequest = ctx.uciRequest();
        UciParser.IsReadyRequestContext isReadyRequest = ctx.isReadyRequest();

        return uciRequest != null ? visitUciRequest(uciRequest) : isReadyRequest != null ? visitIsReadyRequest(isReadyRequest) : null;
    }

    @Override
    public UCICommand visitUciRequest(UciParser.UciRequestContext ctx) {
        return UCIRequest.uci();
    }


}
