package by.anastasia.task3.parser;

import by.anastasia.task3.composite.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParserHandler {
    private static final String WORD_REGEX = "[a-zA-Z]+";
    private static final String PUNCTUATION_REGEX = "\\p{Punct}";

    public SentenceParser() {
        this.successor = new WordParser();
    }

    @Override
    public void parse(String text, AbstractTextComponent composite) {
        Pattern wordPattern = Pattern.compile(WORD_REGEX);
        Matcher wordMatcher = wordPattern.matcher(text);

        Pattern punctuationPattern = Pattern.compile(PUNCTUATION_REGEX);
        Matcher punctuationMatcher = punctuationPattern.matcher(text);

        while (wordMatcher.find()) {
            String word = wordMatcher.group();
            TextComposite wordComponent = new TextComposite(TextType.WORD);
            composite.add(wordComponent);
            successor.parse(word, wordComponent);
        }

        while (punctuationMatcher.find()) {
            String punctuationStr = punctuationMatcher.group();
            char punctuation = punctuationStr.charAt(0);
            AbstractTextComponent punctuationComponent = new SymbolLeaf(TextType.PUNCTUATION, punctuation);
            composite.add(punctuationComponent);
        }
    }
}