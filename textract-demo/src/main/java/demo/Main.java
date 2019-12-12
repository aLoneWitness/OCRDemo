package demo;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.AnalyzeDocumentRequest;
import software.amazon.awssdk.services.textract.model.AnalyzeDocumentResponse;
import software.amazon.awssdk.services.textract.model.Document;
import software.amazon.awssdk.services.textract.model.S3Object;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String document = "/home/sander/Downloads/ocrimagetest.jpeg";
        Path fileLocation = Paths.get(document);


        TextractClient client = TextractClient.builder().region(Region.EU_WEST_1).build();

        AnalyzeDocumentRequest request = null;
        try {
            request = AnalyzeDocumentRequest.builder()
                    .featureTypesWithStrings("TABLES", "FORMS")
                    .document(Document.builder().bytes(SdkBytes.fromByteArray(Files.readAllBytes(fileLocation))).build()).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnalyzeDocumentResponse response = client.analyzeDocument(request);


        System.out.println("Put breakpoint here to analyze response data.");
    }
}
