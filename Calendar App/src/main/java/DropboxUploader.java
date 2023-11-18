import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.RequestedVisibility;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.sharing.SharedLinkSettings;

import java.io.FileInputStream;
import java.io.InputStream;

public class DropboxUploader {
    public static void main(String[] args) throws Exception {
        // Replace with your obtained Dropbox access token
        String ACCESS_TOKEN = "sl.BqEkxLEbsOaEvhMLDoXUZI_JgRuXc3TUVLCVDx7TM3PFPZtQyiYUgjhk5SiGIMsPpL-zHK6adMI13jfoMQSpooBRzD5ehH6KCKfuxp56FzrlSWvpKc3AeWABLj2K-WVc-XYapdao_8VXYMTUMfGO5s8";

        DbxRequestConfig config = DbxRequestConfig.newBuilder("Calendar App").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try (InputStream in = new FileInputStream("src/main/java/Images/apple.jpg")) {
            FileMetadata metadata = client.files().uploadBuilder("/apple.jpg")
                    .withMode(WriteMode.ADD)
                    .uploadAndFinish(in);

            // Get the shared link
            SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings(metadata.getPathDisplay(),
                    SharedLinkSettings.newBuilder().withRequestedVisibility(RequestedVisibility.PUBLIC).build());

            // Get the direct link to the image file
            String directLink = sharedLinkMetadata.getUrl();

            System.out.println("Direct link: " + directLink);
        }
    }
}
