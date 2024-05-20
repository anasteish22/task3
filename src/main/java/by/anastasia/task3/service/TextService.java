package by.anastasia.task3.service;

import by.anastasia.task3.comparator.ParagraphComparator;
import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.exception.TextException;

import java.util.List;
import java.util.Map;

public interface TextService {
    List<AbstractTextComponent> sortParagraphs(AbstractTextComponent composite, ParagraphComparator comparator) throws TextException;

    List<AbstractTextComponent> findSentenceWithLongestWord(AbstractTextComponent composite) throws TextException;

    List<AbstractTextComponent> removeSentences(AbstractTextComponent composite, int wordsAmount) throws TextException;

    Map<String, Integer> findAndCountIdenticalWords(AbstractTextComponent composite) throws TextException;

    int countVowels(AbstractTextComponent composite) throws TextException;

    int countConsonants(AbstractTextComponent composite) throws TextException;
}
