package com.github.r351574nc3.imageresizer.jpeg;

import com.github.r351574nc3.imageresizer.AbstractImageResizer;

public class JpegResizer extends AbstractImageResizer {
    protected static final String JPG_EXTENSION = "jpg";


    public String getExtension() {
        return JPG_EXTENSION;
    }
}
