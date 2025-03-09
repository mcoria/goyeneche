package net.chesstango.uci.protocol.stream;

import net.chesstango.uci.protocol.UCICommand;

import java.util.function.Consumer;

/**
 * @author Mauricio Coria
 */
public interface UCIOutputStream extends Consumer<UCICommand> {
}
