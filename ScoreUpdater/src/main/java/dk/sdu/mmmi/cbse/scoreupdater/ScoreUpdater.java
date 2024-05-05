package dk.sdu.mmmi.cbse.scoreupdater;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ScoreUpdater implements IUIProcessingService {
    @Override
    public void processUI(Pane gameWindow, GameData gameData) {
        Text scoreText = (Text) gameWindow.lookup("#score");
        if (scoreText != null) {
            scoreText.setText("Score: " + gameData.getScore());
        } else {
            scoreText = new javafx.scene.text.Text(10, 10, "Score: " + gameData.getScore());
            scoreText.setId("score");
            gameWindow.getChildren().add(scoreText);
        }
    }
}
