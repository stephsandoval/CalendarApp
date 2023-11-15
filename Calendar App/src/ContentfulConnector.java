import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;

public class ContentfulConnector {
    public static void main(String[] args) {
        fetchEntries();
        System.exit(0);
    }

    private static void fetchEntries() {
        // Create the Contentful client.
        final CDAClient client = CDAClient.builder()
            .setToken("fjgizg8zVhAwhlEs-2h3tEjwUWQLqAGxtN4aAXFpvBA") // required
            .setSpace("7u4zyhwnzl64") // required
            .setEnvironment("master") // optional, defaults to `master`
            .build();

        CDAArray entries = client.fetch(CDAEntry.class).withContentType("calendarEntry").all();
        for (CDAResource resource : entries.items()){
            if (resource instanceof CDAEntry) {
                CDAEntry entry = (CDAEntry) resource;
                System.out.println("Entry ID: " + entry.id());
                System.out.println("Entry Fields:");
                entry.rawFields().forEach((key, value) -> {
                    System.out.println(key + ": " + value);
                });
            }
            System.out.println();
        }
    }

}
