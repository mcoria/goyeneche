// RspUciOkTest.java
package net.chesstango.goyeneche.responses;

import net.chesstango.goyeneche.UCIGui;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for the {@link RspUciOk} class.
 * <p>
 * This class ensures that the execute method behaves as intended
 * and interacts with the {@link UCIGui} correctly.
 */
class RspUciOkTest {

    /**
     * Tests that the execute method calls the {@code do_uciOk} method
     * on the provided {@link UCIGui} mock with the correct parameter.
     */
    @Test
    void testExecute() {
        // Arrange
        UCIGui executorMock = Mockito.mock(UCIGui.class);
        RspUciOk rspUciOk = RspUciOk.INSTANCE;

        // Act
        rspUciOk.execute(executorMock);

        // Assert
        verify(executorMock).do_uciOk(rspUciOk);
    }
}