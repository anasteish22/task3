package by.anastasia.task3.parser;

import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.composite.TextComposite;
import by.anastasia.task3.composite.TextType;

public class ParagraphParser extends AbstractParserHandler {
    private static final String SENTENCE_REGEX = "(?<=\\.|\\?|!|\\.{3})\\s";

    public ParagraphParser() {
        this.successor = new SentenceParser();
    }

    @Override
    public void parse(String text, AbstractTextComponent composite) {
        String[] sentences = text.split(SENTENCE_REGEX);

        for (int i = 0; i < sentences.length; i++) {
            AbstractTextComponent sentenceComponent = new TextComposite(TextType.SENTENCE);
            composite.add(sentenceComponent);
            successor.parse(sentences[i], sentenceComponent);
        }
    }
}