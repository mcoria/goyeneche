package net.chesstango.uci.protocol;

import net.chesstango.uci.protocol.internal.UCIDecoder;
import net.chesstango.uci.protocol.requests.*;
import net.chesstango.uci.protocol.requests.go.ReqGoFast;
import net.chesstango.uci.protocol.requests.go.ReqGoDepth;
import net.chesstango.uci.protocol.requests.go.ReqGoInfinite;
import net.chesstango.uci.protocol.requests.go.ReqGoTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Mauricio Coria
 */
public class UCIDecoderCmdTest {

    private UCIDecoder decoder = null;

    @BeforeEach
    public void setUp() {
        decoder = new UCIDecoder();
    }

    @Test
    public void test_parse_position_startpos() {
        UCICommand result = decoder.parseMessage("position startpos");

        assertTrue(result instanceof UCIRequest);

        assertTrue(result instanceof ReqPosition);

        ReqPosition command = (ReqPosition) result;

        List<String> moves = command.getMoves();

        assertEquals(0, moves.size());

        assertEquals("position startpos", result.toString());
    }


    @Test
    public void test_parse_postition_startpos_with_1_move() {
        UCICommand result = decoder.parseMessage("position startpos moves f2f4");

        assertTrue(result instanceof ReqPosition);

        ReqPosition command = (ReqPosition) result;

        List<String> moves = command.getMoves();

        assertEquals(1, moves.size());

        assertEquals("f2f4", moves.get(0));

        assertEquals("position startpos moves f2f4", result.toString());
    }

    @Test
    public void test_parse_postition_startpos_with_2_move() {
        UCICommand result = decoder.parseMessage("position startpos moves e2e3 e7e5");

        assertTrue(result instanceof ReqPosition);

        ReqPosition command = (ReqPosition) result;

        List<String> moves = command.getMoves();

        assertEquals(2, moves.size());

        assertEquals("e2e3", moves.get(0));
        assertEquals("e7e5", moves.get(1));

        assertEquals("position startpos moves e2e3 e7e5", result.toString());
    }


    @Test
    public void test_parse_postition_fen_with_1_move() {
        UCICommand result = decoder.parseMessage("position fen 2Q4R/5p2/2bPkb1B/p1p2p1p/7P/P4PP1/4n2Q/4K1NR b - - 0 1 moves e2e4");

        assertTrue(result instanceof ReqPosition);

        ReqPosition command = (ReqPosition) result;

        List<String> moves = command.getMoves();

        assertEquals(1, moves.size());
        assertEquals("2Q4R/5p2/2bPkb1B/p1p2p1p/7P/P4PP1/4n2Q/4K1NR b - - 0 1", command.getFen());
        assertEquals("e2e4", moves.get(0));

        assertEquals("position fen 2Q4R/5p2/2bPkb1B/p1p2p1p/7P/P4PP1/4n2Q/4K1NR b - - 0 1 moves e2e4", result.toString());
    }

    @Test
    public void test_parse_uci() {
        UCICommand result = decoder.parseMessage("uci");

        assertTrue(result instanceof ReqUci);

        assertEquals("uci", result.toString());
    }

    @Test
    public void test_parse_ucinewgame() {
        UCICommand result = decoder.parseMessage("ucinewgame");

        assertTrue(result instanceof ReqUciNewGame);

        assertEquals("ucinewgame", result.toString());
    }

    @Test
    public void test_parse_stop() {
        UCICommand result = decoder.parseMessage("stop");

        assertTrue(result instanceof ReqStop);

        assertEquals("stop", result.toString());
    }

    @Test
    public void test_parse_quit() {
        UCICommand result = decoder.parseMessage("quit");

        assertTrue(result instanceof ReqQuit);

        assertEquals("quit", result.toString());
    }

    @Test
    public void test_parse_go() {
        UCICommand result = decoder.parseMessage("go");

        assertTrue(result instanceof ReqGo);
        assertTrue(result instanceof ReqGoInfinite);

        assertEquals("go infinite", result.toString());
    }

    @Test
    public void test_parse_go_infinite() {
        UCICommand result = decoder.parseMessage("go infinite");

        assertTrue(result instanceof ReqGo);
        assertTrue(result instanceof ReqGoInfinite);

        assertEquals("go infinite", result.toString());
    }

    @Test
    public void test_parse_go_depth() {
        UCICommand result = decoder.parseMessage("go depth 1");

        assertTrue(result instanceof ReqGo);

        ReqGoDepth go = (ReqGoDepth) result;
        assertEquals(1, go.getDepth());

        assertEquals("go depth 1", result.toString());
    }

    @Test
    public void test_parse_go_movetime() {
        UCICommand result = decoder.parseMessage("go movetime 20000");

        assertTrue(result instanceof ReqGo);

        ReqGoTime go = (ReqGoTime) result;
        assertEquals(20000, go.getTimeOut());

        assertEquals("go movetime 20000", result.toString());
    }

    @Test
    public void test_parse_go_movebyclock() {
        UCICommand result = decoder.parseMessage("go wtime 120000 btime 130000 winc 6000 binc 7000");

        assertTrue(result instanceof ReqGo);

        ReqGoFast go = (ReqGoFast) result;
        assertEquals(120000, go.getWTime());
        assertEquals(6000, go.getWInc());
        assertEquals(130000, go.getBTime());
        assertEquals(7000, go.getBInc());

        assertEquals("go wtime 120000 btime 130000 winc 6000 binc 7000", result.toString());
    }


    @Test
    public void test_parse_ready() {
        UCICommand result = decoder.parseMessage("isready");

        assertTrue(result instanceof ReqIsReady);

        assertEquals("isready", result.toString());
    }

    @Test
    public void test_parse_setoption() {
        UCICommand result = decoder.parseMessage("setoption name tablebase value c:/bla/ble.bin");

        assertTrue(result instanceof ReqSetOption);

        ReqSetOption option = (ReqSetOption) result;

        assertEquals("tablebase", option.getId());
        assertEquals("c:/bla/ble.bin", option.getValue());

        assertEquals("setoption name tablebase value c:/bla/ble.bin", option.toString());
    }
}
