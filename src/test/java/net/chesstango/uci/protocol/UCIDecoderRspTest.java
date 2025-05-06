package net.chesstango.uci.protocol;

import net.chesstango.uci.protocol.internal.UCIDecoder;
import net.chesstango.uci.protocol.responses.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * @author Mauricio Coria
 */
public class UCIDecoderRspTest {

    private UCIDecoder decoder = null;

    @BeforeEach
    public void setUp() {
        decoder = new UCIDecoder();
    }

    @Test
    public void test_parse_readyok() {
        UCICommand result = decoder.parseMessage("readyok");

        assertInstanceOf(RspReadyOk.class, result);

        assertEquals("readyok", result.toString());
    }

    @Test
    public void test_parse_bestmove() {
        UCICommand result = decoder.parseMessage("bestmove a2a4");

        assertInstanceOf(RspBestMove.class, result);

        RspBestMove bestMove = (RspBestMove) result;

        assertEquals("a2a4", bestMove.getBestMove());

        assertEquals("bestmove a2a4", result.toString());
    }

    @Test
    public void test_parse_bestmove_ponder() {
        UCICommand result = decoder.parseMessage("bestmove a2a4 ponder a5a7");

        assertInstanceOf(RspBestMove.class, result);

        RspBestMove bestMove = (RspBestMove) result;

        assertEquals("a2a4", bestMove.getBestMove());
        assertEquals("a5a7", bestMove.getPonderMove());

        assertEquals("bestmove a2a4 ponder a5a7", result.toString());
    }

    @Test
    public void test_parse_id_author() {
        UCICommand result = decoder.parseMessage("id author Mauricio Coria");

        assertInstanceOf(RspId.class, result);

        RspId rspId = (RspId) result;

        assertEquals(RspId.RspIdType.AUTHOR, rspId.getIdType());

        assertEquals("id author Mauricio Coria", result.toString());
    }

    @Test
    public void test_parse_id_name() {
        UCICommand result = decoder.parseMessage("id name Zonda");

        assertInstanceOf(RspId.class, result);

        RspId rspId = (RspId) result;

        assertEquals(RspId.RspIdType.NAME, rspId.getIdType());

        assertEquals("id name Zonda", result.toString());
    }

    @Test
    public void test_parse_uciok() {
        UCICommand result = decoder.parseMessage("uciok");

        assertInstanceOf(RspUciOk.class, result);

        assertEquals("uciok", result.toString());
    }

    @Test
    public void test_parse_option() {
        UCICommand result = decoder.parseMessage("option name NalimovPath type string default c:\\book");

        assertInstanceOf(RspOption.class, result);

        RspOption option = (RspOption) result;
        assertEquals("NalimovPath", option.getName());
        assertEquals(RspOption.OptionType.STRING, option.getType());
        assertEquals("c:\\book", option.getDefaultValue());

        assertEquals("option name NalimovPath type string default c:\\book", result.toString());
    }
}
