package net.chesstango.goyeneche.internal;

import net.chesstango.goyeneche.UCICommand;
import net.chesstango.goyeneche.UCICommandUnknown;
import net.chesstango.goyeneche.internal.antlr4.UCILexer;
import net.chesstango.goyeneche.internal.antlr4.UCIParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * The UCIDecoder class provides functionality to parse strings formatted in the Universal Chess
 * Interface (UCI) protocol into UCICommand objects.
 * <p>
 * The UCI protocol is a standard protocol used for communication between chess engines and
 * graphical user interfaces (GUIs). This class serves as a utility to interpret textual commands
 * and convert them into corresponding representations for further processing within a chess engine
 * or GUI implementation.
 * <p>
 * The main responsibility of the UCIDecoder is to process input strings, tokenize and analyze
 * their structure using the underlying lexer and parser, and return a UCICommand object that can
 * be acted upon by the associated system components.
 *
 * @author Mauricio Coria
 */
public class UCIDecoder {


    /**
     * Parses a given input string in the Universal Chess Interface (UCI) protocol format
     * and returns the corresponding UCICommand.
     *
     * @param input the input string in UCI protocol format to be parsed
     * @return the UCICommand object representing the parsed UCI command
     */
    public UCICommand parseMessage(String input) {
        CodePointCharStream stream = CharStreams.fromString(input);
        UCILexer lexer = new UCILexer(stream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        // 4. Create a parser that feeds off the tokens buffer
        UCIParser parser = new UCIParser(tokenStream);
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);

        // 5. Begin parsing at the 'command' rule
        UCIParser.CommandContext tree = parser.command();

        // 6. Create the walker and hook up the listener
        ParseTreeWalker walker = ParseTreeWalker.DEFAULT;

        UCIGoyenecheListener listener = new UCIGoyenecheListener();

        walker.walk(listener, tree); // Initiate the walk through the parse tree

        UCICommand command = null;
        try {
            command = listener.getCommand();

            if (command == null) {
                command = new UCICommandUnknown(input);
            }
        } catch (RuntimeException e) {
            command = new UCICommandUnknown(input);
        }

        return command;
    }
}

