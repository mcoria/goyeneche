module net.chesstango.uci.protocol {
    exports net.chesstango.uci.protocol;
    exports net.chesstango.uci.protocol.requests;
    exports net.chesstango.uci.protocol.responses;
    exports net.chesstango.uci.protocol.stream;
    exports net.chesstango.uci.protocol.stream.strings;

    requires org.antlr.antlr4.runtime;
    requires static lombok;
}