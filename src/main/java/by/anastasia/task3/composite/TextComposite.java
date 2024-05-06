package by.anastasia.task3.composite;

import java.util.ArrayList;
import java.util.List;

public class TextComposite extends AbstractTextComponent {
    private List<AbstractTextComponent> components;

    public TextComposite(TextType type) {
        super(type);
        components = new ArrayList<>();
    }

    @Override
    public void add(AbstractTextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(AbstractTextComponent component) {
        components.remove(component);
    }

    @Override
    public AbstractTextComponent getElement(int index) {
        return components.get(index);
    }

    public List<AbstractTextComponent> getComponents() {
        return components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextComposite that = (TextComposite) o;

        return (components != null ? !components.equals(that.components) : that.components != null);
    }

    @Override
    public int hashCode() {
        return components != null ? components.hashCode() : 0;
    }

    @Override
    public String toString() {
        String text = "";
        for (AbstractTextComponent component:components) {
            text += component.toString();
        }
        return text;
    }
}