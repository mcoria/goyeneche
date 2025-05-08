package net.chesstango.goyeneche;

import net.chesstango.goyeneche.internal.UCIDecoder;
import net.chesstango.goyeneche.requests.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * @author Mauricio Coria
 */
public class UCIDecoderReqTest {
    private UCIDecoder decoder = null;

    @BeforeEach
    public void setUp() {
        decoder = new UCIDecoder();
    }

    @Test
    public void test_parse_position_startpos() {
        UCICommand result = decoder.parseMessage("position startpos");

        assertInstanceOf(UCIRequest.class, result);

        assertInstanceOf(ReqPosition.class, result);

        ReqPosition command = (ReqPosition) result;

        List<String> moves = command.getMoves();

        assertEquals(0, moves.size());

        assertEquals("position startpos", result.toString());
    }


    @Test
    public void test_parse_postition_startpos_with_1_move() {
        UCICommand result = decoder.parseMessage("position startpos moves f2f4");

        assertInstanceOf(ReqPosition.class, result);

        ReqPosition command = (ReqPosition) result;

        List<String> moves = command.getMoves();

        assertEquals(1, moves.size());

        assertEquals("f2f4", moves.get(0));

        assertEquals("position startpos moves f2f4", result.toString());
    }

    @Test
    public void test_parse_postition_startpos_with_2_move() {
        UCICommand result = decoder.parseMessage("position startpos moves e2e3 e7e5");

        assertInstanceOf(ReqPosition.class, result);

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

        assertInstanceOf(ReqPosition.class, result);

        ReqPosition command = (ReqPosition) result;

        assertEquals("2Q4R/5p2/2bPkb1B/p1p2p1p/7P/P4PP1/4n2Q/4K1NR b - - 0 1", command.getFen());

        List<String> moves = command.getMoves();
        assertEquals(1, moves.size());
        assertEquals("e2e4", moves.getFirst());

        assertEquals("position fen 2Q4R/5p2/2bPkb1B/p1p2p1p/7P/P4PP1/4n2Q/4K1NR b - - 0 1 moves e2e4", result.toString());
    }

    @Test
    public void test_parse_postition_fen_with_2_move() {
        UCICommand result = decoder.parseMessage("position fen 2Q4R/5p2/2bPkb1B/p1p2p1p/7P/P4PP1/4n2Q/4K1NR b - - 0 1 moves e2e4 e7e5");

        assertInstanceOf(ReqPosition.class, result);

        ReqPosition command = (ReqPosition) result;

        assertEquals("2Q4R/5p2/2bPkb1B/p1p2p1p/7P/P4PP1/4n2Q/4K1NR b - - 0 1", command.getFen());

        List<String> moves = command.getMoves();
        assertEquals(2, moves.size());
        assertEquals("e2e4", moves.get(0));
        assertEquals("e7e5", moves.get(1));

        assertEquals("position fen 2Q4R/5p2/2bPkb1B/p1p2p1p/7P/P4PP1/4n2Q/4K1NR b - - 0 1 moves e2e4 e7e5", result.toString());
    }

    @Test
    public void test_parse_uci() {
        UCICommand result = decoder.parseMessage("uci");

        assertInstanceOf(ReqUci.class, result);

        assertEquals("uci", result.toString());
    }

    @Test
    public void test_parse_ucinewgame() {
        UCICommand result = decoder.parseMessage("ucinewgame");

        assertInstanceOf(ReqUciNewGame.class, result);

        assertEquals("ucinewgame", result.toString());
    }

    @Test
    public void test_parse_stop() {
        UCICommand result = decoder.parseMessage("stop");

        assertInstanceOf(ReqStop.class, result);

        assertEquals("stop", result.toString());
    }

    @Test
    public void test_parse_quit() {
        UCICommand result = decoder.parseMessage("quit");

        assertInstanceOf(ReqQuit.class, result);

        assertEquals("quit", result.toString());
    }

    @Test
    public void test_parse_go() {
        UCICommand result = decoder.parseMessage("go");

        assertInstanceOf(ReqGo.class, result);
        assertInstanceOf(ReqGoInfinite.class, result);

        assertEquals("go infinite", result.toString());
    }

    @Test
    public void test_parse_go_infinite() {
        UCICommand result = decoder.parseMessage("go infinite");

        assertInstanceOf(ReqGo.class, result);
        assertInstanceOf(ReqGoInfinite.class, result);

        assertEquals("go infinite", result.toString());
    }

    @Test
    public void test_parse_go_depth() {
        UCICommand result = decoder.parseMessage("go depth 1");

        assertInstanceOf(ReqGo.class, result);

        ReqGoDepth go = (ReqGoDepth) result;
        assertEquals(1, go.getDepth());

        assertEquals("go depth 1", result.toString());
    }

    @Test
    public void test_parse_go_movetime() {
        UCICommand result = decoder.parseMessage("go movetime 20000");

        assertInstanceOf(ReqGo.class, result);

        ReqGoTime go = (ReqGoTime) result;
        assertEquals(20000, go.getTimeOut());

        assertEquals("go movetime 20000", result.toString());
    }

    @Test
    public void test_parse_go_movebyclock() {
        UCICommand result = decoder.parseMessage("go wtime 120000 btime 130000 winc 6000 binc 7000");

        assertInstanceOf(ReqGo.class, result);

        ReqGoFast go = (ReqGoFast) result;
        assertEquals(120000, go.getWTime());
        assertEquals(6000, go.getWInc());
        assertEquals(130000, go.getBTime());
        assertEquals(7000, go.getBInc());

        assertEquals("go wtime 120000 btime 130000 winc 6000 binc 7000", result.toString());
    }


    @Test
    public void test_parse_isready() {
        UCICommand result = decoder.parseMessage("isready");

        assertInstanceOf(ReqIsReady.class, result);

        assertEquals("isready", result.toString());
    }

    @Test
    public void test_parse_setoption() {
        UCICommand result = decoder.parseMessage("setoption name tablebase value c:/bla/ble.bin");

        assertInstanceOf(ReqSetOption.class, result);

        ReqSetOption option = (ReqSetOption) result;

        assertEquals("tablebase", option.getId());
        assertEquals("c:/bla/ble.bin", option.getValue());

        assertEquals("setoption name tablebase value c:/bla/ble.bin", option.toString());
    }
}
