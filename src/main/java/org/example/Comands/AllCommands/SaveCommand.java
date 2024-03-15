package org.example.Comands.AllCommands;

import org.example.Collection.CollectionManager;
import org.example.Comands.Command;

/**
 * Класс команды сохранения коллекции
 * @author Захарченко Роман
 */
public class SaveCommand implements Command {
    CollectionManager cm = new CollectionManager();
    @Override
    public void execute(String[] tokens) throws Exception {
        if (tokens.length > 1){
            System.out.println("The 'save' command requires no arguments. Try again.");
        }
        else {
            cm.saveCollection();
            System.out.println("Complete!");
        }
    }
}
