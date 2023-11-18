import com.contentful.java.cma.*;
import com.contentful.java.cma.model.CMAAsset;
import com.contentful.java.cma.model.CMAAssetFile;

public class ContentfulConnector {

    public static void main(String[] args) {
        // Create the Contentful client.
        final CMAClient client =
        new CMAClient
            .Builder()
            .setAccessToken("CFPAT-PD1g-fAbvgONwsLi0_CdbJilnPRFE4QyEJ_PuduEdQ4")
            .setSpaceId("7u4zyhwnzl64")
            .setEnvironmentId("master")
            .build();

        // Create an arbitrary new asset with some custom values.
        final CMAAsset asset = new CMAAsset();
        asset.getFields().setTitle("en-US","apple2");

        CMAAssetFile file = new CMAAssetFile();
        file.setFileName("apple.jpg");  // Replace with the actual filename.
        file.setContentType("image/jpg");  // Replace with the actual content type.
        file.setUploadUrl("https://i.pinimg.com/564x/2b/11/66/2b1166e601906c5ecdca2fcb7774169f.jpg");

        asset.getFields().setFile("en-US", file);

        // Create it on Contentful.
        CMAAsset result = client.assets().create(asset);

        client.assets().process(result, "en-US");

        // Assuming you have the ID of the draft asset
        String draftAssetId = result.getId();
        System.out.println(result.getId());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        
        // Get the draft asset
        CMAAsset draftAsset = client.assets().fetchOne(draftAssetId);

        // Publish the draft asset
        CMAAsset p = client.assets().publish(draftAsset);

        System.out.println("Published Asset ID: " + p.getId());

        System.exit(0);
    }
}