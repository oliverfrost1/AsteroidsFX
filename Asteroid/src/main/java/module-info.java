import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
    exports dk.sdu.mmmi.cbse.asteroid;
    requires Common;
    requires CommonAsteroid;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroid.AsteroidPlugin;
    provides AsteroidSPI with dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem;

}
