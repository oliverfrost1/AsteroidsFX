package dk.sdu.mmmi.cbse.collisions;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ScoreUtil implements IScoreUtil {

    private final HttpClient client = HttpClient.newHttpClient();

    @Override
    public void addToScore(int score) {
        String jsonPayload = String.format("{\"score\":%d}", score);
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/score/increment"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Score: " + response.body());
        } catch (Exception e) {
            System.out.println("Failed to add score: " + e.getMessage());
        }
    }


    @Override
    public int getScore() {
        try {
            HttpResponse<String> response = client.send(HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/score"))
                    .GET().build(), HttpResponse.BodyHandlers.ofString());
            return Integer.parseInt(response.body());
        } catch (Exception e) {
            System.out.println("Failed to add score: " + e.getMessage());
        }
        return 0;
    }


}
