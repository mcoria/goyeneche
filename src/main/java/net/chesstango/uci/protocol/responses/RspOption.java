package net.chesstango.uci.protocol.responses;

import lombok.Getter;
import net.chesstango.uci.protocol.UCIGui;
import net.chesstango.uci.protocol.UCIResponse;

/**
 * @author Mauricio Coria
 */
@Getter
public class RspOption implements UCIResponse {


    public enum OptionType {CHECK, STRING, COMBO, BUTTON, SPIN}

    private final String name;
    private final OptionType type;
    private final String defaultValue;
    private final String minValue;
    private final String maxValue;


    RspOption(String name, OptionType type, String defaultValue, String minValue, String maxValue) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static RspOption createStringOption(String name, String defaultValue) {
        return new RspOption(name, OptionType.STRING, defaultValue, null, null);
    }

    public static RspOption createCheckOption(String name, boolean defaultValue) {
        return new RspOption(name, OptionType.CHECK, Boolean.toString(defaultValue), null, null);
    }

    @Override
    public UCIResponseType getResponseType() {
        return UCIResponseType.OPTION;
    }

    @Override
    public void execute(UCIGui executor) {
        executor.do_option(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("option name ");
        sb.append(name).append(" type ").append(type.toString().toLowerCase());
        if (defaultValue != null) {
            sb.append(" default ").append(defaultValue);
        } else if (OptionType.STRING.equals(type)) {
            sb.append(" default ").append("<empty>");
        }
        if (minValue != null) {
            sb.append(" minValue ").append(minValue);
        }
        if (maxValue != null) {
            sb.append(" maxValue ").append(maxValue);
        }
        return sb.toString();
    }
}
