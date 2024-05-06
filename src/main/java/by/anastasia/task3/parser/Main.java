package by.anastasia.task3.parser;

import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.composite.TextComposite;
import by.anastasia.task3.composite.TextType;
import by.anastasia.task3.exception.TextException;
import by.anastasia.task3.reader.TextReader;
import by.anastasia.task3.reader.impl.TextReaderImpl;

public class Main {
    public static void main(String[] args) throws TextException {
        TextReader reader = new TextReaderImpl();
        String text = reader.readFromFile("text/text.txt");

        AbstractParserHandler handler = new TextParser();
        AbstractTextComponent component = new TextComposite(TextType.TEXT);
        handler.parse(text, component);
        System.out.println(component);
    }
}
