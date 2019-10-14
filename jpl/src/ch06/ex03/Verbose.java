package ch06.ex03;

public interface Verbose {

    enum Level {
        SILENT,
        TERSE,
        NORMAL,
        VERBOSE,
    }

    void setVerbosity(int level);
    int getVerbosity();
}
