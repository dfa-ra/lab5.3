package org.example.Comands.AllCommands;

import org.example.Collection.CollectionManager;
import org.example.Comands.Command;

/**
 * Класс команды добавления нового элемента в коллекцию
 * @author Захарченко Роман
 */
public class AddCommand implements Command {
    CollectionManager cm = new CollectionManager();
    @Override
    public void execute(String[] tokens) {
        if (tokens.length > 2){
            System.out.println("The 'add' command does not require 2 or more arguments. Try again.");
        }else if(tokens.length == 1) {
            cm.add(1L);
            System.out.println("Complete!");
        }
        else {
            try{
                if (Long.parseLong(tokens[1]) > 0) {
                    cm.add(Long.parseLong(tokens[1]));
                    System.out.println("Complete!");
                }
                else System.out.println("Error! id > 0");
            }catch (NumberFormatException nfe) {
                System.out.println("Argument type error. Try again!");
            }
        }
    }
}
