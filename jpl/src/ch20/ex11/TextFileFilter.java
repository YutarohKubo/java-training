package ch20.ex11;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class TextFileFilter implements FileFilter {

    private String wordEnd;

    @Override
    public boolean accept(File pathname) {
        return pathname.getName().matches(".*" + wordEnd);
    }

    public TextFileFilter (String wordEnd) {
        this.wordEnd = wordEnd;
    }
}
