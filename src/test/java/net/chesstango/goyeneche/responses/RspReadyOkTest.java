// RspReadyOkTest.java
package net.chesstango.goyeneche.responses;

import net.chesstango.goyeneche.UCIGui;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for the RspReadyOk class.
 * <p>
 * The class RspReadyOk represents the "readyok" response in the Universal Chess Interface (UCI) protocol.
 * The execute method is responsible for invoking the do_readyOk method on the provided UCIGui executor, passing itself as an argument.
 */
class RspReadyOkTest {

    /**
     * Test case for the execute method.
     * Verifies that the do_readyOk method is called on the provided UCIGui executor with the correct RspReadyOk instance.
     */
    @Test
    void testExecute() {
        // Arrange
        UCIGui mockExecutor = Mockito.mock(UCIGui.class);
        RspReadyOk response = RspReadyOk.INSTANCE;

        // Act
        response.execute(mockExecutor);

        // Assert
        verify(mockExecutor).do_readyOk(response);
    }

    /**
     * Test case for the toString method.
     * Verifies that the toString method of RspReadyOk returns the expected string "readyok".
     */
    @Test
    void testToString() {
        // Arrange
        RspReadyOk response = RspReadyOk.INSTANCE;

        // Act
        String result = response.toString();

        // Assert
        assert result.equals("readyok");
    }
}