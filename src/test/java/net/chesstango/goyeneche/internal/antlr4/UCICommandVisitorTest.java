package net.chesstango.goyeneche.internal.antlr4;

import net.chesstango.goyeneche.UCICommand;
import net.chesstango.goyeneche.internal.UCIGoyenecheListener;
import net.chesstango.goyeneche.requests.ReqUci;
import net.chesstango.goyeneche.requests.UCIRequest;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Mauricio Coria
 */
public class UCICommandVisitorTest {

    private UCIGoyenecheListener listener;
    private ParseTreeWalker walker;

    @BeforeEach
    void setup() {
        listener = new UCIGoyenecheListener();
        walker = ParseTreeWalker.DEFAULT;
    }

    @Test
    public void test_parse() {
        CodePointCharStream stream = CharStreams.fromString("uci");
        UCILexer lexer = new UCILexer(stream);

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        UCIParser parser = new UCIParser(tokenStream);
        UCIParser.CommandContext tree = parser.command();

        walker.walk(listener, tree); // Initiate the walk through the parse tree

        UCICommand command = listener.getCommand();

        assertInstanceOf(UCIRequest.class, command);
        assertInstanceOf(ReqUci.class, command);
    }

    @Test
    public void test_unknown_command() {
        CodePointCharStream stream = CharStreams.fromString("xx xxx");
        UCILexer lexer = new UCILexer(stream);

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        UCIParser parser = new UCIParser(tokenStream);
        UCIParser.CommandContext tree = parser.command();

        walker.walk(listener, tree); // Initiate the walk through the parse tree

        UCICommand command = listener.getCommand();

        assertNull(command);
    }
}
