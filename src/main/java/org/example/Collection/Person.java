package org.example.Collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;
import org.example.Collection.Parser.CustomSerializer;
import org.example.enum_.Color;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Класс персонажа с днём рождения, цветом волос и локацией персонажа.
 * @author Захарченко Роман
 */

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    /**
     * День рождения персонажа, не может быть null
     */
    @NotNull(message = "Error!! Birthday can't be null!")
    @JsonSerialize(using = CustomSerializer.class)
    private Date birthday; //Поле не может быть null
    /**
     * Цвет волос персонажа, может быть null
     */
    private Color hairColor; //Поле может быть null
    /**
     * Локация персонажа, не может быть null
     */
    @Valid
    @NotNull(message = "Error!! Location can't be null!")
    @JacksonXmlElementWrapper(localName = "location", useWrapping = false)
    private Location location; //Поле не может быть null
    @JsonCreator
    public Person(@JacksonXmlProperty(localName = "birthday") String birthday,
                  @JacksonXmlProperty(localName = "hairColor") String hairColor,
                  @JacksonXmlProperty(localName = "location") Location location) throws ParseException {
        try {
            this.birthday = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(birthday);
            this.hairColor = Color.valueOf(hairColor);
            this.location = location;
         }catch (IllegalArgumentException e) {
        System.out.println("There are incorrect types in the database! Check the database! This ticket was not cancelled!");
    }catch (NullPointerException e){
        System.out.println("There are incorrect types in the database! Check the database! This ticket was not cancelled!");
    }
    }

    /**
     * Переопределённый метод toString.
     * @return возвращает читаемую и информативную строку с локацией, цветом волос и днём рождения персонажа.
     */
    @Override
    public String toString() {
        return "{" +
                "\n\t\tbirthday=" + birthday +
                "\n\t\thairColor=" + hairColor +
                "\n\t\tlocation=" + location.toString() +
                "\n\t}";
    }
}
