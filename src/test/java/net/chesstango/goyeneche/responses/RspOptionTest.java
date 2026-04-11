package net.chesstango.goyeneche.responses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RspOptionTest {

    /**
     * Tests the toString method for a String option.
     * Verifies that the string representation includes the name, type, and default value.
     */
    @Test
    void testToString_StringOption() {
        RspOption option = RspOption.buildStringOption("ExampleString", "defaultValue");
        String expected = "option name ExampleString type string default defaultValue";
        assertEquals(expected, option.toString());
    }

    /**
     * Tests the toString method for a Button option.
     * Verifies that the string representation includes the name and type only.
     */
    @Test
    void testToString_ButtonOption() {
        RspOption option = RspOption.buildButtonOption("ExampleButton");
        String expected = "option name ExampleButton type button";
        assertEquals(expected, option.toString());
    }

    /**
     * Tests the toString method for a Check option.
     * Verifies that the string representation includes the name, type, and default value (true).
     */
    @Test
    void testToString_CheckOption_TrueDefault() {
        RspOption option = RspOption.buildCheckOption("ExampleCheck", true);
        String expected = "option name ExampleCheck type check default true";
        assertEquals(expected, option.toString());
    }

    /**
     * Tests the toString method for a Check option with a false default value.
     * Verifies the formatting of the string representation.
     */
    @Test
    void testToString_CheckOption_FalseDefault() {
        RspOption option = RspOption.buildCheckOption("ExampleCheck", false);
        String expected = "option name ExampleCheck type check default false";
        assertEquals(expected, option.toString());
    }

    /**
     * Tests the toString method for a Spin option.
     * Verifies that the string representation includes the name, type, min, and max values.
     */
    @Test
    void testToString_SpinOption() {
        RspOption option = RspOption.buildSpingOption("ExampleSpin", "16", "1", "100");
        String expected = "option name ExampleSpin type spin default 16 minValue 1 maxValue 100";
        assertEquals(expected, option.toString());
    }

    /**
     * Tests the toString method for a String option without a default value.
     * Verifies that "<empty>" is included in the string representation.
     */
    @Test
    void testToString_StringOption_EmptyDefault() {
        RspOption option = RspOption.buildStringOption("ExampleString", null);
        String expected = "option name ExampleString type string default <empty>";
        assertEquals(expected, option.toString());
    }
}