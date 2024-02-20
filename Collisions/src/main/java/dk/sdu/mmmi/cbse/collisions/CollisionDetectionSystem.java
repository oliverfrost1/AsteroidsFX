package dk.sdu.mmmi.cbse.collisions;

import dk.sdu.mmmi.cbse.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Arrays;

public class CollisionDetectionSystem implements IPostEntityProcessingService {
    @Override
    public void postProcess(GameData gameData, World world) {

        Entity[] allEntities = world.getEntities().toArray(new Entity[0]);
        System.out.println("Entities: " + allEntities.length);


        return;
        // Player and enemy collision
        for (Entity player : world.getEntities()) {
            for (Entity enemy : world.getEntities(Enemy.class)) {
                if (player.intersects(enemy)) {
                    world.removeEntity(enemy);
                    world.removeEntity(player);
                }
            }
        }

        // Player and asteroid collision

        // Bullet hits entity - decrease the health by one.


        // If bullet hits asteroid, then split into two smaller asteroids
        double minimumHeight = 20;
        double minimumWidth = 20;

        // If hit by a bullet, then split into two smaller asteroids
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            for (Entity bullet : world.getEntities(Bullet.class)) {
                if (asteroid.intersects(bullet)) {
                    // Split into two smaller asteroids, unless the asteroid has gotten smaller than threshold width or height


                    if (asteroid.calculateWidth() > minimumWidth && asteroid.calculateHeight() > minimumHeight) {
                        Entity asteroid1 = new Asteroid();
                        Entity asteroid2 = new Asteroid();
                        double[] polygonCoordinates = Arrays.stream(asteroid.getPolygonCoordinates()).map(point -> point * 0.5).toArray();
                        asteroid1.setPolygonCoordinates(polygonCoordinates);
                        asteroid2.setPolygonCoordinates(polygonCoordinates);
                        asteroid1.setX(asteroid.getX());
                        asteroid1.setY(asteroid.getY());
                        asteroid2.setX(asteroid.getX());
                        asteroid2.setY(asteroid.getY());
                        asteroid1.setRotation(asteroid.getRotation() + 45);
                        asteroid2.setRotation(asteroid.getRotation() - 45);
                        world.addEntity(asteroid1);
                        world.addEntity(asteroid2);
                        world.removeEntity(asteroid);
                    } else {
                        // TODO: Add score here
                        world.removeEntity(asteroid);
                    }
                    world.removeEntity(bullet);
                }
            }

            // If asteroid collides with player, remove asteroid and player
            for (Entity player : world.getEntities(Player.class)) {
                if (asteroid.intersects(player)) {
                    world.removeEntity(asteroid);
                    world.removeEntity(player);
                }
            }
        }

    }


}
