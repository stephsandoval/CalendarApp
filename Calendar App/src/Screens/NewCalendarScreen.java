package Screens;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private NewCalendarController controller = new NewCalendarController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        collectButtons();
        nameButtons();

        aspectColumn.setCellValueFactory(new PropertyValueFactory<>("aspect"));
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("info"));
    }

    private void collectButtons (){
        calendarDays = new ArrayList<>();
        ObservableList<Node> days = calendarGrid.getChildren();
        for (Node day : days){
            Button dayButton = (Button) day;
            dayButton.setOnAction(e -> {
                populatePreview(null);
            });
            calendarDays.add(dayButton);
        }
    }

    private void nameButtons (){
        int start = controller.getOffset();
        int stop = controller.getMaxDays();
        int day = 1;
        for (int i = start; i <= stop + 1; i++){
            calendarDays.get(i).setText(Integer.toString(day++));
            calendarDays.get(i).setAlignment(Pos.CENTER);
        }
    }

    private void populatePreview (LocalDate day){
        ArrayList <DayPrev> dayPreview = controller.getDayPreview(day);
        ObservableList <DayPrev> tablePreview = dayView.getItems();
        tablePreview.clear();
        tablePreview.addAll(dayPreview);
        dayView.setItems(tablePreview);
    }
}