package org.example.Comands.AllCommands;

import org.example.Collection.CollectionManager;
import org.example.Comands.Command;

/**
 * Класс команды вывода информации о коллекции
 * @author Захарченко Роман
 */
public class InfoCommand implements Command {
    CollectionManager cm = new CollectionManager();
    @Override
    public void execute(String[] tokens) {
        if (tokens.length > 1){
            System.out.println("The 'info' command requires no arguments. Try again.");
        }
        else {
            cm.info();
        }
    }
}
