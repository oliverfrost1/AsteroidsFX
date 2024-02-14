package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Used to process entities in the game world.
 */
public interface IEntityProcessingService {

    /**
     * Called for entity components that have been registered in the service.
     * Is called before drawing the entities.
     * Is not used not drawing the entities.
     * @param gameData
     * @param world
     * @precondition The last process is done, the last process'.
     * @postcondition All the needed processing from the entities has been done.
     */
    void process(GameData gameData, World world);
}
