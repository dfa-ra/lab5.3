package org.example.Collection;

import org.example.enum_.Color;
import org.example.enum_.TicketType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public interface InputInfo {
    /**
     * Метод для работы с ведёнными строками.
     * @param about (String) что вводить?
     * @param canBeNull (boolean) может быть null или нет?
     * @return Метод возвращает введённую строку либо null если не ввели ничего
     */
    default String addStr(String about, boolean canBeNull){
        Scanner in = new Scanner(System.in);
        String str = "";
        System.out.print("Input " + about + ": ");
        str=in.nextLine();
        if (!canBeNull){
            while (str == "" || str.matches("^\\s*$")) {
                System.out.print("Input " + about + ": ");
                str=in.nextLine();
            }
        }
        else if (str == "" || str.matches("^\\s*$")) str = null;
        return str;
    }

    /**
     * Метод для введения координат.
     * @return Метод возвращает введённые координаты x и y.
     */
    default Coordinates addCoordinates(){
        Scanner in = new Scanner(System.in);
        int y;
        float x = 0;
        //Coordinates
        for(;;) {
            try {
                System.out.print("Input coordinates x (>-785): ");
                x = in.nextFloat();
                while (x <= -785) {
                    System.out.print("(>-785)!!! Input coordinates x : ");
                    x = in.nextFloat();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error! Invalid data type. Try again.");
                in.next();
            }
        }
        for(;;){
            try{
                System.out.print("Input coordinates y: ");
                y = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error! Invalid data type. Try again.");
                in.next();
            }
        }
        return new Coordinates(x, y);
    }

    /**
     * Метод для введения цены.
     * @return Метод возвращает введённую цену.
     */
    default Double addPrice(){
        Scanner in = new Scanner(System.in);
        Double price;
        for(;;){
            try{
                System.out.print("Input price x (>0): ");
                price = in.nextDouble();
                while (price < 0){
                    System.out.print("Input price x (>0): ");
                    price = in.nextDouble();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error! Invalid data type. Try again.");
                in.next();
            }
        }
        return price;
    }

    /**
     * Метод для введения возвратности билета.
     * @return Возвращает ответ (да или нет) на вопрос. Является ли билет возвратным?
     */
    default Boolean addRefundable(){
        Scanner in = new Scanner(System.in);
        Boolean refundable;
        String str;
        //refundable
        for(;;){
            System.out.print("Input refundable(can be null): ");
            str=in.nextLine();
            if (str.equals("true")) {
                refundable = true;
                break;
            }
            else if (str.equals("false")) {
                refundable = false;
                break;
            }
            else if (str.isEmpty()) {
                refundable = null;
                break;
            }
            else {
                System.out.println("Error! Available answers are true or false!");
            }
        }
        return refundable;
    }

    /**
     * Метод для ввода типа билета.
     * @return Возвращает тип билета.
     */
    default TicketType addTicketType(){
        System.out.println("1)" + TicketType.VIP);
        System.out.println("2)" + TicketType.CHEAP);
        System.out.println("3)" + TicketType.USUAL);
        System.out.println("4)" + TicketType.BUDGETARY);
        Scanner in = new Scanner(System.in);
        TicketType ticketType;
        for(;;){
            try{
                System.out.print("Enter one of these ticket types: ");
                String tmp = in.next();
                if (tmp == "") ticketType = null;
                else ticketType = TicketType.valueOf(tmp);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error! Select an option from the list above!");
            }
        }
        return ticketType;
    }

    /**
     * Метод для ввода даты рождения.
     * @return Возвращает введённую дату рождения
     */
    default Date addBirthDay(){
        Scanner in = new Scanner(System.in);
        String answBirthday = null;
        Date birthday = null;
        SimpleDateFormat birthdayFormat = new SimpleDateFormat("dd.MM.yyyy");
        while (true) {
            try {
                System.out.print("Input your birthday (dd.mm.yyyy): ");
                answBirthday = in.next();
                birthday = birthdayFormat.parse(answBirthday.trim());
                break;
            } catch (ParseException ignored) {
                System.out.println("try again");
            }
        }
        return birthday;
    }

    /**
     * Метод для ввода цвета волос.
     * @return Возвращает введённый цвет волос.
     */
    default Color addHaitColor(){
        System.out.println("1)" + Color.BLACK);
        System.out.println("2)" + Color.GREEN);
        System.out.println("3)" + Color.BLUE);
        Scanner in = new Scanner(System.in);
        Color hairColor;
        for(;;){
            try{
                System.out.print("Enter one of these hair color: ");
                hairColor = Color.valueOf(in.next());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error! Select an option from the list above!");
            }
        }
        return hairColor;
    }

    /**
     * Метод для ввода локации.
     * @return Возвращает введённую локацию с координатами и названием.
     */
    default Location addLocation(){
        double X;
        Long Y;
        float Z;
        Scanner in = new Scanner(System.in);
        for(;;){
            try{
                System.out.print("Input location x (not null): ");
                X = in.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error! Invalid data type. Try again.");
                in.next();
            }
        }
        for(;;){
            try{
                System.out.print("Input location y: ");
                Y = in.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error! Invalid data type. Try again.");
                in.next();
            }
        }
        for(;;){
            try{
                System.out.print("Input location z: ");
                Z = in.nextFloat();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error! Invalid data type. Try again.");
                in.next();
            }
        }
        return new Location(X, Y, Z, addStr("location name", true));
    }
}
