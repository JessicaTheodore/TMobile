package GameObject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// This class is for reading in a SpriteSheet (collection of images laid out in a specific way)
// As long as each graphic on the sheet is the same size, it can parse it into sub images
public class SpriteSheet {
	protected BufferedImage image;
	protected int spriteWidth;
	protected int spriteHeight;
	protected int rowLength;
	protected int columnLength;

	public SpriteSheet(BufferedImage image, int spriteWidth, int spriteHeight) {
		this.image = image;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.rowLength = image.getHeight() / spriteHeight;
		this.columnLength = image.getWidth() / spriteWidth;
	}

	public SpriteSheet(BufferedImage image, int spriteWidth, int spriteHeight, boolean tint) {
		this.image = image;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.rowLength = image.getHeight() / spriteHeight;
		this.columnLength = image.getWidth() / spriteWidth;
	}

	// returns a subimage from the sprite sheet image based on the row and column
	public BufferedImage getSprite(int spriteNumber, int animationNumber) {
		return image.getSubimage((animationNumber * spriteWidth) + animationNumber, (spriteNumber * spriteHeight) + spriteNumber, spriteWidth, spriteHeight);
	}

	// returns a subimage from the sprite sheet image based on the row and column
	// this does the same as "getSprite", I added two methods that do the same thing for some reason
	public BufferedImage getSubImage(int row, int column) {
		return image.getSubimage((column * spriteWidth) + column, (row * spriteHeight) + row, spriteWidth, spriteHeight);
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getSpriteWidth() {
		return spriteWidth;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}

	public void setImage(BufferedImage image){
		this.image = image;
	}

	public static BufferedImage tintExcludingColor(BufferedImage image, Color tint, float intensity, int excludeHex) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new image to store the tinted result
        BufferedImage tintedImage = new BufferedImage(width, height, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);

                // Check if the current pixel matches the exclude color
                if (rgb == excludeHex) {
                    // Copy the pixel as-is
                    tintedImage.setRGB(x, y, rgb);
                } else {
                    // Apply tint to other pixels
                    int alpha = (rgb >> 24) & 0xFF;
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    // Blend the current color with the tint color
                    red = (int) ((1 - intensity) * red + intensity * tint.getRed());
                    green = (int) ((1 - intensity) * green + intensity * tint.getGreen());
                    blue = (int) ((1 - intensity) * blue + intensity * tint.getBlue());

                    // Clamp values to 0-255
                    red = Math.min(255, Math.max(0, red));
                    green = Math.min(255, Math.max(0, green));
                    blue = Math.min(255, Math.max(0, blue));

                    // Set the new RGB value
                    int newRgb = (alpha << 24) | (red << 16) | (green << 8) | blue;
                    tintedImage.setRGB(x, y, newRgb);
                }
            }
        }
        return tintedImage;
    }

	public static BufferedImage removeTint(BufferedImage image, int tintColor, float intensity) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage resultImage = new BufferedImage(width, height, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);

                // Extract ARGB components
                int alpha = (rgb >> 24) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Extract the tint color components
                int tintRed = (tintColor >> 16) & 0xFF;
                int tintGreen = (tintColor >> 8) & 0xFF;
                int tintBlue = tintColor & 0xFF;

                // Reverse the tint effect
                red = (int) ((red - intensity * tintRed) / (1 - intensity));
                green = (int) ((green - intensity * tintGreen) / (1 - intensity));
                blue = (int) ((blue - intensity * tintBlue) / (1 - intensity));

                // Clamp values to 0-255
                red = Math.min(255, Math.max(0, red));
                green = Math.min(255, Math.max(0, green));
                blue = Math.min(255, Math.max(0, blue));

                // Set the new RGB value
                int newRgb = (alpha << 24) | (red << 16) | (green << 8) | blue;
                resultImage.setRGB(x, y, newRgb);
            }
        }
        return resultImage;
    }
}
