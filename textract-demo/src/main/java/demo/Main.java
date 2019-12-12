package demo;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String document = "/home/sander/Downloads/ocrimagetest.jpeg";
        Path fileLocation = Paths.get(document);


        TextractClient client = TextractClient.builder().region(Region.EU_WEST_1).build();

        DetectDocumentTextRequest request = null;
        try {
            request = DetectDocumentTextRequest.builder()
                    .document(Document.builder().bytes(SdkBytes.fromByteArray(Files.readAllBytes(fileLocation))).build()).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DetectDocumentTextResponse response = client.detectDocumentText(request);


        System.out.println("Put breakpoint here to analyze response data.");
    }
}
