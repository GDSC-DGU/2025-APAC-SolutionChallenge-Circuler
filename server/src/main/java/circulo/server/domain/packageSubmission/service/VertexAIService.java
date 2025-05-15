package circulo.server.domain.packageSubmission.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.nimbusds.jose.shaded.gson.JsonArray;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class VertexAIService {

    @Value("${vertex.project-id}")
    private String projectId;

    @Value("${vertex.endpoint-id}")
    private String endpointId;

    private static final String API_URL_TEMPLATE =
            "https://us-central1-aiplatform.googleapis.com/v1/projects/%s/locations/us-central1/endpoints/%s:predict";

    public String classifyPackaging(MultipartFile file) throws IOException {
        try {
            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
            String apiUrl = String.format(API_URL_TEMPLATE, projectId, endpointId);

            // JSON 본문 구성
            JsonObject body = new JsonObject();
            JsonArray instances = new JsonArray();
            JsonObject imageObj = new JsonObject();
            imageObj.addProperty("content", base64Image);
            instances.add(imageObj);
            body.add("instances", instances);

            JsonObject parameters = new JsonObject();
            parameters.addProperty("confidenceThreshold", 0.5);
            parameters.addProperty("maxPredictions", 1);
            body.add("parameters", parameters);

            // 액세스 토큰 가져오기
            String accessToken = getAccessToken();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);

            HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

            // 응답 파싱
            JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();
            JsonObject prediction = json.getAsJsonArray("predictions")
                    .get(0).getAsJsonObject();

            String displayName = prediction.getAsJsonArray("displayNames")
                    .get(0).getAsString();

            return displayName.toUpperCase(); // enum 비교를 위해 대문자로 반환

        } catch (Exception e) {
            // 🔴 여기가 핵심
            System.err.println("🛑 Vertex AI 호출 중 예외 발생:");
            e.printStackTrace();
            throw new IOException("Vertex AI 호출 실패", e);
        }
    }

    private String getAccessToken() throws IOException {
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new FileInputStream(System.getenv("GOOGLE_APPLICATION_CREDENTIALS")))
                .createScoped("https://www.googleapis.com/auth/cloud-platform");
        credentials.refreshIfExpired();
        return credentials.getAccessToken().getTokenValue();
    }
}