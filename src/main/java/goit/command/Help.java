package goit.command;

import goit.view.View;

import java.io.IOException;

public class Help implements Command{
View view;
public Help(View view){
    this.view=view;
}
    @Override
    public void process() {
        view.write("help - show a list of commands");
        view.write("create - create a: pet, order, user,user with Array");
        view.write("get - get: pet by id, pet by status, order by id, order inventory, user by username, user login, user logout");
        view.write("update - update: pet, user");
        view.write("upload - upload pet's image");

    }

    @Override
    public String commandName() {
        return "help";
    }
}
