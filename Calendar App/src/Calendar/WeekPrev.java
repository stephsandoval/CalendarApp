package Calendar;

import javafx.scene.control.ListView;

public class WeekPrev {
    
    private ListView<String> monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    public WeekPrev (){
        this.monday = null;
        this.tuesday = null;
        this.wednesday = null;
        this.thursday = null;
        this.friday = null;
        this.saturday = null;
        this.sunday = null;
    }

    public ListView<String> getMonday (){
        return this.monday;
    }

    public ListView<String> getTuesday (){
        return this.tuesday;
    }

    public ListView<String> getWednesday (){
        return this.wednesday;
    }

    public ListView<String> getThursday (){
        return this.thursday;
    }

    public ListView<String> getFriday (){
        return this.friday;
    }

    public ListView<String> getSaturday (){
        return this.saturday;
    }

    public ListView<String> getSunday (){
        return this.sunday;
    }

    public void setMonday (ListView<String> monday){
        this.monday = monday;
    }

    public void setTuesday (ListView<String> tuesday){
        this.tuesday = tuesday;
    }

    public void setWednesday (ListView<String> wednesday){
        this.wednesday = wednesday;
    }

    public void setThursday (ListView<String> thursday){
        this.thursday = thursday;
    }

    public void setFriday (ListView<String> friday){
        this.friday = friday;
    }

    public void setSaturday (ListView<String> saturday){
        this.saturday = saturday;
    }

    public void setSunday (ListView<String> sunday){
        this.sunday = sunday;
    }
}