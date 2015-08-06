package com.github.r351574nc3.imageresizer;


public interface ImageFormatProvider {
    
    ImageResizer getImageResizer();

    void setFormat(ImageFormat format);

    ImageFormat getFormat();
}
