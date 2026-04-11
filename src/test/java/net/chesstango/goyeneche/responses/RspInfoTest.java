package net.chesstango.goyeneche.responses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link RspInfo} class.
 * Specifically tests the {@link RspInfo#toString()} method.
 */
class RspInfoTest {

    /**
     * Test that the toString method correctly formats the output
     * when the info string is a simple text.
     */
    @Test
    void testToStringSimpleInfo() {
        // Arrange
        String input = "Simple information";
        RspInfo rspInfo = new RspInfo(input);

        // Act
        String result = rspInfo.toString();

        // Assert
        assertEquals("info Simple information", result);
    }

    /**
     * Test that the toString method correctly handles an empty info string.
     */
    @Test
    void testToStringEmptyInfo() {
        // Arrange
        String input = "";
        RspInfo rspInfo = new RspInfo(input);

        // Act
        String result = rspInfo.toString();

        // Assert
        assertEquals("info ", result);
    }

    /**
     * Test that the toString method correctly formats the output when the info string contains special characters.
     */
    @Test
    void testToStringSpecialCharacters() {
        // Arrange
        String input = "Special characters: !@#$%^&*()";
        RspInfo rspInfo = new RspInfo(input);

        // Act
        String result = rspInfo.toString();

        // Assert
        assertEquals("info Special characters: !@#$%^&*()", result);
    }

    /**
     * Test that the toString method correctly formats the output
     * when the info string includes leading and trailing whitespace.
     */
    @Test
    void testToStringWhitespace() {
        // Arrange
        String input = "  Leading and trailing whitespace  ";
        RspInfo rspInfo = new RspInfo(input);

        // Act
        String result = rspInfo.toString();

        // Assert
        assertEquals("info   Leading and trailing whitespace  ", result);
    }
}