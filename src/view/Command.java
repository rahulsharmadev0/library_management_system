package view;



@FunctionalInterface
public interface Command {
    void execute() throws Exception;
}
