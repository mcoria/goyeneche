package net.chesstango.uci.protocol.internal.antlr4;

import net.chesstango.uci.protocol.UCICommand;
import net.chesstango.uci.protocol.requests.ReqUci;
import net.chesstango.uci.protocol.requests.UCIRequest;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * @author Mauricio Coria
 */
public class ParserTest {
    @Test
    public void test_parse() {
        CodePointCharStream stream = CharStreams.fromString("uci");
        UciLexer calcLexer = new UciLexer(stream);
        CommonTokenStream tokenStream = new CommonTokenStream(calcLexer);
        UciParser calcParser = new UciParser(tokenStream);
        UciParser.UciContext uciContext = calcParser.uci();

        TangoUciVisitor tangoUciVisitor = new TangoUciVisitor();

        UCICommand command = uciContext.accept(tangoUciVisitor);

        assertInstanceOf(UCIRequest.class, command);

        assertInstanceOf(ReqUci.class, command);
    }
}
