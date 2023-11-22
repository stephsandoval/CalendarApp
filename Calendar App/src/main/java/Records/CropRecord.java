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
            this.crop = "";
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
            this.cropStatus = "";
        }
    }

    public void setPests (String pests){
        if (pests != null){
            this.pests = pests;
        } else {
            this.pests = "";
        }
    }

    public void setCropNote (String cropNote){
        if (cropNote != null){
            this.cropNote = cropNote;
        } else {
            this.cropNote = "";
        }
    }

    public void updateCrop (String crop){
        if (this.crop.isEmpty()|| this.crop == null){
            this.crop = crop;
        }
    }

    public void updateCropAmount (int cropAmount){
        if (this.amount == 0){
            this.amount = cropAmount;
        }
    }

    public void updateCropStatus (String cropStatus){
        if (this.cropStatus.isEmpty() || this.cropStatus == null){
            this.cropStatus = cropStatus;
        }
    }

    public void updatePests (String pests){
        if (this.pests.isEmpty() || this.pests == null){
            this.pests = pests;
        }
    }

    public void updateNotes (String notes){
        if (this.cropNote.isEmpty() || this.cropNote == null){
            this.cropNote = notes;
        }
    }

    public boolean hasInformation (){
        return (!crop.isEmpty() || amount != 0 || !cropStatus.isEmpty() || !pests.isEmpty() || !cropNote.isEmpty());
    }

    public String toString (){
        return "crop record >> crop > " + crop + " | amount > " + amount + " | status > " + cropStatus + " | pests > " + pests + " | note > " + cropNote;
    }
}