package jpl.ch06.ex03;

public interface Verbose {
    enum Level{
        SILENT,TERSE,NOMAL,VERBODE
    }
    void setVerbose(Level level);
    Level getVerbosity();
}
