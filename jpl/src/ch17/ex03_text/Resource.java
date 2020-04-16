package ch17.ex03_text;

public interface Resource {
    void use(Object key, Object... args);
    void release();
}
