package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class AsteroidControlSystem implements IEntityProcessingService, IPostEntityProcessingService {

    private Random random = new Random();
    @Override
    public void process(GameData gameData, World world) {

        int amountOfAsteroids = world.getEntities(Asteroid.class).size();

        // Spawn asteroids if there are less than 10 randomly
        if(Math.random()*100 > 99 && amountOfAsteroids < 10) {
            Entity asteroid = new Asteroid();
            // Vary the size of the asteroids
            asteroid.setPolygonCoordinates(
                    0*random.nextDouble(0.8,1.2),
                    0*random.nextDouble(0.8,1.2),
                    20*random.nextDouble(0.8,1.2),
                    24*random.nextDouble(0.8,1.2),
                    32*random.nextDouble(0.8,1.2),
                    28*random.nextDouble(0.8,1.2),
                    40*random.nextDouble(0.8,1.2),
                    16*random.nextDouble(0.8,1.2),
                    28*random.nextDouble(0.8,1.2),
                    -4*random.nextDouble(0.8,1.2));
            // Random position
            asteroid.setX(gameData.getDisplayWidth()*Math.random());
            asteroid.setY(gameData.getDisplayHeight()*Math.random());
            asteroid.setRotation((float) (Math.random()*360));
            world.addEntity(asteroid);
        }


        // Move asteroids randomly, but in semi-continuous directions

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
            asteroid.setX(asteroid.getX() + changeX/2);
            asteroid.setY(asteroid.getY() + changeY/2);

            // Change rotation a little bit randomly, but if it's close to the edges, they adjust to the opposite direction
            // Get edges
            double width = gameData.getDisplayWidth();
            double height = gameData.getDisplayHeight();
            if(asteroid.getX() < 0) {
                asteroid.setRotation(asteroid.getRotation() + 5);
            }
            if(asteroid.getX() > width) {
                asteroid.setRotation(asteroid.getRotation() - 5);
            }
            if(asteroid.getY() < 0) {
                asteroid.setRotation(asteroid.getRotation() + 5);
            }
            if(asteroid.getY() > height) {
                asteroid.setRotation(asteroid.getRotation() - 5);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


    @Override
    public void postProcess(GameData gameData, World world) {

        double minimumHeight = 20;
        double minimumWidth = 20;
        // If hit by a bullet, then split into two smaller asteroids
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            for (Entity bullet : world.getEntities(Bullet.class)) {
                if(asteroid.intersects(bullet)) {
                   // Split into two smaller asteroids, unless the asteroid has gotten smaller than threshold width or height


                    if(asteroid.calculateWidth() > minimumWidth && asteroid.calculateHeight() > minimumHeight) {
                        Entity asteroid1 = new Asteroid();
                        Entity asteroid2 = new Asteroid();
                        double[] polygonCoordinates = Arrays.stream(asteroid.getPolygonCoordinates()).map(point -> point*0.5).toArray();
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
                if(asteroid.intersects(player)) {
                    world.removeEntity(asteroid);
                    world.removeEntity(player);
                }
            }
        }

    }
}
