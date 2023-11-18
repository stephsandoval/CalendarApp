import com.contentful.java.cma.*;
import com.contentful.java.cma.model.CMAAsset;
import com.contentful.java.cma.model.CMAAssetFile;
import com.contentful.java.cma.model.CMALink;
import com.contentful.java.cma.model.CMASystem;
import com.contentful.java.cma.model.CMAType;
import com.contentful.java.cma.model.CMAUpload;

import java.io.FileInputStream;
import java.io.IOException;

public class ContentfulConnector {

    public static void main(String[] args) {
        // Set up Contentful client
        final CMAClient client = new CMAClient
                .Builder()
                .setAccessToken("CFPAT-PD1g-fAbvgONwsLi0_CdbJilnPRFE4QyEJ_PuduEdQ4")
                .setSpaceId("7u4zyhwnzl64")
                .setEnvironmentId("master")
                .build();

        try {
            // Upload the file
            CMAUpload upload = client.uploads().create("7u4zyhwnzl64", new FileInputStream("src/main/java/Images/apple.jpg"));

            CMASystem sys = new CMASystem();
            sys.setLinkType(CMAType.Upload);
            sys.setType(CMAType.Link);
            sys.setId(upload.getSystem().getId());
            CMALink link = new CMALink();
            link.setSystem(sys);


            // Create an arbitrary new asset with some custom values.
            final CMAAsset asset = new CMAAsset();
            asset.getFields().setTitle("en-US","apple");

            CMAAssetFile file = new CMAAssetFile();
            file.setFileName("apple.jpg");  // Replace with the actual filename.
            file.setContentType("image/jpg");  // Replace with the actual content type.
            file.setUploadFrom(link);

            asset.getFields().setFile("en-US", file);

            // Create it on Contentful.
            CMAAsset result = client.assets().create(asset);

            client.assets().process(result, "en-US");

            // Assuming you have the ID of the draft asset
            String draftAssetId = result.getId();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}

            // Get the draft asset
            CMAAsset draftAsset = client.assets().fetchOne(draftAssetId);

            // Publish the draft asset
            CMAAsset p = client.assets().publish(draftAsset);

            System.out.println("Published Asset ID: " + p.getId());
        } catch (IOException e) {}
        System.exit(0);
    }
}
