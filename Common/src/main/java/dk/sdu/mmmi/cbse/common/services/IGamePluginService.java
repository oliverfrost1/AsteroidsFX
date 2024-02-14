package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Used handle the start and stop conditions of the plugins in the game.
 */
public interface IGamePluginService {

    /**
     * Called when the plugin is started.
     * @param gameData
     * @param world
     * @precondition The plugin is not loaded.
     * @postcondition The plugin is loaded.
     */
    void start(GameData gameData, World world);

    /**
     * Called when the plugin is stopped.
     * @param gameData
     * @param world
     * @precondition The plugin is loaded and running.
     * @postcondition The plugin is unloaded and cleaned up.
     */
    void stop(GameData gameData, World world);
}
