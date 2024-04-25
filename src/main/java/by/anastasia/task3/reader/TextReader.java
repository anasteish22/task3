package by.anastasia.task3.reader;

import by.anastasia.task3.exception.TextException;

public interface TextReader {
    String readFromFile(String filepath) throws TextException;
}