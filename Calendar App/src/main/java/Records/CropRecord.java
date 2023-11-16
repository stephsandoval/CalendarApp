package Records;

public class CropRecord {
 
    private String crop, cropStatus, pests, cropNote;
    private int amount;

    public CropRecord (String crop, int amount, String cropStatus, String pests, String cropNote){
        this.crop = crop;
        this.amount = amount;
        this.cropStatus = cropStatus;
        this.pests = pests;
        this.cropNote = cropNote;
    }

    public String getCrop (){
        return this.crop;
    }

    public int getAmount (){
        return this.amount;
    }

    public String getCropStatus (){
        return this.cropStatus;
    }

    public String getPests (){
        return this.pests;
    }

    public String getCropNote (){
        return this.cropNote;
    }
    
    public void setCrop (String crop){
        this.crop = crop;
    }

    public void setAmount (int amount){
        this.amount = amount;
    }

    public void setCropStatus (String cropStatus){
        this.cropStatus = cropStatus;
    }

    public void setPests (String pests){
        this.pests = pests;
    }

    public void setCropNote (String cropNote){
        this.cropNote = cropNote;
    }

    public String toString (){
        return "crop record >> crop > " + crop + " | amount > " + amount + " | status > " + cropStatus + " | pests > " + pests + " | note > " + cropNote;
    }
}