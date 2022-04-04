package goit.command;

import lombok.SneakyThrows;

import java.io.IOException;

public interface Command {
    @SneakyThrows
    void process();

    String commandName();

    default boolean canProcess(String command) {
        return commandName().equals(command);
    }
}
