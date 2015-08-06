package com.github.r351574nc3.imageresizer;


public interface ImageFormat {

    boolean matches(String fileName);

    /**
     * The file extension of this image format in lowercase. For JPEG, it will be .jpg and for PNG it will be .png
     *
     * @return the {@link String} instance of the image format extension
     */
    String getExtension();
}
