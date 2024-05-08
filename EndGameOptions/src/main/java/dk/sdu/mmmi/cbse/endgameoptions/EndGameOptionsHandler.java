package dk.sdu.mmmi.cbse.endgameoptions;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EndGameOptionsHandler implements IUIProcessingService {
    @Override
    public void processUI(Pane gameWindow, GameData gameData) {
        Text gameOverText = (Text) gameWindow.lookup("#gameOverText");
        Text resetButton = (Text) gameWindow.lookup("#resetText");
        if (!gameData.isGameOver()) {
            if (gameOverText != null) {
                gameWindow.getChildren().remove(gameOverText);
            }
            if (resetButton != null) {
                gameWindow.getChildren().remove(resetButton);
            }
            return;
        }

        // Set game over text
        if (gameOverText != null) {
            gameOverText.setText("Game Over");
        } else {
            gameOverText = new javafx.scene.text.Text(gameWindow.getHeight() / 2, gameWindow.getWidth() / 2, "Game Over");
            gameOverText.setId("gameOverText");
            gameWindow.getChildren().add(gameOverText);
        }

        // Add reset button
        if (resetButton != null) {
            resetButton.setText("Click here to restart the game");
        } else {
            resetButton = new javafx.scene.text.Text(gameWindow.getWidth() / 2, gameWindow.getHeight() / 2 + 100, "Click here to restart the game");
            resetButton.setId("resetText");
            resetButton.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    handleResetClick(gameData);
                }
            });
            gameWindow.getChildren().add(resetButton);
        }
    }

    public void handleResetClick(GameData gameData) {
        // Reset game
        System.out.println("Resetting game");
        gameData.setGameOver(false);
        gameData.setResetAndStartGame(true);
    }
}
