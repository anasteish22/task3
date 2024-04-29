package by.anastasia.task3.comparator;

import by.anastasia.task3.composite.AbstractTextComponent;

import java.util.Comparator;

public class ParagraphComparator implements Comparator<AbstractTextComponent> {
    @Override
    public int compare(AbstractTextComponent firstComposite, AbstractTextComponent secondComposite) {
        int firstCompositeSize = firstComposite.getComponents().size();
        int secondCompositeSize = firstComposite.getComponents().size();

        return firstCompositeSize - secondCompositeSize;
    }
}
