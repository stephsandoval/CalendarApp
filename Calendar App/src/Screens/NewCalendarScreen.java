package Screens;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Calendar.Year;
import CalendarPrev.DayPrev;
import Controllers.NewCalendarController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;

public class NewCalendarScreen extends GeneralScreen implements Initializable{
 
    @FXML
    public GridPane calendarGrid;
    @FXML
    private Button previousButton, nextButton;
    @FXML
    private Label monthLabel, yearLabel;
    @FXML 
    private TableView<DayPrev> dayView; // change object when the calendar is defined
    @FXML
    private TableColumn<DayPrev, String> aspectColumn, infoColumn;

    private ArrayList<Button> calendarDays;
    private Month month;
    private int year, day;
    private NewCalendarController controller = new NewCalendarController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalDate today = LocalDate.now();
        this.month = today.getMonth();
        this.year = today.getYear();
        setCalendar(month, year);
        aspectColumn.setCellValueFactory(new PropertyValueFactory<>("aspect"));
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("info"));
    }

    private void setCalendar (Month month, int year){
        controller.setMonthandYear(month, year);
        monthLabel.setText(month.toString());
        yearLabel.setText(Integer.toString(year));
        collectButtons();
        clearButtons();
        nameButtons();
    }

    private void collectButtons (){
        calendarDays = new ArrayList<>();
        ObservableList<Node> days = calendarGrid.getChildren();
        for (Node day : days){
            Button dayButton = (Button) day;
            calendarDays.add(dayButton);
        }
    }

    private void clearButtons (){
        for (Button button : calendarDays){
            button.setText("");
            button.setOnAction(e -> {});
        }
    }

    private void nameButtons() {
        int start = controller.getOffset();
        int stop = controller.getMaxDays();
        int currentDay = 1;
    
        for (int i = start; i < stop + start; i++) {
            calendarDays.get(i).setText(Integer.toString(currentDay));
            int dayOfMonth = currentDay;
            calendarDays.get(i).setOnAction(e -> {
                YearMonth yearMonth = YearMonth.of(year, month);
                populatePreview(yearMonth.atDay(dayOfMonth));
            });
            calendarDays.get(i).setAlignment(Pos.CENTER);
            currentDay++;
        }
    }
    

    private void populatePreview (LocalDate day){
        System.out.println("should print day of " + day.toString());
        ArrayList <DayPrev> dayPreview = controller.getDayPreview(day);
        ObservableList <DayPrev> tablePreview = dayView.getItems();
        tablePreview.clear();
        tablePreview.addAll(dayPreview);
        dayView.setItems(tablePreview);
    }

    public void loadPreviousMonth (){
        if (month == Month.JANUARY){
            year--;
        }
        month = month.minus(1);
        setCalendar(month, year);
    }

    public void loadNextMonth (){
        if (month == Month.DECEMBER){
            year++;
        }
        month = month.plus(1);
        setCalendar(month, year);
    }
}