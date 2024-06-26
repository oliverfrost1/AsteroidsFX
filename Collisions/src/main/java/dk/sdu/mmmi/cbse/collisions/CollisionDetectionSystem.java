package dk.sdu.mmmi.cbse.collisions;

import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Arrays;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class CollisionDetectionSystem implements IPostEntityProcessingService {
    @Override
    public void postProcess(GameData gameData, World world) {

        Entity[] allEntities = world.getEntities().toArray(new Entity[0]);
        Entity[] enemies = Arrays.stream(allEntities).filter(entity -> entity.getEntityType().equals(Entity.EntityType.ENEMY)).toArray(Entity[]::new);
        Entity[] players = Arrays.stream(allEntities).filter(entity -> entity.getEntityType().equals(Entity.EntityType.PLAYER)).toArray(Entity[]::new);
        Entity[] asteroids = Arrays.stream(allEntities).filter(entity -> entity.getEntityType().equals(Entity.EntityType.ASTEROID)).toArray(Entity[]::new);
        Entity[] bullets = Arrays.stream(allEntities).filter(entity -> entity.getEntityType().equals(Entity.EntityType.BULLET)).toArray(Entity[]::new);
        Entity[] allNonBullets = Arrays.stream(allEntities).filter(entity -> !entity.getEntityType().equals(Entity.EntityType.BULLET)).toArray(Entity[]::new);


        // If player and enemy collide, remove both entities
        for (Entity player : players) {
            for (Entity enemy : enemies) {
                if (player.intersects(enemy)) {
                    world.removeEntity(enemy);
                    world.removeEntity(player);
                    gameData.setGameOver(true);
                }
            }
        }


        // If bullet hits entity, decrease the health by one
        for (Entity bullet : bullets) {
            for (Entity entity : allNonBullets) {
                if (bullet.intersects(entity)) {
                    entity.setHealth(entity.getHealth() - 1);
                    world.removeEntity(bullet);
                }
            }
        }

        // If player or enemy health is 0, remove entity
        for (Entity entity : players) {
            if (entity.getHealth() <= 0) {
                world.removeEntity(entity);
                gameData.setGameOver(true);
            }
        }
        for (Entity entity : enemies) {
            if (entity.getHealth() <= 0) {
                world.removeEntity(entity);
            }
        }

        // If asteroid health is 0, split into two smaller asteroids
        for (Entity asteroid : asteroids) {
            if (asteroid.getHealth() <= 0) {
                getAsteroidSPI().forEach(asteroidSPI -> {
                    boolean wasRemoved = asteroidSPI.splitAsteroidOrRemoveIt(asteroid, world);
                    if (wasRemoved) {
                        gameData.setScore(gameData.getScore() + 1);
                    }
                });
            }
        }

        // If player hits asteroid, remove player
        for (Entity player : players) {
            for (Entity asteroid : asteroids) {
                if (player.intersects(asteroid)) {
                    world.removeEntity(player);
                    gameData.setGameOver(true);
                }
            }
        }

    }

    private Collection<? extends AsteroidSPI> getAsteroidSPI() {
        return ServiceLoader.load(AsteroidSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


}
