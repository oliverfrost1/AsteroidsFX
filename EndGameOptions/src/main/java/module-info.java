import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import dk.sdu.mmmi.cbse.endgameoptions.EndGameOptionsHandler;

module EndGameOptions {
    exports dk.sdu.mmmi.cbse.endgameoptions;
    requires Common;
    requires javafx.graphics;
    provides IUIProcessingService with EndGameOptionsHandler;
}