package Calendar;

public class DayPrev {

    private String aspect, info;

    public DayPrev (String aspect, String info){
        this.aspect = aspect;
        this.info = info;
    }

    public String getAspect (){
        return this.aspect;
    }

    public String getInfo (){
        return this.info;
    }

    public void setAspect (String aspect){
        this.aspect = aspect;
    }

    public void setInfo (String info){
        this.info = info;
    }
}