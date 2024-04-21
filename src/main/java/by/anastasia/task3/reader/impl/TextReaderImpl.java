package by.anastasia.task3.reader.impl;

import by.anastasia.task3.exception.TextException;
import by.anastasia.task3.reader.TextReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.stream.Collectors;

public class TextReaderImpl implements TextReader {
    static final Logger LOGGER = LogManager.getLogger();
    private static final String SPACE_DELIMITER = " ";

    @Override
    public String readFromFile(String filepath) throws TextException {
        File file = new File(filepath);
        String text;

        if (!file.exists() || file.length() == 0) {
            LOGGER.log(Level.ERROR, "File " + filepath + " is empty or doesn't exist");
            text = "";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            text = reader.lines().map(str -> str + SPACE_DELIMITER).collect(Collectors.joining());
        } catch (IOException e) {
            throw new TextException("File " + filepath + " can't be read.");
        }
        return text;
    }
}
