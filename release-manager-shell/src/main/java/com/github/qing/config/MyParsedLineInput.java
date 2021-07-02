package com.github.qing.config;

import org.jline.reader.ParsedLine;
import org.springframework.shell.Input;

import java.util.List;

/**
 *h
 * @author qing
 */
public class MyParsedLineInput implements Input {
    private final ParsedLine parsedLine;

    public MyParsedLineInput(ParsedLine parsedLine) {
        this.parsedLine = parsedLine;
    }

    @Override
    public String rawText() {
        return this.parsedLine.line();
    }

    @Override
    public List<String> words() {
        return this.parsedLine.words();
    }
}
