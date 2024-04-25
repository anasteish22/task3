package by.anastasia.task3.composite;

import java.util.List;

public interface TextComponent {
    void add(AbstractTextComponent component);

    void remove(AbstractTextComponent component);

    AbstractTextComponent getElement(int index);

    List<AbstractTextComponent> getComponents();
}
