package goit.control;

import goit.command.Command;
import goit.command.Help;
import goit.command.pet.*;
import goit.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    View view;
    private List<Command> commands;

    public Controller(View view) {
        this.view = view;
        this.commands = new ArrayList<>(Arrays.asList(
                new Help(view),
                new Get(view),
                new Delete(view),
                new Create(view),
                new Update(view),
                new Upload(view)

        ));
    }

    public void run() {
        view.write("Welcome to the application");
        doCommand();
    }

    private void doCommand() {
        boolean isNotExit = true;
        while (isNotExit) {
            view.write("Please enter a command or help to retrieve command list");
            String inputCommand = view.read();
            for (Command command : commands) {
                if (command.canProcess(inputCommand)) {

                        command.process();
                        break;


                } else if (inputCommand.equalsIgnoreCase("exit")) {
                    view.write("Good Bye!");
                    isNotExit = false;
                    break;
                }
            }
        }
    }
}
