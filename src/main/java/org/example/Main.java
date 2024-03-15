package org.example;

import org.example.Collection.Parser.XmlManager;
import org.example.Comands.Invoker;

/**
 * Стартовый класс имеющий статический метод main
 * @author Захарченко Роман
 */
public class Main {
    public static void main(String[] args) throws Exception{
//        CollectionManager.setNotebook(XmlManager.myParser());
        XmlManager.mySecondParser();
        Invoker invk = new Invoker();
        invk.commandSelection();
    }
}

