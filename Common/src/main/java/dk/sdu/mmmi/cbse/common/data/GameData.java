package dk.sdu.mmmi.cbse.common.data;

public class GameData {

    private int displayWidth = 800;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();

    private int score = 0;
    private boolean isGameOver = false;
    private boolean resetAndStartGame = false;


    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public boolean isResetAndStartGame() {
        return resetAndStartGame;
    }

    public void setResetAndStartGame(boolean resetAndStartGame) {
        this.resetAndStartGame = resetAndStartGame;
    }
}
