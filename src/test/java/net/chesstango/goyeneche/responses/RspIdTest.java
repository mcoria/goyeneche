package net.chesstango.goyeneche.responses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link RspId} class.
 * Specifically tests the {@link RspId#toString()} method.
 */
class RspIdTest {

    /**
     * Test that the toString method correctly formats the output
     * when the RspIdType is NAME and the text is a simple string.
     */
    @Test
    void testToString_NameType() {
        // Arrange
        RspId.RspIdType type = RspId.RspIdType.NAME;
        String text = "SimpleName";
        RspId rspId = new RspId(type, text);

        // Act
        String result = rspId.toString();

        // Assert
        assertEquals("id name SimpleName", result);
    }

    /**
     * Test that the toString method correctly formats the output
     * when the RspIdType is AUTHOR and the text is a simple string.
     */
    @Test
    void testToString_AuthorType() {
        // Arrange
        RspId.RspIdType type = RspId.RspIdType.AUTHOR;
        String text = "SimpleAuthor";
        RspId rspId = new RspId(type, text);

        // Act
        String result = rspId.toString();

        // Assert
        assertEquals("id author SimpleAuthor", result);
    }

    /**
     * Test that the toString method correctly handles an empty text string
     * when the RspIdType is NAME.
     */
    @Test
    void testToString_EmptyText_NameType() {
        // Arrange
        RspId.RspIdType type = RspId.RspIdType.NAME;
        String text = "";
        RspId rspId = new RspId(type, text);

        // Act
        String result = rspId.toString();

        // Assert
        assertEquals("id name ", result);
    }

    /**
     * Test that the toString method correctly handles an empty text string
     * when the RspIdType is AUTHOR.
     */
    @Test
    void testToString_EmptyText_AuthorType() {
        // Arrange
        RspId.RspIdType type = RspId.RspIdType.AUTHOR;
        String text = "";
        RspId rspId = new RspId(type, text);

        // Act
        String result = rspId.toString();

        // Assert
        assertEquals("id author ", result);
    }

    /**
     * Test that the toString method correctly handles text with special characters
     * when the RspIdType is NAME.
     */
    @Test
    void testToString_SpecialCharacters_NameType() {
        // Arrange
        RspId.RspIdType type = RspId.RspIdType.NAME;
        String text = "Special!@#$%^&*()";
        RspId rspId = new RspId(type, text);

        // Act
        String result = rspId.toString();

        // Assert
        assertEquals("id name Special!@#$%^&*()", result);
    }

    /**
     * Test that the toString method correctly handles text with leading and trailing whitespace
     * when the RspIdType is AUTHOR.
     */
    @Test
    void testToString_Whitespace_AuthorType() {
        // Arrange
        RspId.RspIdType type = RspId.RspIdType.AUTHOR;
        String text = "  Author Name  ";
        RspId rspId = new RspId(type, text);

        // Act
        String result = rspId.toString();

        // Assert
        assertEquals("id author   Author Name  ", result);
    }
}