package ch17.ex03;

public class ResourceImpl implements Resource {

    int keyHash;
    boolean needsRelease = false;

    @Override
    public void use(Object key, Object... args) {

    }

    @Override
    public void release() {

    }
}
