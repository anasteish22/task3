package by.anastasia.task3.parser;

import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.composite.SymbolLeaf;
import by.anastasia.task3.composite.TextType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParser extends AbstractParserHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String LETTER_REGEX = "[a-zA-Zа-яА-Я]";
    private static final String PUNCTUATION_REGEX = "\\p{Punct}|“|”|.";
    private static final String NUMBER_REGEX = "\\d";

    @Override
    public void parse(String text, AbstractTextComponent composite) {
        LOGGER.log(Level.DEBUG, "Start word parsing");
        char[] symbols = text.toCharArray();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            String strCh = String.valueOf(ch);

            if (strCh.matches(NUMBER_REGEX)) {
                SymbolLeaf symbol = new SymbolLeaf(TextType.NUMBER, ch);
                composite.add(symbol);
            } else if (strCh.matches(LETTER_REGEX)) {
                SymbolLeaf symbol = new SymbolLeaf(TextType.LETTER, ch);
                composite.add(symbol);
            } else if (strCh.matches(PUNCTUATION_REGEX)) {
                SymbolLeaf symbol = new SymbolLeaf(TextType.PUNCTUATION, ch);
                composite.add(symbol);
            } else {
                LOGGER.log(Level.WARN, "Unknown symbol");
            }
        }
        LOGGER.log(Level.DEBUG, "Finish word parsing");
    }
}