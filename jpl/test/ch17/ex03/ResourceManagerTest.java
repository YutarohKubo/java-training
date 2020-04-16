package ch17.ex03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResourceManagerTest {

    @Test
    public void testUseResourceManager() {
        ResourceManager resourceManager = new ResourceManager();
        for (long i = 0; i < 1000; i++) {
            ResourceManager.Box resBox = resourceManager.getResource();
            resBox.getResource().use(resBox.getKey());
            System.gc();
            assertTrue(resourceManager.getMapSize() < 10);
        }
        resourceManager.shutdown();
    }

}
