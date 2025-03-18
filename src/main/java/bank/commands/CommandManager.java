package bank.commands;

import java.util.Stack;

public class CommandManager implements ICommandManager {
    
    private Stack<ICommand> commandHistoryStack;
    private Stack<ICommand> undoCommandStack;
    public CommandManager() {
		this.commandHistoryStack = new Stack<>();
		this.undoCommandStack = new Stack<>();
	}

    @Override
	public void executeCommand(ICommand command){
        if (command != null) {
            command.execute();
            this.commandHistoryStack.add(command);
        }
    }

    @Override
	public void undo(){
        if (!this.commandHistoryStack.isEmpty()) {
            ICommand command = this.commandHistoryStack.pop();
            command.unExecute();
            this.undoCommandStack.add(command);
        }
    }
    @Override
	public void redo(){
        if (!this.undoCommandStack.isEmpty()) {
            ICommand command = this.undoCommandStack.pop();
            command.execute();
            this.commandHistoryStack.add(command);
        }
    }
}
