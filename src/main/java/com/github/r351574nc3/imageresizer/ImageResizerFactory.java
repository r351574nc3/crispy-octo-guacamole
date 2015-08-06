package com.github.r351574nc3.imageresizer;

import com.github.r351574nc3.imageresizer.annotations.ProvidesFor;
import com.github.r351574nc3.imageresizer.jpeg.JpegProvider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;

public class ImageResizerFactory {

    protected static final String DELIMITER = ".";
    protected static ImageResizerFactory instance;
    protected static Map<String,ImageFormatProvider> providers;

    protected ImageResizerFactory() {
    }

    public static ImageResizerFactory newInstance() {
        if (instance == null) {
            instance = new ImageResizerFactory();
            providers = new HashMap<String,ImageFormatProvider>();
        }

        return instance;
    }

    public static void registerProvider(final Class<? extends ImageFormatProvider> providerClass) {
        try {
            final ImageFormatProvider provider = providerClass.newInstance();
            final ProvidesFor providesFor = providerClass.getAnnotation(ProvidesFor.class);
            final ImageFormat format = (ImageFormat) providesFor.format().newInstance();
            provider.setFormat(format);
            final String key = format.getExtension();

            providers.put(key, provider);
        }
        catch (Exception e) {
        }
    }

    protected ImageFormatProvider findProviderFor(final String fileName) {
        if (fileName.lastIndexOf(DELIMITER) < 0) {
            throw new IllegalArgumentException(String.format("Unable to locate a provider for %s", fileName));
        }
        
        final String suffix = fileName.substring(fileName.lastIndexOf(DELIMITER) + DELIMITER.length());
        return providers.get(suffix);
    }

    
    protected ImageResizer findResizerFor(final String fileName) {
        final ImageFormatProvider provider = findProviderFor(fileName);
        if (provider != null) {
            return provider.getImageResizer();
        }
        
        return null;
    }
}
