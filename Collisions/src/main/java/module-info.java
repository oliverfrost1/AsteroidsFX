import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.testingsplit.TestingSplitPackages;

module Collisions {
    requires Common;
    requires Asteroid;
    requires CommonAsteroid;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisions.CollisionDetectionSystem, TestingSplitPackages;
    uses AsteroidSPI;
}