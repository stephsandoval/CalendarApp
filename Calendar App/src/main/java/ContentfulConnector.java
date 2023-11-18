
/*import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ContentfulImageFetcher {

    public static void main(String[] args) throws Exception {
        String spaceId = "your_space_id";
        String entryId = "your_entry_id";
        String assetId = "your_asset_id";
        String accessToken = "your_access_token";

        // Construct entry URL
        String entryUrl = "https://cdn.contentful.com/spaces/" + spaceId + "/entries/" + entryId + "?access_token=" + accessToken;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest entryRequest = HttpRequest.newBuilder()
                .uri(URI.create(entryUrl))
                .build();

        HttpResponse<String> entryResponse = client.send(entryRequest, HttpResponse.BodyHandlers.ofString());

        // Parse entry response and extract image information
        String imageUrl = parseImageUrlFromEntryResponse(entryResponse.body());

        // Construct asset URL
        String assetUrl = "https://cdn.contentful.com/spaces/" + spaceId + "/assets/" + assetId + "?access_token=" + accessToken;

        HttpRequest assetRequest = HttpRequest.newBuilder()
                .uri(URI.create(assetUrl))
                .build();

        HttpResponse<String> assetResponse = client.send(assetRequest, HttpResponse.BodyHandlers.ofString());

        // Parse asset response to get the actual image URL or other information
        String actualImageUrl = parseImageUrlFromAssetResponse(assetResponse.body());

        // Now you can use actualImageUrl to display or download the image
        System.out.println("Actual Image URL: " + actualImageUrl);
    }

    private static String parseImageUrlFromEntryResponse(String entryResponse) {
        // Implement parsing logic based on your JSON structure
        // Extract the information needed to construct the asset URL
        return "extracted_image_url";
    }

    private static String parseImageUrlFromAssetResponse(String assetResponse) {
        // Implement parsing logic based on your JSON structure
        // Extract the actual image URL or other information
        return "actual_image_url";
    }
}*/