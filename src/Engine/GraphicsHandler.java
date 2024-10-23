package Engine;

import GameObject.ImageEffect;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GraphicsHandler extends JPanel {
    private Graphics2D g;

    //variables for the user-inputted player name
    private String playerName;

    public Graphics2D getGraphics() {
        return g;
    }

    public void setGraphics(Graphics2D g) {
        this.g = g;
    }

    public void drawImage(BufferedImage image, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    public void drawImage(BufferedImage image, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void drawImage(BufferedImage image, int x, int y, int width, int height, ImageEffect imageEffect) {
        switch (imageEffect) {
            case NONE:
                drawImage(image, x, y, width, height);
                break;
            case FLIP_HORIZONTAL:
                g.drawImage(image, x + width, y, -width, height, null);
                break;
            case FLIP_VERTICAL:
                g.drawImage(image, x, y + height, width, -height, null);
                break;
            case FLIP_H_AND_V:
                g.drawImage(image, x + width, y + height, -width, -height, null);
                break;
        }
    }

    public void drawRectangle(int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.drawRect(x, y, width, height);
    }

    public void drawRectangle(int x, int y, int width, int height, Color color, int borderThickness) {
        g.setStroke(new BasicStroke(borderThickness));
        g.setColor(color);
        g.drawRect(x, y, width, height);
    }

    public void drawFilledRectangle(int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void drawFilledRectangleWithBorder(int x, int y, int width, int height, Color fillColor, Color borderColor, int borderThickness) {
        drawFilledRectangle(x, y, width, height, fillColor);
        drawRectangle(x, y, width, height, borderColor, borderThickness);
    }

    public void drawString(String text, int x, int y, Font font, Color color) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }

    //this will draw the actual pop up box that prompts the user to enter their name
    public void drawString(String text, int x, int y, Font font, Color color, boolean showTextField) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
        //setPlayerName("TEST");

        if (showTextField) {
            // System.out.println("This is working");
            JFrame frame = new JFrame("Enter your player name:");
            GraphicsHandler handler = new GraphicsHandler();
            frame.add(handler);
            frame.setSize(250, 150);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);

            //adds the textbox field for the player name input
            JTextField playerInputTextField = new JTextField(10);
            playerInputTextField.setFont(font);
            this.setLayout(null);
            this.add(playerInputTextField);

            //adds the button to continue
            JButton submitButton = new JButton("Continue");

            //will close the box when the continue button is clicked
            //also will take the player input for the same and set it to the playerName variable so it can show up! :D
            submitButton.addActionListener(e -> {
                setPlayerName(playerInputTextField.getText());
                frame.dispose();
            });

            //adds to the panel
            JPanel panel = new JPanel();
            panel.add(new JLabel("Enter your player name"));
            panel.add(playerInputTextField);
            panel.add(submitButton);

            //adds panel to the frame
            frame.add(panel);
            frame.setLocation(620, 400);
            frame.setVisible(true);
        }        
        
    }
    
    //formats the name into the game
    public void drawString(String text, String playerName, int x, int y, Font font, Color color) {
        String finalText = String.format(text, playerName);
        g.setFont(font);
        g.setColor(color);
        g.drawString(finalText, x, y);
    }

    // https://stackoverflow.com/a/35222059 and https://stackoverflow.com/a/31831120
    public void drawStringWithOutline(String text, int x, int y, Font font, Color textColor, Color outlineColor, float outlineThickness) {
        // remember original settings
        Color originalColor = g.getColor();
        Stroke originalStroke = g.getStroke();
        RenderingHints originalHints = g.getRenderingHints();
        g.setStroke(new BasicStroke(outlineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // create a glyph vector from your text
        GlyphVector glyphVector = font.createGlyphVector(g.getFontRenderContext(), text);

        // get the shape object
        Shape textShape = glyphVector.getOutline();
        AffineTransform at = new AffineTransform();
        at.setToTranslation(Math.round(x), Math.round(y));
        textShape = at.createTransformedShape(textShape);

        // activate anti aliasing for text rendering (if you want it to look nice)
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g.setColor(outlineColor);
        g.draw(textShape); // draw outline

        g.setColor(textColor);
        g.fill(textShape); // fill the shape

        // reset to original settings after painting
        g.setColor(originalColor);
        g.setStroke(originalStroke);
        g.setRenderingHints(originalHints);
    }

    public void setPlayerName(String name) {
        playerName = name;
    }
    
    public String getPlayerName() {
        return playerName;
    }
}
