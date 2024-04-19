import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collisions {
    requires Common;
    requires Asteroid;
    requires CommonAsteroid;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisions.CollisionDetectionSystem, dk.sdu.mmmi.cbse.collisions.TestingSplitPackages;
    uses AsteroidSPI;
}