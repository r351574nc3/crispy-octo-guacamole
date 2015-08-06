package com.github.r351574nc3.imageresizer;

import static com.github.r351574nc3.logging.FormattedLogger.*;

public class Main {

    protected static final String JPEG_PROVIDER_NAME = "com.github.r351574nc3.imageresizer.jpeg.JpegProvider";

    protected ImageResizerFactory factory;

    public Main() {
    }

    public static void main(final String ... args) {
        if (args.length < 1) {
            printUsage();
            return;
        }

        debug("Registering provider: %s", JPEG_PROVIDER_NAME);
        registerProvider(JPEG_PROVIDER_NAME);

        final String fileName = args[0];
        final ImageResizer resizer = findResizerFor(fileName);

        if (resizer != null) {

            debug("Processing file: %s", fileName);
            resizer.resize(fileName);
        }
    }

    protected static void printUsage() {
        final StringBuilder usage = new StringBuilder();

        usage.append("Usage: \n")
            .append("        ./image_resizer <the_file.jpg>");

        System.out.println(usage.toString());
    }

    public static void registerProvider(final String providerName) {
        if (providerName == null) {
            throw new IllegalArgumentException("Cannot register an empty provider.");
        }

        
        try {
            final Class<? extends ImageFormatProvider> provider = (Class<? extends ImageFormatProvider>) Class.forName(providerName);
            ImageResizerFactory.newInstance().registerProvider(provider);
        }
        catch (Exception e) {
            if (isDebuggingEnabled()) {
                throwing(e);
            }
        }
    }

    protected static ImageResizer findResizerFor(final String fileName) {
        return ImageResizerFactory.newInstance().findResizerFor(fileName);
    }
}
