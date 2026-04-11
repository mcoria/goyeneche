package net.chesstango.goyeneche.requests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class ReqGoDepthTest {

    /**
     * Tests the execute method of ReqGoDepth to ensure it invokes the correct
     * method on the ReqGoExecutor with the correct parameter.
     */
    @Test
    void testExecute() {
        // Arrange
        int depth = 5;
        ReqGoDepth reqGoDepth = new ReqGoDepth(depth);
        ReqGoExecutor executorMock = Mockito.mock(ReqGoExecutor.class);

        // Act
        reqGoDepth.execute(executorMock);

        // Assert
        verify(executorMock).go(reqGoDepth);
    }

    /**
     * Tests the toString method to ensure it returns the expected string representation.
     */
    @Test
    void testToString() {
        // Arrange
        int depth = 10;
        ReqGoDepth reqGoDepth = new ReqGoDepth(depth);

        // Act
        String result = reqGoDepth.toString();

        // Assert
        assertEquals("go depth 10", result);
    }

    /**
     * Tests the equals method to ensure it correctly evaluates equality for equal objects.
     */
    @Test
    void testEquals_SameDepth() {
        // Arrange
        ReqGoDepth reqGoDepth1 = new ReqGoDepth(5);
        ReqGoDepth reqGoDepth2 = new ReqGoDepth(5);

        // Act & Assert
        assertEquals(reqGoDepth1, reqGoDepth2);
        assertTrue(reqGoDepth1.equals(reqGoDepth2)); // To verify explicitly
    }

    /**
     * Tests the equals method to ensure it returns false for differing depths.
     */
    @Test
    void testEquals_DifferentDepth() {
        // Arrange
        ReqGoDepth reqGoDepth1 = new ReqGoDepth(5);
        ReqGoDepth reqGoDepth2 = new ReqGoDepth(10);

        // Act & Assert
        assertNotEquals(reqGoDepth1, reqGoDepth2);
    }

    /**
     * Tests the equals method to ensure it returns false when compared against an unrelated object or null.
     */
    @Test
    void testEquals_UnrelatedObject() {
        // Arrange
        ReqGoDepth reqGoDepth = new ReqGoDepth(5);

        // Act & Assert
        assertNotEquals(reqGoDepth, null);
        assertNotEquals(reqGoDepth, "Some String");
    }

    /**
     * Tests the hashCode method to ensure consistent hashing for equal objects.
     */
    @Test
    void testHashCode() {
        // Arrange
        ReqGoDepth reqGoDepth1 = new ReqGoDepth(7);
        ReqGoDepth reqGoDepth2 = new ReqGoDepth(7);

        // Act & Assert
        assertEquals(reqGoDepth1.hashCode(), reqGoDepth2.hashCode());
    }
}