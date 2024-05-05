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
    private static final String WORD_REGEX = "[a-zA-Z]+";
    private static final String PUNCTUATION_REGEX = "\\p{Punct}";
    private static final String NUMBER_REGEX = "\\d";

    public LexemeParser() {
        setSuccessor(new WordParser());
    }

    @Override
    public void parse(String text, AbstractTextComponent composite) {
        LOGGER.log(Level.DEBUG, "Start lexeme parsing");

        Pattern numberPattern = Pattern.compile(NUMBER_REGEX);
        Matcher numberMatcher = numberPattern.matcher(text);

        Pattern wordPattern = Pattern.compile(WORD_REGEX);
        Matcher wordMatcher = wordPattern.matcher(text);

        Pattern punctuationPattern = Pattern.compile(PUNCTUATION_REGEX);
        Matcher punctuationMatcher = punctuationPattern.matcher(text);

        while (numberMatcher.find()) {
            String numberStr = numberMatcher.group();
            char number = numberStr.charAt(0);
            AbstractTextComponent numberComponent = new SymbolLeaf(TextType.NUMBER, number);
            composite.add(numberComponent);
        }

        while (wordMatcher.find()) {
            String word = wordMatcher.group();
            TextComposite wordComponent = new TextComposite(TextType.WORD);
            composite.add(wordComponent);
            getSuccessor().parse(word, wordComponent);
        }

        while (punctuationMatcher.find()) {
            String punctuationStr = punctuationMatcher.group();
            char punctuation = punctuationStr.charAt(0);
            AbstractTextComponent punctuationComponent = new SymbolLeaf(TextType.PUNCTUATION, punctuation);
            composite.add(punctuationComponent);
        }

        LOGGER.log(Level.DEBUG, "Finish lexeme parsing");
    }
}
