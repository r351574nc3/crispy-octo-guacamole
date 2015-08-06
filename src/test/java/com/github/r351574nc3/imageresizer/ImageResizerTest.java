package com.github.r351574nc3.imageresizer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;

import static org.junit.Assert.*;

public class ImageResizerTest {

    protected static final File RESIZED_IMAGES_PATH = new File("resized_images");
    protected static final File XROOT_PATH          = new File(RESIZED_IMAGES_PATH, "xroot");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void tearDown() throws Exception {
        // RESIZED_IMAGES_PATH.delete();
    }

    @Test
    public void testNormalJpg() {
        Main.main("target/test-classes/xroot.jpg");

        assertTrue(XROOT_PATH.exists());
        assertTrue(new File(XROOT_PATH, "xroot_640x480.jpg").exists());
        assertTrue(new File(XROOT_PATH, "xroot_1920x1080.jpg").exists());
        assertTrue(new File(XROOT_PATH, "xroot_1440x960.jpg").exists());
    }

    @Test
    public void testTxt() {
        Main.main("xroot.txt");
    }

    @Test
    public void testNoExtension() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        
        Main.main("xroot");        
    }
}
