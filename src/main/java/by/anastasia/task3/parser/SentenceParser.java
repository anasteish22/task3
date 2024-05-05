package by.anastasia.task3.parser;

import by.anastasia.task3.composite.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser extends AbstractParserHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String LEXEME_REGEX = "\\s";

    public SentenceParser() {
        setSuccessor(new LexemeParser());
    }

    @Override
    public void parse(String text, AbstractTextComponent composite) {
        LOGGER.log(Level.DEBUG, "Start sentence parsing");

        String[] lexemes = text.split(LEXEME_REGEX);

        for (String lexeme : lexemes) {
            AbstractTextComponent lexemeComponent = new TextComposite(TextType.LEXEME);
            composite.add(lexemeComponent);
            getSuccessor().parse(lexeme, lexemeComponent);
        }

        LOGGER.log(Level.DEBUG, "Finish sentence parsing");
    }
}