package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class EnemyPluginTests {

    private EnemyPlugin enemyPlugin;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void setUpTest() {
        this.gameData = new GameData();
        this.world = new World();
        this.enemyPlugin = new EnemyPlugin();
    }

    // Test that start doesn't spawn any enemies
    @Test
    public void testNoEnemiesAppearOnStart() {
        // Given
        int amountOfEnemies = world.getEntities(Enemy.class).size();
        if (amountOfEnemies > 0) {
            fail("There are already enemies in the world");
        }

        // When
        enemyPlugin.start(gameData, world);

        // Then
        assert (world.getEntities(Enemy.class).size() == amountOfEnemies);
    }

    // Test that all enemies disappear when the plugin is stopped
    @Test
    public void testAllEnemiesDisappearOnStop() {
        // Given
        enemyPlugin.start(gameData, world);

        // When
        world.addEntity(new Enemy());
        assertTrue(world.getEntities(Enemy.class).size() > 0);
        enemyPlugin.stop(gameData, world);

        // Then
        assert (world.getEntities(Enemy.class).size() == 0);
    }

}
