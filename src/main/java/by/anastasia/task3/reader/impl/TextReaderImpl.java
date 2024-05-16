package by.anastasia.task3.reader.impl;

import by.anastasia.task3.exception.TextException;
import by.anastasia.task3.reader.TextReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class TextReaderImpl implements TextReader {
    static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String readFromFile(String filepath) throws TextException {
        Path path = Path.of(filepath);
        String text;

        if (!Files.exists(path)) {
            LOGGER.log(Level.ERROR, "File " + filepath + " is empty or doesn't exist");
            throw new TextException("File " + filepath + " is empty or doesn't exist");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            text = reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new TextException("File " + filepath + " can't be read.", e);
        }
        return text;
    }
}