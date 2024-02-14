package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        // Amount of enemies
        int amountOfEnemies = world.getEntities(Enemy.class).size();

        if(Math.random()*1000 > 990 && amountOfEnemies < 10) {
            // Don't spawn too many enemies
            Entity enemy = new Enemy();
            enemy.setPolygonCoordinates(-10,-10,10,0,-10,10);
            enemy.setX(gameData.getDisplayWidth()*Math.random());
            enemy.setY(gameData.getDisplayHeight()*Math.random());
            enemy.setRotation((float) (Math.random()*360));
            world.addEntity(enemy);
        }


        // TODO: Move this to own function
        // Process movement for all enemies
        for (Entity enemy : world.getEntities(Enemy.class)) {

            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX/2);
            enemy.setY(enemy.getY() + changeY/2);
            // Change rotation a little bit randomly, but if it's close to the edges, they adjust
            // Get edges
            double width = gameData.getDisplayWidth();
            double height = gameData.getDisplayHeight();
            if(enemy.getX() < 0) {
                enemy.setRotation(enemy.getRotation() + 5);
            }
            if(enemy.getX() > width) {
                enemy.setRotation(enemy.getRotation() - 5);
            }
            if(enemy.getY() < 0) {
                enemy.setRotation(enemy.getRotation() + 5);
            }
            if(enemy.getY() > height) {
                enemy.setRotation(enemy.getRotation() - 5);
            }
            // if not close to edge, adjust direction randomly
            if(enemy.getX() > 0 && enemy.getX() < width && enemy.getY() > 0 && enemy.getY() < height) {
                if(Math.random()*10 > 9) {
                    enemy.setRotation(enemy.getRotation() + (Math.random()*1000 > 500 ? 5 : -5));
                }
            }


            // Spawn bullet randomly
            if(Math.random()*1000 > 990) {
                getBulletSPIs().forEach(bulletSPI -> {
                    Entity bullet = bulletSPI.createBullet(enemy, gameData);
                    world.addEntity(bullet);

                });
            }



        }

    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


}