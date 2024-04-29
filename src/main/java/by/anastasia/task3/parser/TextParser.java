package by.anastasia.task3.parser;

import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.composite.TextComposite;
import by.anastasia.task3.composite.TextType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser extends AbstractParserHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAGRAPH_REGEX = "(?!^)(?m)(?=^\\s{4})";

    public TextParser() {
        setSuccessor(new ParagraphParser());
    }

    @Override
    public void parse(String text, AbstractTextComponent composite) {
        LOGGER.log(Level.DEBUG, "Start text parsing");
        String[] paragraphs = text.split(PARAGRAPH_REGEX);

        for (String paragraph : paragraphs) {
            AbstractTextComponent paragraphComponent = new TextComposite(TextType.PARAGRAPH);
            composite.add(paragraphComponent);
            getSuccessor().parse(paragraph, paragraphComponent);
        }
        LOGGER.log(Level.DEBUG, "Finish text parsing");
    }
}