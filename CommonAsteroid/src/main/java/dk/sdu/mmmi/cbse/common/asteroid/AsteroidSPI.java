package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

public interface AsteroidSPI {
    /**
     * Split the asteroid into two smaller asteroids and remove the original asteroid
     *
     * @param asteroid
     * @param world
     * @return true if the asteroid was removed, false if the asteroid was split
     * @precondition asteroid is not null and world is not null
     * @postcondition asteroid is removed from the world and two new asteroids are added to the world
     */
    public boolean splitAsteroidOrRemoveIt(Entity asteroid, World world);
}
