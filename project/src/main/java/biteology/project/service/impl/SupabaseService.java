package biteology.project.service.impl;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class SupabaseService {
    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.api-key}")
    private String supabaseApiKey;

    @Value("${supabase.bucket}")
    private String bucketName;

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        String uploadUrl = supabaseUrl + "/storage/v1/object/" + bucketName + "/" + fileName;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost uploadRequest = new HttpPost(uploadUrl);
            uploadRequest.setHeader("Authorization", "Bearer " + supabaseApiKey);
            uploadRequest.setHeader("Accept", "application/json");

            // Tạo multipart request
            uploadRequest.setEntity(MultipartEntityBuilder.create()
                    .addPart("file", new InputStreamBody(file.getInputStream(), ContentType.create(file.getContentType()), fileName))
                    .build());

            try (CloseableHttpResponse response = client.execute(uploadRequest)) {
                String responseBody = EntityUtils.toString(response.getEntity());

                if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 201) {
                    return supabaseUrl + "/storage/v1/object/public/" + bucketName + "/" + fileName;
                } else {
                    throw new RuntimeException("Upload failed: " + responseBody);
                }
            }
        }
    }
}
