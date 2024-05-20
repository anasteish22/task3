package by.anastasia.task3.parser;

import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.composite.SymbolLeaf;
import by.anastasia.task3.composite.TextComposite;
import by.anastasia.task3.composite.TextType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParserHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String WORD_DELIMITER = "(?=[,.!?)])";
    private static final String PUNCTUATION_REGEX = "\\p{Punct}";

    public LexemeParser() {
        setSuccessor(new WordParser());
    }

    @Override
    public void parse(String text, AbstractTextComponent composite) {
        LOGGER.log(Level.DEBUG, "Start lexeme parsing");

        String[] words = text.split(WORD_DELIMITER);

        for (String word: words) {
            if (word.matches(PUNCTUATION_REGEX)) {
                char ch = word.charAt(0);
                SymbolLeaf symbol = new SymbolLeaf(TextType.PUNCTUATION, ch);
                composite.add(symbol);
                System.out.println(ch);
            } else {
                AbstractTextComponent wordComponent = new TextComposite(TextType.WORD);
                composite.add(wordComponent);
                System.out.println(word);
                getSuccessor().parse(word, wordComponent);
            }
        }
        LOGGER.log(Level.DEBUG, "Finish lexeme parsing");
    }
}