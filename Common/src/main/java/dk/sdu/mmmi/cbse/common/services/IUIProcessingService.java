package dk.sdu.mmmi.cbse.common.services;

import javafx.scene.layout.Pane;

/**
 * Used to update the UI
 */
public interface IUIProcessingService {

    /**
     * Called after the entity post processing has been done.
     *
     * @param gameWindow
     * @precondition The entity processing has been done.
     * @postcondition The post entity processing has been done.
     */
    void processUI(Pane gameWindow);
}
