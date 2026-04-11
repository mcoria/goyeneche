package net.chesstango.goyeneche.responses;

import net.chesstango.goyeneche.UCIGui;
import net.chesstango.goyeneche.responses.RspBestMove;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RspBestMoveTest {

    /**
     * RspBestMove is a final class used to represent a UCI Response for the best move and optionally a ponder move.
     * The `execute` method in this class triggers the `do_bestMove` method of the provided `UCIGui` executor.
     * These tests focus on verifying the correct interaction between the `RspBestMove` instance and the mocked `UCIGui`.
     */

    @Test
    void testExecute_withBestMoveOnly() {
        // Arrange
        String bestMove = "e2e4";
        RspBestMove rspBestMove = new RspBestMove(bestMove);
        UCIGui mockExecutor = mock(UCIGui.class);

        // Act
        rspBestMove.execute(mockExecutor);

        // Assert
        verify(mockExecutor, times(1)).do_bestMove(rspBestMove);
        verifyNoMoreInteractions(mockExecutor);
    }

    @Test
    void testExecute_withBestMoveAndPonderMove() {
        // Arrange
        String bestMove = "e2e4";
        String ponderMove = "e7e5";
        RspBestMove rspBestMove = new RspBestMove(bestMove, ponderMove);
        UCIGui mockExecutor = mock(UCIGui.class);

        // Act
        rspBestMove.execute(mockExecutor);

        // Assert
        verify(mockExecutor, times(1)).do_bestMove(rspBestMove);
        verifyNoMoreInteractions(mockExecutor);
    }

}