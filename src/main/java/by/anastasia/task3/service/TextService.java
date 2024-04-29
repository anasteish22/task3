package by.anastasia.task3.service;

import by.anastasia.task3.comparator.ParagraphComparator;
import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.exception.TextException;

import java.util.List;

public interface TextService {
    List<AbstractTextComponent> sortParagraphs(AbstractTextComponent composite, ParagraphComparator comparator) throws TextException;

    List<AbstractTextComponent> findSentenceWithLongestWord(AbstractTextComponent composite) throws TextException;

    List<AbstractTextComponent> removeSentences(AbstractTextComponent composite, int wordsAmount) throws TextException;

    int countIdenticalWords(AbstractTextComponent composite) throws TextException;

    int countVowels(AbstractTextComponent composite) throws TextException;

    int countConsonants(AbstractTextComponent composite) throws TextException;
}
