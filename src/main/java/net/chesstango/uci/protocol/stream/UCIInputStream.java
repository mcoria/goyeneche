package net.chesstango.uci.protocol.stream;

import net.chesstango.uci.protocol.UCICommand;

import java.util.function.Supplier;

/**
 * @author Mauricio Coria
 */
public interface UCIInputStream extends Supplier<UCICommand> {

}
