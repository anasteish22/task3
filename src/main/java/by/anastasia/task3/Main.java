package by.anastasia.task3;

import by.anastasia.task3.comparator.ParagraphComparator;
import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.composite.TextComposite;
import by.anastasia.task3.composite.TextType;
import by.anastasia.task3.exception.TextException;
import by.anastasia.task3.parser.AbstractParserHandler;
import by.anastasia.task3.parser.ParagraphParser;
import by.anastasia.task3.parser.SentenceParser;
import by.anastasia.task3.parser.TextParser;
import by.anastasia.task3.reader.TextReader;
import by.anastasia.task3.reader.impl.TextReaderImpl;
import by.anastasia.task3.service.impl.TextServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws TextException {
        TextReader reader = new TextReaderImpl();
        String text = reader.readFromFile("text/text.txt");

        AbstractParserHandler handler = new TextParser();
        AbstractTextComponent component = new TextComposite(TextType.TEXT);
        handler.parse(text, component);
        System.out.println(component);

        AbstractParserHandler handler1 = new ParagraphParser();
        AbstractTextComponent component1 = new TextComposite(TextType.PARAGRAPH);
        handler1.parse(text, component1);

        ParagraphComparator comparator = new ParagraphComparator();
        TextServiceImpl textService = new TextServiceImpl();
        System.out.println(textService.sortParagraphs(component, comparator));
        System.out.println(textService.countVowels(component1.getElement(0)));
        System.out.println(textService.countConsonants(component1.getElement(0)));
        System.out.println(textService.findAndCountIdenticalWords(component));
        System.out.println(textService.removeSentences(component, 10));
        System.out.println(textService.findSentenceWithLongestWord(component));
    }
}
