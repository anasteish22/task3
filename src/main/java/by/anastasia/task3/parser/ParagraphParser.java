package by.anastasia.task3.parser;

import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.composite.TextComposite;
import by.anastasia.task3.composite.TextType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser extends AbstractParserHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SENTENCE_REGEX = "(?<=\\.|\\?|!|\\.{3})\\s";

    public ParagraphParser() {
        setSuccessor(new SentenceParser());
    }

    @Override
    public void parse(String text, AbstractTextComponent composite) {
        LOGGER.log(Level.DEBUG, "Start paragraph parsing");
        String[] sentences = text.split(SENTENCE_REGEX);

        for (String sentence : sentences) {
            AbstractTextComponent sentenceComponent = new TextComposite(TextType.SENTENCE);
            composite.add(sentenceComponent);
            getSuccessor().parse(sentence, sentenceComponent);
        }
        LOGGER.log(Level.DEBUG, "Finish paragraph parsing");
    }
}