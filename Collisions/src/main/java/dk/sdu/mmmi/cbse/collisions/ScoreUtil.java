package dk.sdu.mmmi.cbse.collisions;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ScoreUtil implements IScoreUtil {

    private final HttpClient client = HttpClient.newHttpClient();

    @Override
    public void addToScore(int score) {
        try {
            client.send(HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/score/increment"))
                    .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(score)))
                    .build(), HttpResponse.BodyHandlers.ofString());
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
