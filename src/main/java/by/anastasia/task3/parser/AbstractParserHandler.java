package by.anastasia.task3.parser;

import by.anastasia.task3.composite.AbstractTextComponent;

public abstract class AbstractParserHandler {
    private AbstractParserHandler successor;

    public void setSuccessor(AbstractParserHandler successor) {
        this.successor = successor;
    }

    public AbstractParserHandler getSuccessor() {
        return successor;
    }

    public abstract void parse(String text, AbstractTextComponent composite);
}