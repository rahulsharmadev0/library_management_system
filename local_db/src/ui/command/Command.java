package ui.command;

@FunctionalInterface
public interface Command {
    void execute() throws Exception;
}
