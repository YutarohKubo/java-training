package ch17.ex03;

public interface Resource {
    void use(int key, Object... args);
    void release();
}
