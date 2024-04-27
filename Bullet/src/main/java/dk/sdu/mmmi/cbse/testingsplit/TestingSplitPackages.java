package dk.sdu.mmmi.cbse.testingsplit;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class TestingSplitPackages implements IPostEntityProcessingService {
    @Override
    public void postProcess(GameData gameData, World world) {
        System.out.println("Works in TestingSplitPackages in package dk.sdu.mmmi.cbse.bulletsystem");
    }
}
