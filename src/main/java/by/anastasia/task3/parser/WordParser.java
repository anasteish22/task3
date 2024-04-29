package by.anastasia.task3.parser;

import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.composite.SymbolLeaf;
import by.anastasia.task3.composite.TextType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParser extends AbstractParserHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void parse(String text, AbstractTextComponent composite) {
        LOGGER.log(Level.DEBUG, "Start word parsing");
        char[] letters = text.toCharArray();

        for (char letter : letters) {
            AbstractTextComponent letterComponent = new SymbolLeaf(TextType.LETTER, letter);
            composite.add(letterComponent);
        }
        LOGGER.log(Level.DEBUG, "Finish word parsing");
    }
}