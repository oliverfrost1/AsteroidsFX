package dk.sdu.mmmi.cbse.scoreupdater;

import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ScoreUpdater implements IUIProcessingService {

    private int score = 0;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public void processUI(Pane gameWindow) {


        updateScore();
        gameWindow.getChildren().clear();
        gameWindow.getChildren().add(new javafx.scene.text.Text("Score: " + getScore()));
    }

    private void updateScore() {
        // Get score from ScoreService
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/score"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            setScore(Integer.parseInt(response.body()));
        } catch (IOException e) {
            System.out.println("Failed to update score");
        } catch (InterruptedException e) {
            System.out.println("Failed to update score");
        }


    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
