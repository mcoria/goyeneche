package net.chesstango.goyeneche.stream;

import net.chesstango.goyeneche.UCICommand;

import java.util.function.Consumer;

/**
 * @author Mauricio Coria
 */
public interface UCIOutputStream extends Consumer<UCICommand> {
}
