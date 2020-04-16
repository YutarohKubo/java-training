package ch17.ex03_text;

public class Main {

    public static void main(String[] args) {
        ResourceManager resourceManager = new ResourceManager();
        for (long i = 0; i < 1000; i++) {
            Object object = new Object();
            Resource resource = resourceManager.getResource(object);
            System.gc();
            resource.use(object);
        }
        resourceManager.shutdown();
    }

}
