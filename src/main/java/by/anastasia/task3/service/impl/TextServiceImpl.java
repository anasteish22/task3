package by.anastasia.task3.service.impl;

import by.anastasia.task3.comparator.ParagraphComparator;
import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.composite.TextType;
import by.anastasia.task3.exception.TextException;
import by.anastasia.task3.service.TextService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String VOWEL_REGEX_ENG = "[aAeEiIoOuUyYа]";
    private static final String VOWEL_REGEX_RUS = "[АеЕёЁиИоОуУэЭыЫюЮ]";
    @Override
    public List<AbstractTextComponent> sortParagraphs(AbstractTextComponent composite, ParagraphComparator comparator) throws TextException {
        if (composite.getType() != TextType.TEXT) {
            LOGGER.log(Level.ERROR, "Wrong text type: " + composite.getType() + ", but 'TEXT' was expected.");
            throw new TextException("Wrong text type: " + composite.getType() + ", but 'TEXT' was expected.");
        }
        List<AbstractTextComponent> textComponents = composite.getComponents();
        List<AbstractTextComponent> sortedTextComponents = textComponents.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        return sortedTextComponents;
    }

    @Override
    public List<AbstractTextComponent> findSentenceWithLongestWord(AbstractTextComponent composite) throws TextException {
        return null;
    }

    @Override
    public AbstractTextComponent removeSentences(AbstractTextComponent composite, int wordsAmount) throws TextException {
        return null;
    }

    @Override
    public Map<String, Integer> findAndCountIdenticalWords(AbstractTextComponent composite) throws TextException {
        if (composite.getType() != TextType.TEXT) {
            LOGGER.log(Level.ERROR, "Wrong text type: " + composite.getType() + ", but 'TEXT' was expected.");
            throw new TextException("Wrong text type: " + composite.getType() + ", but 'TEXT' was expected.");
        }

        Map<String, Integer> map = new HashMap<>();
        List<AbstractTextComponent> textComponents = composite.getComponents();
        textComponents.stream()
                .flatMap(paragraph -> paragraph.getComponents().stream())
                .flatMap(sentence -> sentence.getComponents().stream())
                .flatMap(lexeme -> lexeme.getComponents().stream())
                .filter(word -> word.getType() == TextType.WORD)
                .forEach(word -> {
                    String key = word.toString().toLowerCase();
                    int count = map.getOrDefault(key, 0);
                    map.put(key, ++count);
                });
        Set<Map.Entry<String, Integer>> set = map.entrySet().stream()
                .filter(element -> element.getValue() > 1)
                .collect(Collectors.toSet());
        Map<String, Integer> resultMap = set.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return resultMap;
    }

    @Override
    public int countVowels(AbstractTextComponent composite) throws TextException {
        if (composite.getType() != TextType.SENTENCE) {
            LOGGER.log(Level.ERROR, "Wrong text type: " + composite.getType() + ", but 'SENTENCE' was expected.");
            throw new TextException("Wrong text type: " + composite.getType() + ", but 'SENTENCE' was expected.");
        }

        List<AbstractTextComponent> sentenceComponents = composite.getComponents();
        int vowelsAmount = (int) sentenceComponents.stream()
                .flatMap(lexeme -> lexeme.getComponents().stream())
                .filter(word -> word.getType() == TextType.WORD)
                .flatMap(word -> word.getComponents().stream())
                .filter(symbol -> symbol.getType() == TextType.LETTER)
                .filter(letter -> Pattern.matches(VOWEL_REGEX_ENG, letter.toString()) || Pattern.matches(VOWEL_REGEX_RUS, letter.toString()))
                .count();
        return vowelsAmount;
    }

    @Override
    public int countConsonants(AbstractTextComponent composite) throws TextException {
        if (composite.getType() != TextType.SENTENCE) {
            LOGGER.log(Level.ERROR, "Wrong text type: " + composite.getType() + ", but 'SENTENCE' was expected.");
            throw new TextException("Wrong text type: " + composite.getType() + ", but 'SENTENCE' was expected.");
        }

        List<AbstractTextComponent> sentenceComponents = composite.getComponents();
        int consonantsAmount = (int) sentenceComponents.stream()
                .flatMap(lexeme -> lexeme.getComponents().stream())
                .filter(word -> word.getType() == TextType.WORD)
                .flatMap(word -> word.getComponents().stream())
                .filter(symbol -> symbol.getType() == TextType.LETTER)
                .filter(letter -> !Pattern.matches(VOWEL_REGEX_ENG, letter.toString()) && !Pattern.matches(VOWEL_REGEX_RUS, letter.toString()))
                .count();
        return consonantsAmount;
    }
}
