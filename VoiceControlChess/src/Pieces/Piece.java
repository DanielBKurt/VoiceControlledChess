package Pieces;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import BoardComponents.Position;
import Information.Tag;
import Information.Tag.Color;

public abstract class Piece {
    private boolean alive;
    private Color side;
    private Position position;
    private BufferedImage image;

    public Piece(Color side, Position start, String imageFileName) {
        setAlive();
        setSide(side);
        setPosition(start);
        setImage(imageFileName);
    }

    // setters
    public void setAlive() { this.alive = true; }
    public void setDead() { this.alive = false; }
    public void setSide(Color side) { this.side = side; }
    public void setPosition(Position position) { this.position = position; }
    
    public void setImage(String imageFileName) { 
        if (this.image == null)
            try { this.image = ImageIO.read(new File(imageFileName));
            } catch (IOException e) { e.printStackTrace(); } 
    }

    // getters
    public Color getSide() { return this.side; }
    public boolean isAlive() { return this.alive == true; }
    public boolean isDead() { return this.alive == false; }
    public Position getPosition() { return this.position; }
    public Image getImage() { return this.image; }
    public void draw(Graphics g) { g.drawImage(this.getImage(), this.getPosition().getPosX(), this.getPosition().getPosY(), null); }

    // methods handling move
    public boolean move(Position desPosition) {
        boolean canMove = true;
        Piece desPiece = desPosition.getPiece();

        if(desPiece != null) {
            if(desPiece.getSide() == this.side) {
                canMove = false;
            } else {
                desPiece = null;
                desPosition.setPiece(this.position.removePiece());
            }
        } else {
            desPosition.setPiece(this.position.removePiece());
        }

        return canMove;
    }


    // methods handling legal check
    public boolean positionInBounds(int value) {
        return (value >= Tag.SIZE_MIN && value < Tag.SIZE_MAX);
    }

    public boolean basicLegalPosition(Position[][] gameBoard, int x, int y) {
        return ((positionInBounds(x) && positionInBounds(y)) && (gameBoard[x][y].isFree() || gameBoard[x][y].getPiece().getSide() != this.getSide()));
    }

    public boolean complexLegalPostion(Position[][] gameBoard, int x, int y) {
        return (!gameBoard[x][y].isFree() && gameBoard[x][y].getPiece().getSide() != this.getSide());
    }

    /***
     * this method gets all legal position linear from start position
     * @param gameBoard - board to check
     * @param start - starting position to get legal moves from
     * @return all legal positions north, south, east, and west from start
     */
    public ArrayList<Position> getLegalLinearPositions(Position[][] gameBoard, Position start) {
        ArrayList<Position> linearPositions = new ArrayList<Position>();
        int startX = start.getPosY(); // swaping x with y
        int startY = start.getPosX(); // swaping y with x
        
        // check north
        for(int i = startX - 1; i >= Tag.SIZE_MIN; i--) {
            if(gameBoard[i][startY].isFree()) {
                linearPositions.add(gameBoard[i][startY]);
            } else {
                if(gameBoard[i][startY].getPiece().getSide() != this.getSide()) 
                    linearPositions.add(gameBoard[i][startY]);
                break;
            }
        }
        // check south
        for(int i = startX + 1; i < Tag.SIZE_MAX; i++) {
            if(gameBoard[i][startY].isFree()) {
                linearPositions.add(gameBoard[i][startY]);
            } else { 
                if(gameBoard[i][startY].getPiece().getSide() != this.getSide())
                    linearPositions.add(gameBoard[i][startY]);
                break;
            }
        }
        // check east
        for(int i = startY + 1; i < Tag.SIZE_MAX; i++) {
            if(gameBoard[startX][i].isFree()) {
                linearPositions.add(gameBoard[startX][i]);
            } else { 
                if(gameBoard[startX][i].getPiece().getSide() != this.getSide())
                    linearPositions.add(gameBoard[startX][i]);
                break;
            }
        }
         // check west
         for(int i = startY - 1; i >= Tag.SIZE_MIN; i--) {
            if(gameBoard[startX][i].isFree()) {
                linearPositions.add(gameBoard[startX][i]);
            } else {
                if(gameBoard[startX][i].getPiece().getSide() != this.getSide())
                    linearPositions.add(gameBoard[startX][i]);
                break;
            }
        }

        return linearPositions;
    }

   /***
     * Method that gets all legal diagonal positions from start position
     * @param gameBoard - board to check
     * @param start - starting position to get legal moves from
     * @return all legal positions north east, north west, south east, and south west from start
     */
    public ArrayList<Position> getLegalDiagonalPositions(Position[][] gameBoard, Position start) {
        ArrayList<Position> diagonalPositions = new ArrayList<Position>();
        int startX = start.getPosY(); // swaping x with y
        int startY = start.getPosX(); // swaping y with x
        int x = startX + 1;
        int y = startY + 1;

        // check north east
        while (x < Tag.SIZE_MAX && y < Tag.SIZE_MAX) {
            if(gameBoard[x][y].isFree()) {
                diagonalPositions.add(gameBoard[x][y]);
            } else {
                if(gameBoard[x][y].getPiece().getSide() != this.getSide())
                    diagonalPositions.add(gameBoard[x][y]);
                break;
            }
            x++;
            y++;
        }
        // check south east
        x = startX - 1;
        y = startY - 1;
        while (x >= Tag.SIZE_MIN && y >= Tag.SIZE_MIN) {
            if(gameBoard[x][y].isFree()) {
                diagonalPositions.add(gameBoard[x][y]);
            } else {
                if(gameBoard[x][y].getPiece().getSide() != this.getSide())
                    diagonalPositions.add(gameBoard[x][y]);
                break;
            }
            x--;
            y--;
        }
        // check north west
        x = startX - 1;
        y = startY + 1;
        while (x >= Tag.SIZE_MIN && y < Tag.SIZE_MAX) {
            if(gameBoard[x][y].isFree()) {
                diagonalPositions.add(gameBoard[x][y]);
            } else {
                if(gameBoard[x][y].getPiece().getSide() != this.getSide())
                    diagonalPositions.add(gameBoard[x][y]);
                break;
            }
            x--;
            y++;
        }
        // check south west
        x = startX + 1;
        y = startY - 1;
        while (x < Tag.SIZE_MAX && y >= Tag.SIZE_MIN) {
            if(gameBoard[x][y].isFree()) {
                diagonalPositions.add(gameBoard[x][y]);
            } else {
                if(gameBoard[x][y].getPiece().getSide() != this.getSide())
                    diagonalPositions.add(gameBoard[x][y]);
                break;
            }
            x++;
            y--;
        }
        return diagonalPositions;
    }

    /**
     * abstract methods to return all legal moves from current position of piece
     * @param gameBoard - board to checl
     * @return - all legal moves from current postion on baord
     */
    public abstract ArrayList<Position> getLegalMoves(Position[][] gameBoard);
    
    public String name() { 
        return "(_)";
    }
}