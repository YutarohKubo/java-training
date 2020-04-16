package ch17.ex04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReaperThreadTest {

    @Test
    public void reaperThreadDieAfterShutDown() {
        ResourceManager resourceManager = new ResourceManager();
        for (long i = 0; i < 1000; i++) {
            Object object = new Object();
            Resource resource = resourceManager.getResource(object);
            resource.use(object);
        }
        resourceManager.shutdown();
        System.gc();
        try {
            //刈り取りスレッドが、mapからResourceを回収するのを待つために、10秒止める
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //map内からオブジェクトがgcされているため、刈り取りスレッドは死んでいる
        assertFalse(resourceManager.isReaperThreadAlive());
    }

    @Test
    public void reaperThreadAliveAfterShutDown() {
        ResourceManager resourceManager = new ResourceManager();
        for (long i = 0; i < 1000; i++) {
            Object object = new Object();
            Resource resource = resourceManager.getResource(object);
            resource.use(object);
        }
        resourceManager.shutdown();
        //map内にオブジェクトが存在しているため、刈り取りスレッドは生きている
        assertTrue(resourceManager.isReaperThreadAlive());
    }

}
