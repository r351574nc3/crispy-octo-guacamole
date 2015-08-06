package com.github.r351574nc3.imageresizer.io;

import java.io.File;

public class FileUtil {

    protected static FileUtil instance;
    protected static final String DELIMITER      = ".";
    protected static final String CWD_KEY        = "user.dir";
    protected static final String RESIZED_IMAGES = "resized_images";

    protected File workingDirectory;
    protected File resizedImagesDirectory;

    protected FileUtil() {
        workingDirectory = new File(System.getProperty(CWD_KEY));
        resizedImagesDirectory = new File(workingDirectory, RESIZED_IMAGES);
        if (!resizedImagesDirectory.exists()) {
            resizedImagesDirectory.mkdirs();
        }
    }

    public File getCurrentWorkingDirectory() {
        return workingDirectory;
    }

    public File getResizedImagesDirectory() {
        return resizedImagesDirectory;
    }

    public String getDirectory(final String fileName) {
        final File source = new File(fileName);
        if (source.isDirectory()) {
            return source.getPath();
        }
        return source.getParent();
    }

    public String getBaseName(final String fileName) {
        final File source = new File(fileName);

        if (source.isDirectory()) {
            return source.getPath();
        }

        final String baseName = source.getName();
        if (baseName.indexOf(DELIMITER) < 0) {
            return baseName;
        }
        
        return baseName.substring(0, baseName.lastIndexOf(DELIMITER));        
    }

    public static FileUtil newInstance() {
        if (instance == null) {
            instance = new FileUtil();
        }
        return instance;
    }
}
