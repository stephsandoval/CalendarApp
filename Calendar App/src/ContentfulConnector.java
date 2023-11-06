/* Mariangel Alfaro, Stephanie Sandoval
 * Trabajo en clase - 27 de octubre, 2023
 * Intento de lectura de base de datos
 */

// based on some research, one can use the com.contenful library to access contenful
// yet, right now, we are still not sure how to use it properly

import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;

import com.contentful.java.cma.CMAClient;
import com.contentful.java.cma.model.CMAEntry;
import com.contentful.java.cma.model.CMAResource;
import com.contentful.java.cma.model.CMASpace;

public class ContentfulConnector {
    private final CDAClient cdaClient;

    public ContentfulConnector(String spaceId, String accessToken) {
        cdaClient = CDAClient.builder().setSpace(spaceId).setToken(accessToken).build();
    }

    public CDAEntry readData(String entryId) {
        try {
            return cdaClient.fetch(CDAEntry.class).one(entryId);
        } catch (Exception e) {}
        return null;
    }

    public static void main(String[] args) {
        String accessToken = "puayiNWwLm-pSbG5iRLJv0tyIYafTfy4S0eIArpt0HA";
        String spaceId = "03kzqwf1vyey";
        ContentfulConnector contentfulConnector = new ContentfulConnector(spaceId, accessToken);
        CDAEntry entry = contentfulConnector.readData("61O3cxlhXLRwClGYlq4d8O");
        if (entry != null) {
            // Access and print the fields of the CDAEntry
            System.out.println("Entry ID: " + entry.id());
            System.out.println("Entry Fields:");
            entry.rawFields().forEach((key, value) -> {
                System.out.println(key + ": " + value);
            });
        } else {
            System.out.println("Failed to retrieve entry.");
        }
        System.out.println("data has been read");
        System.exit(0);
    }
}