package org.example.Comands.AllCommands;

import org.example.Comands.Command;
import org.example.Comands.Invoker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс команды исполнения скрипта
 * @author Захарченко Роман
 */
public class ExecuteScriptCommand implements Command {
    BufferedReader reader;

    @Override
    public void execute(String[] tokens) {
        if (tokens.length > 2){
            System.out.println("The 'execute_script' command does not require 2 or more arguments. Try again.");
        }else if(tokens.length == 1) System.out.println("This command requires an argument. Try again!");
        else {
            Invoker invk = new Invoker();
            try {
                reader = new BufferedReader(new FileReader(tokens[1]));
                String line = reader.readLine();

                while (line != null) {
                    System.out.println("--- " + line);
                    if (line.equals("execute_script " + tokens[1])){
                        System.out.println("Recursion!!");
                        return;
                    }
                    invk.commandSelectionByStr(line);
                    line = reader.readLine();
                }

                reader.close();
                System.out.println("Complete!");

            } catch (IOException e) {
                System.out.println("This file does not exist, try again!");
            } catch (Exception e) {
                System.out.println("Error!! Try again!");
            }

        }
    }
}
