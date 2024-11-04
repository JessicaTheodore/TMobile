package SpriteFont;

import Engine.GraphicsHandler;
import Maps.Level2ScreenMap;

import java.awt.*;

// This class represents a sprite font, which is graphic text (text drawn to the screen as if it were an image)
public class SpriteFont {
	protected String text;
	protected Font font;
	protected float x;
	protected float y;
	protected Color color;
	protected Color outlineColor;
	protected float outlineThickness = 1f;
	protected boolean showTextField = false;
	protected boolean needName = false;
	protected boolean showRanger = true;
	protected Level2ScreenMap level2Map;
	protected boolean isPlayerSpeaking = true;
	protected boolean treesAreSpeaking = false;

	public SpriteFont(String text, float x, float y, String fontName, int fontSize, Color color) {
		this.text = text;
		font = new Font(fontName, Font.PLAIN, fontSize);
		this.x = x;
		this.y = y;
		this.color = color;
		//just in case the thing messes up, only spritefont with boolean in the constructor
		// will run the field, otherwise it will be false
		this.showTextField = false;
		this.needName = false;
	}

	public SpriteFont(String text, float x, float y, Font font, Color color) {
		this.text = text;
		this.font = font;
		this.x = x;
		this.y = y;
		this.color = color;
		this.showTextField = false;
		this.needName = false;
		this.treesAreSpeaking = true;
	}

	public SpriteFont(String text, float x, float y, Font font, Color color, Level2ScreenMap level2Map) {
		this.text = text;
		this.font = font;
		this.x = x;
		this.y = y;
		this.color = color;
		this.showTextField = false;
		this.needName = false;
		this.level2Map = level2Map;
		this.treesAreSpeaking = true;
	}

	public SpriteFont(String text, float x, float y, Font font, Color color, boolean showTextField) {
		this.text = text;
		this.font = font;
		this.x = x;
		this.y = y;
		this.color = color;
		this.showTextField = showTextField;
		this.needName = false;
	}

	public SpriteFont(String text, boolean needName, float x, float y, Font font, Color color) {
		this.text = text;
		this.needName = needName;
		this.font = font;
		this.x = x;
		this.y = y;
		this.color = color;
		this.showTextField = false;
	}

	public SpriteFont(String text, float x, float y, Font font, Color color, boolean showRanger, Level2ScreenMap level2Map, boolean isPlayerSpeaking) {
		this.text = text;
		this.font = font;
		this.x = x;
		this.y = y;
		this.color = color;
		this.needName = false;
		this.showRanger = showRanger;
		this.level2Map = level2Map;
		this.isPlayerSpeaking = isPlayerSpeaking;
		this.treesAreSpeaking = false;
	}


	public SpriteFont(String text, boolean needName, float x, float y, Font font, Color color, boolean showRanger, Level2ScreenMap level2Map, boolean isPlayerSpeaking) {
		this.text = text;
		this.needName = needName;
		this.font = font;
		this.x = x;
		this.y = y;
		this.color = color;
		this.showTextField = false;
		this.showRanger = showRanger;
		this.level2Map = level2Map;
		this.isPlayerSpeaking = isPlayerSpeaking;
		this.treesAreSpeaking = false;
	}

	public boolean isShowTextField() {
		return showTextField;
	}

	public boolean doesNeedNameReplacement() {
		return needName;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getText() {
		return text;
	}

	public Font getFont() { return font; }

	public void setText(String text) {
		this.text = text;
	}

	public void setFontName(String fontName) {
		this.font = new Font(fontName, this.font.getStyle(), this.font.getSize());
	}

	public void setFontStyle(int fontStyle) {
		this.font = new Font(font.getFontName(), fontStyle, this.font.getSize());
	}

	public void setFontSize(int size) {
		this.font = new Font(font.getFontName(), this.font.getStyle(), size);
	}

	public void setFont(Font font) { this.font = font; }

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public void setOutlineThickness(float outlineThickness) {
		this.outlineThickness = outlineThickness;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void moveX(float dx) {
		x += dx;
	}

	public void moveY(float dy) {
		y += dy;
	}

	public void moveRight(float dx) {
		x += dx;
	}

	public void moveLeft(float dx) {
		x -= dx;
	}

	public void moveDown(float dy) {
		y += dy;
	}

	public void moveUp(float dy) {
		y -= dy;
	}

	private int getAscent(Graphics2D graphics) {
		FontMetrics fm = graphics.getFontMetrics(font);
		return fm.getAscent();
	}

	public void draw(GraphicsHandler graphicsHandler) {
		if(level2Map != null) {
			this.level2Map.setShowRanger(this.showRanger);
			this.level2Map.setPlayerSpeaking(this.isPlayerSpeaking);
			this.level2Map.setTreesSpeaking(this.treesAreSpeaking);
		}
		int ascent = getAscent(graphicsHandler.getGraphics());
		if (outlineColor != null && !outlineColor.equals(color)) {
			graphicsHandler.drawStringWithOutline(text, Math.round(x), Math.round(y) + ascent, font, color, outlineColor, outlineThickness);
		} else {
			graphicsHandler.drawString(text, Math.round(x), Math.round(y) + ascent, font, color);
		}
	}

	// this can be called instead of regular draw to have the text drop to the next line in graphics space on a new line character
	public void drawWithParsedNewLines(GraphicsHandler graphicsHandler, int gapBetweenLines) {
		if(level2Map != null) {
			this.level2Map.setShowRanger(this.showRanger);
			this.level2Map.setPlayerSpeaking(this.isPlayerSpeaking);
			this.level2Map.setTreesSpeaking(this.treesAreSpeaking);
		}
		int ascent = getAscent(graphicsHandler.getGraphics());
		int drawLocationY = Math.round(this.y) + ascent;
		for (String line: text.split("\n")) {
			if (outlineColor != null && !outlineColor.equals(color)) {
				graphicsHandler.drawStringWithOutline(line, Math.round(x), drawLocationY, font, color, outlineColor, outlineThickness);
			} else {
				//takes the user's inputted name
				//added second argument to equal false
				if (isShowTextField()) {
                    graphicsHandler.drawString(line, Math.round(x), drawLocationY, font, color, true);
					showTextField = false;
				} else if (doesNeedNameReplacement()) {
					graphicsHandler.drawString(line, graphicsHandler.getPlayerName(), Math.round(x), drawLocationY, font, color);
				} else {
                    graphicsHandler.drawString(line, Math.round(x), drawLocationY, font, color);
                }
			}
			drawLocationY += font.getSize() + gapBetweenLines;
		}
	}
}
