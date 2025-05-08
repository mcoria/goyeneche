package net.chesstango.goyeneche.internal.antlr4;

import net.chesstango.goyeneche.UCICommand;
import net.chesstango.goyeneche.requests.ReqUci;
import net.chesstango.goyeneche.requests.UCIRequest;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * @author Mauricio Coria
 */
public class ParserTest {
    @Test
    public void test_parse() {
        CodePointCharStream stream = CharStreams.fromString("uci");
        UciLexer lexer = new UciLexer(stream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        UciParser parser = new UciParser(tokenStream);
        ParserRuleContext commandContext = parser.command();

        TangoUciVisitor tangoUciVisitor = new TangoUciVisitor();

        UCICommand command = commandContext.accept(tangoUciVisitor);

        assertInstanceOf(UCIRequest.class, command);

        assertInstanceOf(ReqUci.class, command);
    }
}
