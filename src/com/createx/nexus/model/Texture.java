package com.createx.nexus.model;

import java.awt.image.BufferedImage;

import static com.createx.nexus.utility.Documents.*;
import static com.createx.nexus.utility.Memory.*;
import static org.lwjgl.opengl.GL11.*;

public class Texture {

    private int width;
    private int height;
    private int handle;

    public Texture(String path) {
        BufferedImage bufferedImage = readImage(path);

        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();

        int length = width * height;
        int[] pixels = new int[length];

        bufferedImage.getRGB(0, 0, width, height, pixels, 0, width);

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
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
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
