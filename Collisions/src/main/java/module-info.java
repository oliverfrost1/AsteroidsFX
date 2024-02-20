import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem;

module Collisions {
    requires Common;
    requires Asteroid;
    requires CommonAsteroid;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisions.CollisionDetectionSystem;
}