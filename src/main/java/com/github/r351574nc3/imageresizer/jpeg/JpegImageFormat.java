package com.github.r351574nc3.imageresizer.jpeg;

import com.github.r351574nc3.imageresizer.ImageFormat;

/**
 * JPEG Implementation of the {@link ImageFormat} interface.
 *
 * @author Leo Przybylski
 */
public class JpegImageFormat implements ImageFormat {

    static final String EXTENSION = "jpg";

    @Override
    public boolean matches(final String fileName) {
        return fileName.toLowerCase().endsWith("." + getExtension());
    }

    @Override
    public String getExtension() {
        return EXTENSION.toLowerCase();
    }
}
