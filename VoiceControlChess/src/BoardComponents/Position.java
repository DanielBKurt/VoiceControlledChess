package BoardComponents;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import Pieces.Piece;

public class Position extends JComponent {
    private static final Color GEYSER = new Color(212, 219, 225);
    private static final Color SHADE_BORDER =  new Color(252, 0, 0);
    private static final Color ECRU_WHITE = new Color(251, 252, 247);
    private static final Color SHUTTLE_GRAY = new Color(89, 96, 112);
    private static final Color LIGHT_BORDER = new Color(248, 207, 168);
    private static final Color ATHS_SPECIAL = new Color(234, 240, 216);

    private int posX;
    private int posY;
    private Piece piece;
    private boolean highLight;
    private boolean ligherShade;
    private boolean displayPiece;

    public Position(int x, int y, boolean light) {
        setPosX(x);
        setPosY(y);
        setShade(light);
        setHighLight(false);
        setDisplayPiece(false);
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    // getters
    public int getPosX() { return this.posX; }
    public int getPosY() { return this.posY; }
    public Piece getPiece() { return this.piece; }
    public boolean isLighterShade() { return this.ligherShade == true; }
    public boolean isHighlighed() { return this.highLight == true; }
    public boolean getDisplayPiece() { return this.displayPiece; }
    public boolean isFree() { return (this.piece == null); }

    // setters
    public void setPosX(int x) { this.posX = x; }
    public void setPosY(int y) { this.posY = y; }
    public void setShade(Boolean shade) { this.ligherShade = shade; }
    public void setHighLight(Boolean highlighed) { this.highLight = highlighed; }
    public void setDisplayPiece(boolean display) { this.displayPiece = display; }

    public void setPiece(Piece piece) { 
        this.piece = piece;
        setDisplayPiece(true);
        piece.setPosition(this);
    }

    public Piece removePiece() {
        Piece temp = this.piece;
        setDisplayPiece(false);
        this.piece.setDead();
        this.piece = null;
        return temp;
    }

    
     // method to draw position to screen and piece
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw light or dark position
        if(this.ligherShade) { 
            if(highLight) g.setColor(ECRU_WHITE);
            else g.setColor(ATHS_SPECIAL);
        } else {
            if(highLight) g.setColor(GEYSER);
            else g.setColor(SHUTTLE_GRAY);
        }

        // highlight position
        if(highLight) this.setBorder(BorderFactory.createEtchedBorder(LIGHT_BORDER, SHADE_BORDER));
        else this.setBorder(BorderFactory.createEmptyBorder());

        // display piece if it is at current position
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        if(this.piece != null && displayPiece) piece.draw(g);
    }
}