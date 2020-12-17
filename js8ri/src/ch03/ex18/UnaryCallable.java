package ch03.ex18;

public interface UnaryCallable<V1, V2> {

    V2 call(V1 v) throws Exception;
}
