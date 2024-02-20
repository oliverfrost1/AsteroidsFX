package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {


    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            if (bullet.getX() < 0 ||bullet.getX() > gameData.getDisplayWidth()||bullet.getY() < 0 ||bullet.getY() > gameData.getDisplayHeight()) {
                world.removeEntity(bullet);
            }

            // Move bullet in correct direction
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX*1.3);
            bullet.setY(bullet.getY() + changeY*1.3);
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(5,-1,1,0,-1,1);

        // Define an offset distance to spawn the bullet in front of the shooter
        // This value should be adjusted based on the size of the shooter entity
        double offsetDistance = 20.0; // Example offset, adjust as needed

        // Calculate the bullet's spawn position based on the shooter's orientation and the offset
        double spawnX = shooter.getX() + Math.cos(Math.toRadians(shooter.getRotation())) * offsetDistance;
        double spawnY = shooter.getY() + Math.sin(Math.toRadians(shooter.getRotation())) * offsetDistance;

        bullet.setX(spawnX);
        bullet.setY(spawnY);
        bullet.setRotation(shooter.getRotation());

        return bullet;
    }


    // TODO: Use this to set shape of bullet based on direction??
    private void setShape(Entity entity) {
    }

}
