package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Used to process logic after the entity logic has been processed.
 */
public interface IPostEntityProcessingService {

    /**
     * Called after the entity processing has been done.
     * @param gameData
     * @param world
     * @precondition The entity processing has been done.
     * @postcondition The post entity processing has been done.
     */
    void postProcess(GameData gameData, World world);
}
