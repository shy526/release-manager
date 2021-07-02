package com.github.sunjiaqing.config;

import org.jline.reader.LineReader;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.ResultHandler;
import org.springframework.shell.jline.PromptProvider;

/**
 * @author qing
 */
@Configuration
public class ShellConfig {
    @Autowired
    ApplicationContext applicationContext;
    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("release-manager:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }

    @Bean
    public MidwayProvider midwayInputProvider(@Lazy LineReader lineReader,@Qualifier("mainResultHandler")ResultHandler<?> resultHandler) {
        return new MidwayProvider(lineReader,resultHandler);
    }
}
