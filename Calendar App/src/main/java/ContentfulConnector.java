import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;

import com.contentful.java.cma.CMAClient;
import com.contentful.java.cma.model.CMAEntry;

public class ContentfulConnector {
    public static void main(String[] args) {

        // Create the Contentful client.
       /*  final CMAClient client = new CMAClient.Builder()
            .setAccessToken("CFPAT-PD1g-fAbvgONwsLi0_CdbJilnPRFE4QyEJ_PuduEdQ4")
            .setSpaceId("7u4zyhwnzl64")
            .setEnvironmentId("master").build();

        // Create a local entry with id.
        final CMAEntry entry = new CMAEntry().setField("date", "en-US", "2023-11-08");
        entry.setField("entryTitle", "en-US", "2023-11-08-calendar");
        entry.setField("cropType", "en-US", "apple");
        entry.setField("cropAmount", "en-US", 75);
        entry.setField("pests", "en-US", "insects");
        entry.setField("cropNotes", "en-US", "lost some apples thanks to insects");

        // Create the same entry in Contentful.
        CMAEntry result = client.entries().create("calendarEntry", entry);
        client.entries().publish(result);*/

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
