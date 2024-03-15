package org.example.Comands;

import org.example.Collection.*;
import org.example.Comands.AllCommands.*;

import java.util.*;

/**
 * Класс реализующий взаимодействие с пользователем.
 * @author Захарченко Роман
 */
public class Invoker {
    /**
     * Список всех введённых команд
     */
    protected List<String> history = new ArrayList<>();
    /**
     * Коллекция типа ключ значение ключ='названию команды' значение='класс исполнитель команды'
     */
    protected Map<String, Command> commands = new HashMap<>();

    /**
     * Конструктор в котором определяются все команды
     */
    public Invoker(){
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("exit", new ExitCommand());
        commands.put("show", new ShowCommand());
        commands.put("add", new AddCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("update", new UpdateCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("history", new HistoryCommand());
        commands.put("group_counting_by_type", new GroupCountingByTypeCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("remove_lower", new RemoveLowerCommand());
        commands.put("print_ascending", new PrintAscendingCommand());
        commands.put("print_field_descending_type", new PrintFieldDescendingTypeCommand());
        commands.put("print_field_type", new PrintFieldTypeCommand());
    }
    protected String[] tokens;

    /**
     * Метод реализующий ввод команды
     * @throws Exception
     */
    public void commandSelection() throws Exception {
        String str = "";
        Scanner in = new Scanner(System.in);
        while (true) {
            try{
                System.out.print("Input command(for help write 'help'): ");
                str = in.nextLine();
                commandSelectionByStr(str);
            } catch (NoSuchElementException e) {
                System.out.println("CTRL+D entered - program terminated");
                commandSelectionByStr("exit");
            }
        }
    }

    /**
     * Метод разделяющий введённую команду на слова, проверяющий существование команды и выполняющий её если она есть. Так же этот метод заносит данный элемент в историю команд.
     * @param str введённая строка
     * @throws Exception
     */
    public void commandSelectionByStr(String str) throws Exception {
        String[] tokens = str.split(" ");
        if (commands.containsKey(tokens[0])){
            Command command = commands.get(tokens[0]);
            command.execute(tokens);
            history.add(str);
        }
        else {
            System.out.println("This command does not exist. Using the 'help' command you can see all available commands.");
        }
    }

    /**
     * Метод реализующий команду истории. Выводящий последние 8 введённых команд.
     */
    public class HistoryCommand implements Command{
        @Override
        public void execute(String[] tokens) {
            int i = history.size() - 1;
            int j = 0;
            while (i-j >= 0) {
                System.out.println(i-j+") " + history.get(i-j));
                j++;
                if (j == 8) break;
            }
        }
    }

}
