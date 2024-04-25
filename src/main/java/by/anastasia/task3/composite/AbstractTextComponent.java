package by.anastasia.task3.composite;

import java.util.List;

public abstract class AbstractTextComponent implements TextComponent {
    private TextType type;

    public AbstractTextComponent(TextType type) {
        this.type = type;
    }

    public TextType getType() {
        return type;
    }

    public abstract void add(AbstractTextComponent component);

    public abstract void remove(AbstractTextComponent component);

    public abstract AbstractTextComponent getElement(int index);

    public abstract List<AbstractTextComponent> getComponents();
}