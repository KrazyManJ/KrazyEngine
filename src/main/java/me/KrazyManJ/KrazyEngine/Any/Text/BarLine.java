package me.KrazyManJ.KrazyEngine.Any.Text;

import me.KrazyManJ.KrazyEngine.Any.Component.ComponentUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.awt.*;

@SuppressWarnings("unused")
public final class BarLine {
    private char symbol;
    public BarLine setSymbol(char symbol) {this.symbol = symbol;return this;}
    public char getSymbol() {return symbol;}

    private int symbolamount;
    public BarLine setSymbolamount(int symbolamount) {this.symbolamount = symbolamount;return this;}
    public int getSymbolamount() {return symbolamount;}

    private float maxValue;
    public BarLine setMaxValue(float maxValue) throws IllegalBarLineActionException {
        if (maxValue < value) throw new IllegalBarLineActionException("Setting max value to lower than actual value");
        this.maxValue = maxValue;
        return this;
    }

    private float value;
    public BarLine setValue(float value) {this.value = Math.min(value, maxValue);return this;}

    private Color valueColor;
    public BarLine setValueColor(Color valueColor) {this.valueColor = valueColor;return this;}

    private Color backColor;
    public BarLine setBackColor(Color backColor) {this.backColor = backColor;return this;}

    public BarLine(char symbol, int symbolamount, float maxValue, Color valueColor, Color backColor) {
        this.symbol = symbol;

        this.symbolamount = symbolamount;
        this.maxValue = maxValue;
        this.value = 0;

        this.valueColor = valueColor;
        this.backColor = backColor;
    }

    public int coloredAmount(){
        return (int) Math.floor(value/maxValue*symbolamount);
    }

    public String draw(){
        return ChatColor.of(valueColor)+(symbol+"").repeat(coloredAmount())+
                ChatColor.of(backColor)+(symbol+"").repeat(symbolamount-coloredAmount());
    }
    public BaseComponent[] drawComponent(){
        return TextComponent.fromLegacyText(draw());
    }
}
