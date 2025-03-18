package bank.commands;

public interface ICommandManager {

    void executeCommand(ICommand command);

    void undo();

    void redo();

}