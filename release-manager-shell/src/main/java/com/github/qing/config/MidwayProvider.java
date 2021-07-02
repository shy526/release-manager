package com.github.qing.config;

import org.jline.reader.LineReader;
import org.jline.reader.UserInterruptException;
import org.jline.utils.AttributedString;
import org.springframework.shell.ExitRequest;
import org.springframework.shell.Input;
import org.springframework.shell.ResultHandler;
import org.springframework.shell.jline.PromptProvider;

/**
 * 获去命令中途输入输出的能力
 *
 * @author qing
 */

public class MidwayProvider {
    private final LineReader lineReader;

    private final ResultHandler resultHandler;

    public MidwayProvider(LineReader lineReader, ResultHandler resultHandler) {
        this.lineReader = lineReader;
        this.resultHandler = resultHandler;
    }


    public Input readInput(PromptProvider promptProvider) {
        if (promptProvider != null) {
            try {
                AttributedString prompt = promptProvider.getPrompt();
                this.lineReader.readLine(prompt.toAnsi(this.lineReader.getTerminal()));
            } catch (UserInterruptException var2) {
                if (var2.getPartialLine().isEmpty()) {
                    throw new ExitRequest(1);
                }
                return Input.EMPTY;
            }
        }
        return new MyParsedLineInput(this.lineReader.getParsedLine());
    }

    public void writerOutput(Object result) {
        resultHandler.handleResult(result);
    }

}
