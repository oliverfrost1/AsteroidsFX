package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class AsteroidControlSystem implements IEntityProcessingService, IPostEntityProcessingService {

    private Random random = new Random();
    @Override
    public void process(GameData gameData, World world) {

        int amountOfAsteroids = world.getEntities(Asteroid.class).size();



        // Don't spawn too many asteroids
        if(Math.random()*100 > 99 && amountOfAsteroids < 10) {
            Entity asteroid = new Asteroid();
            // Vary the size of the asteroids
            asteroid.setPolygonCoordinates(0*random.nextDouble(0.8,1.2),
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
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


    @Override
    public void postProcess(GameData gameData, World world) {

    }
}
