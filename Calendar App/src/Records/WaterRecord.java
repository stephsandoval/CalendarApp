package Records;

public class WaterRecord {
    
    private double waterAmount, waterpH;
    private String waterSource, waterNote;

    public WaterRecord (String waterSource, double waterAmount, double waterpH, String waterNote){
        this.waterSource = waterSource;
        this.waterAmount = waterAmount;
        this.waterpH = waterpH;
        this.waterNote = waterNote;
    }

    public String getWaterSource (){
        return this.waterSource;
    }

    public double getWaterAmount (){
        return this.waterAmount;
    }

    public double getWaterpH (){
        return this.waterpH;
    }

    public String getWaterNote (){
        return this.waterNote;
    }

    public void setWaterSource (String waterSource){
        this.waterSource = waterSource;
    }

    public void setWaterAmount (double waterAmount){
        this.waterAmount = waterAmount;
    }

    public void setWaterpH (double pH){
        this.waterpH = pH;
    }

    public void setWaterNote (String waterNote){
        this.waterNote = waterNote;
    }

    public String toString (){
        return "water record >> source > " + waterSource + " | amount > " + waterAmount + " | pH > " + waterpH + " | note > " + waterNote;
    }
}