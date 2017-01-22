package com.harsain;

/**
 * Created by harsain on 21/1/17.
 */
public class CommandBroker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void execute(String arguments) throws Exception {
        this.command.execute(arguments);
    }
}
