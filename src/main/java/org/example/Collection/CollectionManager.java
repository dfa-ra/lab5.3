package org.example.Collection;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.example.Collection.Parser.XmlManager;
import org.example.enum_.TicketType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Класс работы с коллекцией (добавление, обновление, информация и т.д.)
 * @author Захарченко Роман
 */
@Getter
@Setter
public class CollectionManager implements InputInfo{
    /**
     * @return HashSet коллекция состоящая из ticket-ов
     */
    static HashSet<Ticket> notebook = new HashSet<>();
    /**
     * Дата инициализации коллекции в программе
     */
    static Date date = new Date();

    /**
     * Метод инициализации коллекции notebook
     * @param notebook коллекция которую надо инициализировать
     */
    public static void setNotebook(HashSet<Ticket> notebook) {
        CollectionManager.notebook = notebook;
    }

    static HashSet<Ticket> getNotebook(){
        return notebook;
    }
    /**
     * Метод, который выводит информацию о коллекции.
     */
    public void info(){
        if (!notebook.isEmpty()){
            System.out.println("Type: " + notebook.iterator().next().getClass());
        }
        System.out.println("Date: " + date);
        System.out.println("Lenght: " + notebook.size());
    }

    /**
     * Метод выводящий в стандартный поток вывода все элементы коллекции в строковом представлении
     */
    public void show(){
        if (notebook.isEmpty()){
            System.out.println("Collection empty");
        }
        else {
            Iterator<Ticket> i = notebook.iterator();
            while (i.hasNext()) {
                System.out.println(i.next());
            }
        }
    }




    /**
     * Метод добавляет новый элемент в коллекцию.
     */
    public void add(Long id) {
        Scanner in = new Scanner(System.in);
        Long oldid = Long.valueOf(0);;
        //id
        while (id != oldid) {
            oldid = id;
            Iterator<Ticket> i = notebook.iterator();
            while (i.hasNext()) {
                Ticket tmp = i.next();
                if (tmp.getId().equals(id)) {
                    id++;
                }
            }
        }
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedatetime = ZonedDateTime.of(date, time, zoneId);
        Ticket ticket = new Ticket(id, addStr("name", false), addCoordinates(), zonedatetime, addPrice(), addStr("comment", true), addRefundable(), addTicketType(), null);
        System.out.print("Would you like to enter a description of the person?(y/anything): ");
        String answ = in.next();
        if (answ.equals("y")) ticket.setPerson(new Person(addBirthDay(), addHaitColor(), addLocation()));
        notebook.add(ticket);
    }

    /**
     * Метод, который обновляет элемент коллекции по-заданному id.
     * @param id id элемента который надо обновить.
     */
    public void update(long id){
        Iterator<Ticket> i = notebook.iterator();
        while (i.hasNext()) {
            Ticket tmp = i.next();
            if (tmp.getId() == id){
                Scanner in = new Scanner(System.in);
                notebook.remove(tmp);
                //name
                ZonedDateTime zonedatetime = tmp.getCreationDate();
                Ticket ticket = new Ticket(id, addStr("name", false), addCoordinates(), zonedatetime, addPrice(), addStr("comment", true), addRefundable(), addTicketType(), null);
                System.out.print("Would you like to enter a description of the person?(y/anything): ");
                String answ = in.next();
                if (answ.equals("y")) ticket.setPerson(new Person(addBirthDay(), addHaitColor(), addLocation()));
                System.out.println("end");
                return;
            }
        }
        System.out.println("No such ID found. Try again!");
    }

    /**
     * Метод удаления элемента коллекции по id.
     * @param id id элемента который надо удалить
     */
    public void removeById(long id){
        Iterator<Ticket> i = notebook.iterator();
        while (i.hasNext()) {
            Ticket tmp = i.next();
            if (tmp.getId() == id){
                notebook.remove(tmp);
                System.out.println("Complete!");
                return;
            }
        }
        System.out.println("No such ID found. Try again!");
    }

    /**
     * Метод очистки коллекции.
     */
    public void clear(){
        notebook.clear();
        System.out.println("Collection cleared");
    }

    /**
     * Метод, который удаляет все элементы id больше заданного.
     * @param id заданный id.
     */
    public void removeGreater(long id){
        Iterator<Ticket> i = notebook.iterator();
        boolean flag = false;
        while (i.hasNext()) {
            Ticket tmp = i.next();
            if (tmp.getId() > id ){
                flag = true;
                i.remove();
            }
        }
        if (!flag) System.out.println("No IDs were found that matched the requirements. Try again!");
    }

    /**
     * Метод, который удаляет все элементы id меньше заданного.
     * @param id заданный id.
     */
    public void removeLower(long id){
        Iterator<Ticket> i = notebook.iterator();
        boolean flag = false;
        while (i.hasNext()) {
            Ticket tmp = i.next();
            if (tmp.getId() < id ){
                flag = true;
                i.remove();
            }
        }
        if (!flag) System.out.println("No IDs were found that matched the requirements. Try again!");
    }

    /**
     * Метод сгруппировывающий элементы коллекции по значению поля type и выводящий количество элементов в каждой группе.
     */
    public void groupCountingByType(){
        HashMap<TicketType, List<Ticket>> group = new HashMap<>();
        Iterator<Ticket> i = notebook.iterator();
        while (i.hasNext()) {
            Ticket tmp = i.next();
            if (group.containsKey(tmp.getType())){
                group.get(tmp.getType()).add(tmp);
            }
            else{
                group.put(tmp.getType(), new ArrayList<Ticket>(){{
                    add(tmp);
                }});
            }
        }
        for (TicketType key : group.keySet()) {
            System.out.println(key + ": " + group.get(key).size());
        }

    }

    /**
     * Метод выводит элементы коллекции в порядке возрастания.
     */
    public void printAscending() {
        List<Ticket> sortedList = new ArrayList<>(notebook);
        Collections.sort(sortedList);
        System.out.println(sortedList);
    }

    /**
     * Метод выводит отсортированные по id элементы коллекции имеющие заданный тип.
     * @param type тип для которого надо вывести элементы коллекции.
     */
    public void printAllType(TicketType type){
        Iterator<Ticket> i = notebook.iterator();
        ArrayList<Long> list = new ArrayList<>();
        System.out.print(type.toString() + ": ");
        while (i.hasNext()) {
            Ticket tmp = i.next();
            if (tmp.getType() == type ){
                list.add(tmp.getId());
            }
        }
        Comparator<? super Long> comparator =  new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return (int) (o1 - o2);
            }
        };
        list.sort(comparator);
        System.out.println(list);
    }

    /**
     * Метод выводит id всех типов билетов по группам
     */
    public void printFieldType(){
        printAllType(TicketType.VIP);
        printAllType(TicketType.USUAL);
        printAllType(TicketType.BUDGETARY);
        printAllType(TicketType.CHEAP);
    }

    /**
     * Метод выводит значения поля type всех элементов в порядке убывания
     */
    public void printFieldDescendingType(){
        List<Ticket> sortedList = new ArrayList<>(notebook);
        Collections.sort(sortedList);
        for (int i = sortedList.size()-1; i >=0 ; i--) {
            System.out.println(sortedList.get(i).getType().toString());
        }
    }

    /**
     * Метод, который сохраняет коллекцию.
     * @throws Exception
     */
    public void saveCollection() throws Exception {
        KeepCollection keepCollection = new KeepCollection();
        keepCollection.setTicket(notebook);
        XmlManager.mySecondSaveCollection(keepCollection);
    }
}
