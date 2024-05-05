import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;

module ScoreUpdater {
    exports dk.sdu.mmmi.cbse.scoreupdater;
    requires Common;
    requires java.net.http;
    requires javafx.graphics;
    provides IUIProcessingService with dk.sdu.mmmi.cbse.scoreupdater.ScoreUpdater;
}