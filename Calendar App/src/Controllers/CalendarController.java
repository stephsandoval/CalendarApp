package Controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import CalendarPrev.DayPreview;
import CalendarPrev.WeekPrev;
import javafx.scene.control.ListView;

public class CalendarController {

    public ArrayList <WeekPrev> getWeeks (){
        ArrayList <WeekPrev> weeksList = new ArrayList<>();
        WeekPrev week = new WeekPrev();
        
        ListView <String> monday = new ListView<>();
        ListView <String> wednesday = new ListView<>();
        ListView <String> friday = new ListView<>();

        monday.getItems().addAll("event m1", "event m2");
        wednesday.getItems().addAll("event w1", "event w2", "event w3");
        friday.getItems().addAll("event f1", "event f2");

        monday.setPrefHeight(84);
        wednesday.setPrefHeight(84);
        friday.setPrefHeight(84);

        week.setMonday(monday);
        week.setWednesday(wednesday);
        week.setFriday(friday);

        weeksList.add(week);
        return weeksList;
    }

    public ArrayList <DayPreview> getDayPreview (LocalDate day){
        // verify the day exists, if it does, return array with events
        ArrayList <DayPreview> preview = new ArrayList<>();
        preview.add(new DayPreview("temperature", "25°C"));
        preview.add(new DayPreview("notes", "sunny weather"));
        preview.add(new DayPreview("water source", "groundwater"));
        return preview;
    }
}