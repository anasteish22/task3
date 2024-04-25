package by.anastasia.task3.parser;

import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.composite.TextComposite;
import by.anastasia.task3.composite.TextType;

public class TextParser extends AbstractParserHandler {
    private static final String PARAGRAPH_REGEX = "[\\n\\t]+";

    public TextParser() {
        this.successor = new ParagraphParser();
    }

    @Override
    public void parse(String text, AbstractTextComponent composite) {
        String[] paragraphs = text.split(PARAGRAPH_REGEX);

        for (int i = 0; i < paragraphs.length; i++) {
            AbstractTextComponent paragraphComponent = new TextComposite(TextType.PARAGRAPH);
            composite.add(paragraphComponent);
            successor.parse(paragraphs[i], paragraphComponent);
        }
    }
}