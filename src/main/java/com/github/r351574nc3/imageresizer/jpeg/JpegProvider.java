package com.github.r351574nc3.imageresizer.jpeg;

import com.github.r351574nc3.imageresizer.ImageFormat;
import com.github.r351574nc3.imageresizer.ImageFormatProvider;
import com.github.r351574nc3.imageresizer.ImageResizer;
import com.github.r351574nc3.imageresizer.annotations.ProvidesFor;

/**
 * 
 */
@ProvidesFor(format = JpegImageFormat.class)
public class JpegProvider implements ImageFormatProvider {
    protected ImageResizer resizer;

    protected ImageFormat format;
    
    @Override
    public void setFormat(final ImageFormat format) {
        this.format = format;
    }

    public ImageFormat getFormat() {
        return format;
    }

    @Override
    public ImageResizer getImageResizer() {
        if (resizer == null) {
            resizer = new JpegResizer();
        }
        return resizer;
    }
}

