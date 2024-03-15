package org.example.Collection.Parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.example.Collection.CollectionManager;
import org.example.Collection.KeepCollection;
import org.example.Collection.Ticket;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Класс работы с xml файлом
 * @author Захарченко Роман
 */
public class XmlManager {
    static ObjectMapper objectMapper = new XmlMapper();
    static SimpleModule module = new SimpleModule();

    /**
     * Метод парсит xml с помощи jackson в коллекцию
     */
    public static void mySecondParser() throws IOException {
        File f = new File(System.getenv("PWD") + "/base2.xml");
        //File f = new File(System.getenv("RBASE") + "/base.xml");
        HashSet<Ticket> notebook = new HashSet<>();
        if(!f.exists() || f.isDirectory()) {
            System.out.println("File not exists or it is directory! We create new file on this way.");
            return;
        }
        if(!f.canRead() || !f.canWrite()){
            System.out.println("Loading error! The file is not readable or writable. Collection is empty.");
            return;
        }
        if (f.length() == 0) return;

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Reads from XML and converts to POJO
        try{
            KeepCollection keepCollection = objectMapper.readValue(
                    //StringUtils.toEncodedString(Files.readAllBytes(Paths.get("test.xml")), StandardCharsets.UTF_8),
                    StringUtils.toEncodedString(Files.readAllBytes(Paths.get(System.getenv("PWD") + "/base2.xml")), StandardCharsets.UTF_8),
                    KeepCollection.class);
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Iterator<Ticket> i = keepCollection.getTicket().iterator();
            while (i.hasNext()) {
                Ticket tmp = i.next();
                Set<ConstraintViolation<Ticket>> violations = validator.validate(tmp);
                for (ConstraintViolation<Ticket> violation : violations) {
                    System.out.println(violation.getMessage());
                    i.remove();
                    break;
                }
            }
            CollectionManager.setNotebook(keepCollection.getTicket());
        }catch (JsonMappingException e){
            System.out.println("!!!!! The file has syntax errors.");
        }

    }
    /**
     * Метод сохраняющий коллекцию в файл base.xml
     */
    public static void mySecondSaveCollection(KeepCollection notebook) throws IOException {
        objectMapper.writeValue(new File(System.getenv("PWD") + "/base2.xml"), notebook);
    }

}
