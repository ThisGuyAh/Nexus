package com.codex.nexus.render;

import static com.codex.nexus.utility.Memory.store;
import static org.lwjgl.opengl.GL11.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Texture2D {

    private int width;
    private int height;
    private int handle;

    public Texture2D(String path) {
        int length = 0;
        int[] pixels = null;

        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(path));

            width = bufferedImage.getWidth();
            height = bufferedImage.getHeight();
            length = width * height;
            pixels = new int[length];

            bufferedImage.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        int[] rearrangedPixels = new int[length];

        for (int i = 0; i < length; i++) {
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = (pixels[i] & 0xff);

            rearrangedPixels[i] = a << 24 | b << 16 | g << 8 | r;
        }

        handle = glGenTextures();

        bind();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE,
            store(rearrangedPixels));
        unbind();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHandle() {
        return handle;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, handle);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void delete() {
        glDeleteTextures(handle);
    }

}
