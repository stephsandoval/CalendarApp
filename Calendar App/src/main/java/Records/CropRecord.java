package Records;

public class CropRecord {
 
    private String crop, cropStatus, pests, cropNote;
    private int amount;

    public CropRecord (){
        this.crop = "-";
        this.amount = 0;
        this.cropStatus = "-";
        this.pests = "-";
        this.cropNote = "-";
    }

    public CropRecord (String crop, int amount, String cropStatus, String pests, String cropNote){
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
        } else {
            this.crop = "-";
        }
    }

    public void setAmount (int amount){
        if (amount >= 0){
            this.amount = amount;
        } else {
            this.amount = 0;
        }
    }

    public void setCropStatus (String cropStatus){
        if (cropStatus != null){
            this.cropStatus = cropStatus;
        } else {
            this.cropStatus = "-";
        }
    }

    public void setPests (String pests){
        if (pests != null){
            this.pests = pests;
        } else {
            this.pests = "-";
        }
    }

    public void setCropNote (String cropNote){
        if (cropNote != null){
            this.cropNote = cropNote;
        } else {
            this.cropNote = "-";
        }
    }

    public boolean hasInformation (){
        return (!crop.equals("-") || amount != 0 || !cropStatus.equals("-") || !pests.equals("-") || !cropNote.equals("-"));
    }

    public String toString (){
        return "crop record >> crop > " + crop + " | amount > " + amount + " | status > " + cropStatus + " | pests > " + pests + " | note > " + cropNote;
    }
}