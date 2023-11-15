package Screens;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import CalendarPrev.DayPreview;
import CalendarPrev.WeekPrev;
import Controllers.CalendarController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CalendarScreen extends GeneralScreen implements Initializable{
    
    @FXML
    private Button addEventButton, goButton;
    @FXML
    private Label monthLabel;
    @FXML 
    private TableView<WeekPrev> calendar; // change object when the calendar is defined
    @FXML
    private DatePicker datePicker;
    @FXML 
    private TableView<DayPreview> dayView; // change object when the calendar is defined
    @FXML
    private TableColumn<WeekPrev, String> mondayColumn, tuesdayColumn, wednesdayColumn, thursdayColumn, fridayColumn, saturdayColumn, sundayColumn;
    @FXML
    private TableColumn<DayPreview, String> aspectColumn, infoColumn;

    private CalendarController controller = new CalendarController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mondayColumn.setCellValueFactory(new PropertyValueFactory<>("monday"));
        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<>("tuesday"));
        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<>("wednesday"));
        thursdayColumn.setCellValueFactory(new PropertyValueFactory<>("thursday"));
        fridayColumn.setCellValueFactory(new PropertyValueFactory<>("friday"));
        saturdayColumn.setCellValueFactory(new PropertyValueFactory<>("saturday"));
        sundayColumn.setCellValueFactory(new PropertyValueFactory<>("sunday"));

        aspectColumn.setCellValueFactory(new PropertyValueFactory<>("aspect"));
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("info"));

        populateCalendar();
    }

    private void populateCalendar (){
        ArrayList <WeekPrev> weeks = controller.getWeeks();
        ObservableList <WeekPrev> tableWeeks = calendar.getItems();
        tableWeeks.clear();
        tableWeeks.addAll(weeks);
        calendar.setItems(tableWeeks);
    }

    public void showDayPreview (){
        LocalDate day = datePicker.getValue();
        populatePreview(day);
    }

    private void populatePreview (LocalDate day){
        ArrayList <DayPreview> dayPreview = controller.getDayPreview(day);
        ObservableList <DayPreview> tablePreview = dayView.getItems();
        tablePreview.clear();
        tablePreview.addAll(dayPreview);
        dayView.setItems(tablePreview);
    }
}