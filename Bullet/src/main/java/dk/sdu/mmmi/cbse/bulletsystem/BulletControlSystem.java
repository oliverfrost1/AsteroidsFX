package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {


    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            moveForward(bullet);
            checkOutOfBounds(bullet, gameData, world);
        }
    }

    private void moveForward(Entity bullet) {
        double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
        double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
        bullet.setX(bullet.getX() + changeX);
        bullet.setY(bullet.getY() + changeY);
    }

    private void checkOutOfBounds(Entity bullet, GameData gameData, World world) {
        if (bullet.getX() < 0 || bullet.getX() > gameData.getDisplayWidth() || bullet.getY() < 0 || bullet.getY() > gameData.getDisplayHeight()) {
            world.removeEntity(bullet);
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(0, 0, 0, 4, 4, 4, 4, 0);

        // Offset in front of shooter
        double offsetDistance = 20.0;

        // Calculate the bullet's spawn position
        double spawnX = shooter.getX() + Math.cos(Math.toRadians(shooter.getRotation())) * offsetDistance;
        double spawnY = shooter.getY() + Math.sin(Math.toRadians(shooter.getRotation())) * offsetDistance;

        bullet.setX(spawnX);
        bullet.setY(spawnY);
        bullet.setRotation(shooter.getRotation());

        return bullet;
    }

}
