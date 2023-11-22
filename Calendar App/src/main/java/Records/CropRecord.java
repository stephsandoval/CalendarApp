package Records;

public class CropRecord {
 
    private String crop, cropStatus, pests, cropNote;
    private int amount;

    public CropRecord (){
        this.crop = "";
        this.amount = 0;
        this.cropStatus = "";
        this.pests = "";
        this.cropNote = "";
    }

    public CropRecord (String crop, int amount, String cropStatus, String pests, String cropNote){
        this();
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
        if (crop != null && !crop.isEmpty()){
            this.crop = crop;
        }
    }

    public void setAmount (int amount){
        if (amount > 0){
            this.amount = amount;
        }
    }

    public void setCropStatus (String cropStatus){
        if (cropStatus != null && !cropStatus.isEmpty()){
            this.cropStatus = cropStatus;
        }
    }

    public void setPests (String pests){
        if (pests != null && !pests.isEmpty()){
            this.pests = pests;
        }
    }

    public void setCropNote (String cropNote){
        if (cropNote != null && !cropNote.isEmpty()){
            this.cropNote = cropNote;
        }
    }

    public boolean hasInformation (){
        return (!crop.isEmpty() || amount != 0 || !cropStatus.isEmpty() || !pests.isEmpty() || !cropNote.isEmpty());
    }

    public String toString (){
        return "crop record >> crop > " + crop + " | amount > " + amount + " | status > " + cropStatus + " | pests > " + pests + " | note > " + cropNote;
    }
}