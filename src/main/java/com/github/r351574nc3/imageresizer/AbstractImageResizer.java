package com.github.r351574nc3.imageresizer;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.LinkedList;
import java.util.List;

import com.github.r351574nc3.imageresizer.io.FileUtil;

/**
 * Generic image resizer implementation. Uses 3 basic presets.
 *
 * @author Leo Przybylski
 */
public abstract class AbstractImageResizer implements ImageResizer {

    protected static final String DELIMITER        = ".";
    protected static final Integer WIDTH_IDX       = 0;
    protected static final Integer HEIGHT_IDX      = 1;
    protected static final Integer[] SMALL_PRESET  = new Integer[] { 640, 480 };
    protected static final Integer[] MEDIUM_PRESET = new Integer[] { 1440, 960 };
    protected static final Integer[] LARGE_PRESET  = new Integer[] { 1920, 1080 };

    protected List<Integer[]> presets;

    protected AbstractImageResizer() {
        setPresets(new LinkedList<Integer[]>() {{
                add(SMALL_PRESET);
                add(MEDIUM_PRESET);
                add(LARGE_PRESET);
        }});
    }

    protected void setPresets(final List<Integer[]> presets) {
        this.presets = presets;
    }

    protected List<Integer[]> getPresets() {
        return this.presets;
    }

    protected BufferedImage resizeTo(final BufferedImage original, final Integer[] preset) throws Exception {
        return resizeTo(original, preset[WIDTH_IDX], preset[HEIGHT_IDX]);
    }

    protected BufferedImage resizeTo(final BufferedImage original, final int width, final int height) throws Exception {
        final int type = original.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : original.getType();
        
        final BufferedImage resizedImage = new BufferedImage(width, height, type);
        final Graphics2D g = resizedImage.createGraphics();
        g.drawImage(original, 0, 0, width, height, null);
        g.dispose();
		
        return resizedImage;
    }
    
    @Override
    public void resize(final String fileName) {
        if (fileName.lastIndexOf(DELIMITER) < 0) {
            throw new IllegalArgumentException(String.format("Unable to locate a provider for %s", fileName));
        }

        final String baseName = FileUtil.newInstance().getBaseName(fileName);
        final File resizedImagesDirectory = new File(FileUtil.newInstance().getResizedImagesDirectory(), baseName);

        if (!resizedImagesDirectory.exists()) {
            resizedImagesDirectory.mkdirs();
        }

        try {
            final BufferedImage original = ImageIO.read(new File(fileName));
            for (final Integer[] preset : getPresets()) {
                final BufferedImage resized = resizeTo(original, preset);
                final File resizedFile = new File(resizedImagesDirectory, convertName(baseName, preset));
                ImageIO.write(resized, getExtension(), resizedFile);
            }
        }
        catch (Exception e) {
        }
        
    }

    protected String convertName(final String prefix, final Integer[] preset) {
        return convertName(prefix, preset[WIDTH_IDX], preset[HEIGHT_IDX]);
    }

    protected String convertName(final String prefix, final Integer width, final Integer height) {
        return String.format("%s_%sx%s.%s", prefix, width, height, getExtension());
    }

    public abstract String getExtension();
}
