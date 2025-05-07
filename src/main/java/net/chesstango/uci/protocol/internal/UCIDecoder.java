package net.chesstango.uci.protocol.internal;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.internal.antlr4.TangoUciVisitor;
import net.chesstango.uci.protocol.internal.antlr4.UciLexer;
import net.chesstango.uci.protocol.internal.antlr4.UciParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

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

