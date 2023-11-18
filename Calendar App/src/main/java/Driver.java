import java.io.File;

import org.apache.tika.Tika;

public class Driver {
    public static void main (String [] args){
        String videoPath = "src/main/java/Videos/seedlings.mp4";
        String potatoPath = "src/main/java/Images/potato.jpg";
        String tempPath = "https://images.ctfassets.net/7u4zyhwnzl64/6jNFjWiOT3l0fhfeHR80EJ/5502dab671e0aa2a5acac566ff3e6f7c/potato.jpg";

        Tika tika = new Tika();

        String mimeType = tika.detect(videoPath);
        System.out.println(mimeType);
        mimeType = tika.detect(potatoPath);
        System.out.println(mimeType);
        mimeType = tika.detect(tempPath);
        System.out.println(mimeType.split("/")[0]);

        File file = new File(potatoPath);
        String fileName = file.getName();
        System.out.println(fileName.split(".")[0]);
    }
}