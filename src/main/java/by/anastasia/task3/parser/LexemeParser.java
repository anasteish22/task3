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
    private static final String WORD_REGEX = "[a-zA-Zа-яА-Я]+";
    private static final String LETTER_REGEX = "[a-zA-Zа-яА-Я]";
    private static final String PUNCTUATION_REGEX = "\\p{Punct}";
    private static final String NUMBER_REGEX = "\\d";

    public LexemeParser() {
        setSuccessor(new WordParser());
    }

    @Override
    public void parse(String text, AbstractTextComponent composite) {
        LOGGER.log(Level.DEBUG, "Start lexeme parsing");

        AbstractTextComponent lexeme = new TextComposite(TextType.LEXEME);

        Pattern wordPattern = Pattern.compile(WORD_REGEX);
        Matcher wordMatcher = wordPattern.matcher(text);

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            String strCh = String.valueOf(ch);

            SymbolLeaf symbol = new SymbolLeaf(ch);

            if (strCh.matches(NUMBER_REGEX)) {
                symbol.setType(TextType.NUMBER);
                composite.add(symbol);
            } else if (strCh.matches(PUNCTUATION_REGEX)) {
                symbol.setType(TextType.PUNCTUATION);
                composite.add(symbol);
            } else if (wordMatcher.find()) {
                String word = wordMatcher.group();
                TextComposite wordComponent = new TextComposite(TextType.WORD);
                composite.add(wordComponent);
                getSuccessor().parse(word, wordComponent);

                if (strCh.matches(NUMBER_REGEX)) {
                    symbol.setType(TextType.NUMBER);
                    composite.add(symbol);
                } else if (strCh.matches(PUNCTUATION_REGEX)) {
                    symbol.setType(TextType.PUNCTUATION);
                    composite.add(symbol);
                } else {
                    LOGGER.log(Level.WARN, "Unknown symbol");
                }
                LOGGER.log(Level.DEBUG, "Finish lexeme parsing");
            }
        }
    }
}