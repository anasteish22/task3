package by.anastasia.task3.parser;

import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.composite.SymbolLeaf;
import by.anastasia.task3.composite.TextType;

public class WordParser extends AbstractParserHandler {

    @Override
    public void parse(String text, AbstractTextComponent composite) {
        char[] letters = text.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            AbstractTextComponent letterComponent = new SymbolLeaf(TextType.LETTER, letters[i]);
            composite.add(letterComponent);
        }
    }
}