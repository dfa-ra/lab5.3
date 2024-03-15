package org.example.Comands.AllCommands;

import org.example.Collection.CollectionManager;
import org.example.Comands.Command;

/**
 * Класс команды отчистки всей коллекции
 * @author Захарченко Роман
 */
public class ClearCommand implements Command {
    CollectionManager cm = new CollectionManager();
    @Override
    public void execute(String[] tokens) {
        if (tokens.length > 1){
            System.out.println("The 'clear' command requires no arguments. Try again.");
        }
        else {
            cm.clear();
            System.out.println("Complete!");
        }
    }
}
