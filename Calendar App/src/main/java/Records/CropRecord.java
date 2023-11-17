package Records;

public class CropRecord {
 
    private String crop, cropStatus, pests, cropNote;
    private int amount;
    private boolean hasInformation;

    public CropRecord (){
        this.crop = "-";
        this.amount = 0;
        this.cropStatus = "-";
        this.pests = "-";
        this.cropNote = "-";
        this.hasInformation = false;
    }

    public CropRecord (String crop, int amount, String cropStatus, String pests, String cropNote){
        this.hasInformation = false;
        setCrop(crop);
        setAmount(amount);
        setCropStatus(cropStatus);
        setPests(pests);
        setCropNote(cropNote);
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
        if (crop != null){
            this.crop = crop;
            this.hasInformation = true;
        } else {
            this.crop = "-";
        }
    }

    public void setAmount (int amount){
        if (amount >= 0){
            this.amount = amount;
            this.hasInformation = true;
        } else {
            this.amount = 0;
        }
    }

    public void setCropStatus (String cropStatus){
        if (cropStatus != null){
            this.cropStatus = cropStatus;
            this.hasInformation = true;
        } else {
            this.cropStatus = "-";
        }
    }

    public void setPests (String pests){
        if (pests != null){
            this.pests = pests;
            this.hasInformation = true;
        } else {
            this.pests = "-";
        }
    }

    public void setCropNote (String cropNote){
        if (cropNote != null){
            this.cropNote = cropNote;
            this.hasInformation = true;
        } else {
            this.cropNote = "-";
        }
    }

    public boolean hasInformation (){
        return this.hasInformation;
    }

    public String toString (){
        return "crop record >> crop > " + crop + " | amount > " + amount + " | status > " + cropStatus + " | pests > " + pests + " | note > " + cropNote;
    }
}