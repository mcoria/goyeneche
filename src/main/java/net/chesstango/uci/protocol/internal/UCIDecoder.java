package net.chesstango.uci.protocol.internal;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.UCICommandUnknown;
import net.chesstango.uci.protocol.internal.antlr4.TangoUciVisitor;
import net.chesstango.uci.protocol.internal.antlr4.UciLexer;
import net.chesstango.uci.protocol.internal.antlr4.UciParser;
import net.chesstango.uci.protocol.requests.*;
import net.chesstango.uci.protocol.requests.go.ReqGoDepth;
import net.chesstango.uci.protocol.requests.go.ReqGoFast;
import net.chesstango.uci.protocol.requests.go.ReqGoInfinite;
import net.chesstango.uci.protocol.requests.go.ReqGoTime;
import net.chesstango.uci.protocol.responses.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mauricio Coria
 */
public class UCIDecoder {


    public UCICommand parseMessage(String input) {
        CodePointCharStream stream = CharStreams.fromString(input);
        UciLexer calcLexer = new UciLexer(stream);
        CommonTokenStream tokenStream = new CommonTokenStream(calcLexer);
        UciParser calcParser = new UciParser(tokenStream);
        UciParser.CommandContext cmdContext = calcParser.command();

        TangoUciVisitor tangoUciVisitor = new TangoUciVisitor();

        return cmdContext.accept(tangoUciVisitor);
    }

}

