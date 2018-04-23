import org.search.engine.model.Document;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class RESTClient {
    public static final String REST_SERVICE_URI = "http://localhost:8081";

    private static void createDocument(Long id, String text) {
        RestTemplate restTemplate = new RestTemplate();
        Document document = new Document(id, text);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/document/", document, Document.class);
        System.out.println(document);
    }

    private static void getDocument(Long id){
        RestTemplate restTemplate = new RestTemplate();
        String document = restTemplate.getForObject(REST_SERVICE_URI+"/documents/"+id, String.class);
        System.out.println(document);
        Assert.notNull(document);
    }

    private static void findAllDocuments() {
        RestTemplate restTemplate = new RestTemplate();
        String documents = restTemplate.getForObject(REST_SERVICE_URI + "/documents/", String.class);
        System.out.println(documents);
        Assert.notNull(documents);
    }

    private static void findDocumentsByTokens(String tokens) {
        RestTemplate restTemplate = new RestTemplate();
        String ids = restTemplate.getForObject(REST_SERVICE_URI + "/documents/tokens/"+tokens, String.class);
        System.out.println(ids);
        Assert.isTrue(ids.contains("1"));
        Assert.isTrue(ids.contains("3"));
    }


    public static void main(String args[]){
        System.out.println("Creating documents: ");
        createDocument(1L,"abc, def, xyz, ");
        createDocument(2L,"glk, gle, gls, gl, qq, tt");
        createDocument(3L,"ddt, bbc, xxx");

        System.out.println("Getting documents: ");
        getDocument(1L);
        getDocument(2L);
        getDocument(3L);
        findAllDocuments();

        String tokens = "ddt abc";
        System.out.println("Searching documents by tokens: "+tokens);
        findDocumentsByTokens(tokens);
    }

 }

