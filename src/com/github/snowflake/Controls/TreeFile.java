package com.github.snowflake.Controls;

import java.io.File;

public class TreeFile extends File {

    public TreeFile(String pathname) {
        super(pathname);
    }

    @Override
    public String toString() {
        return getName();
    }
}
