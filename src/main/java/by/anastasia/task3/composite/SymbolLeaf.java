package by.anastasia.task3.composite;

import java.util.List;

public class SymbolLeaf extends AbstractTextComponent {
    private char symbol;

    public SymbolLeaf(TextType type, char symbol) {
        super(type);
        this.symbol = symbol;
    }

    public SymbolLeaf(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void add(AbstractTextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(AbstractTextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public AbstractTextComponent getElement(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AbstractTextComponent> getComponents() {
        throw new UnsupportedOperationException();
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}