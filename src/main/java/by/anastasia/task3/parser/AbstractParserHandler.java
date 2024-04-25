package by.anastasia.task3.parser;

import by.anastasia.task3.composite.AbstractTextComponent;
import by.anastasia.task3.composite.TextComponent;
import by.anastasia.task3.composite.TextComposite;

import java.util.List;

public abstract class AbstractParserHandler {
    protected AbstractParserHandler successor;

    public abstract void parse(String text, AbstractTextComponent composite);
}