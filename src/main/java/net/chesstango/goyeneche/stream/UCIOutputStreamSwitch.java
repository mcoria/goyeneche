package net.chesstango.goyeneche.stream;

import net.chesstango.goyeneche.UCICommand;

import java.util.function.Predicate;

/**
 * @author Mauricio Coria
 */
public class UCIOutputStreamSwitch implements UCIOutputStream {

    private UCIOutputStream output;

    private final Predicate<UCICommand> predicateCondition;

    private final Runnable execute;

    public UCIOutputStreamSwitch(Predicate<UCICommand> predicateCondition, Runnable execute) {
        this.predicateCondition = predicateCondition;
        this.execute = execute;
    }

    public UCIOutputStreamSwitch setOutputStream(UCIOutputStream output) {
        this.output = output;
        return this;
    }

    @Override
    public void accept(UCICommand message) {
        output.accept(message);
        if (predicateCondition.test(message)) {
            execute.run();
        }
    }
}
