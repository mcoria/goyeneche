package net.chesstango.goyeneche.responses;

import lombok.Getter;
import net.chesstango.goyeneche.UCIGui;

/**
 * @author Mauricio Coria
 */
@Getter
public final class RspOption implements UCIResponse {
    public enum OptionType {CHECK, STRING, COMBO, BUTTON, SPIN}

    private final String name;
    private final OptionType type;
    private final String defaultValue;
    private final String minValue;
    private final String maxValue;

    static RspOption buildStringOption(String name, String defaultValue) {
        return new RspOption(name, RspOption.OptionType.STRING, defaultValue, null, null);
    }

    static RspOption buildButtonOption(String name) {
        return new RspOption(name, RspOption.OptionType.BUTTON, null, null, null);
    }

    static RspOption buildCheckOption(String name, boolean defaultValue) {
        return new RspOption(name, RspOption.OptionType.CHECK, Boolean.toString(defaultValue), null, null);
    }

    private RspOption(String name, OptionType type, String defaultValue, String minValue, String maxValue) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
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
