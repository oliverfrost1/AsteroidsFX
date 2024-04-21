package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnemyControlSystemTests {

    private GameData gameData;
    private World world;
    private Enemy enemy;
    private EnemyControlSystem enemyControlSystem;

    @BeforeEach
    public void setUpTest() {
        this.gameData = new GameData();
        this.world = new World();
        this.enemy = new Enemy();
        this.enemyControlSystem = new EnemyControlSystem();
        world.addEntity(enemy);
    }

    // Test that enemy moves when processed
    @Test
    public void testDoesEnemyMove() {
        // Given
        double enemyPosX = enemy.getX();
        double enemyPosY = enemy.getY();

        // When
        enemyControlSystem.process(gameData, world);

        // Then
        boolean hasEnemyMoved = enemyPosX != enemy.getX() || enemyPosY != enemy.getY();
        Assertions.assertTrue(hasEnemyMoved);
    }

    // Test that enemy can be spawned
    @Test
    public void testCanEnemySpawn() {
        // Given
        int amountOfEnemies = world.getEntities(Enemy.class).size();

        // When
        enemyControlSystem.spawnEnemy(gameData, world);
        enemyControlSystem.process(gameData, world);

        // Then
        boolean hasEnemySpawned = amountOfEnemies < world.getEntities(Enemy.class).size();
        Assertions.assertTrue(hasEnemySpawned);
    }

    // Test that enemy can be spawned
    @Test
    public void testEnemySpawnLimitCannotBeExceeded() {
        // Given
        // When
        enemyControlSystem.spawnEnemy(gameData, world);
        enemyControlSystem.spawnEnemy(gameData, world);
        enemyControlSystem.spawnEnemy(gameData, world);
        enemyControlSystem.spawnEnemy(gameData, world);
        enemyControlSystem.spawnEnemy(gameData, world);
        enemyControlSystem.process(gameData, world);

        // Then
        Assertions.assertTrue(world.getEntities(Enemy.class).size() <= 4);
    }
}
