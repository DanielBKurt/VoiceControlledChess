package Pieces;

import java.util.ArrayList;

import BoardComponents.Position;
import Information.Tag.Color;

public class Rook extends Piece {
    private boolean moved;
    
    public Rook(Color side, Position start, String imageFileName) {
        super(side, start, imageFileName);
        this.moved = false;
    }

    public boolean isFirstMove() { return this.moved == true; }
    public void setFirstMoveTrue() { this.moved = true; }

    @Override
    public ArrayList<Position> getLegalMoves(Position[][] gameBoard) {
        ArrayList<Position> rookLegalMoves = new ArrayList<Position>();
        rookLegalMoves.addAll(getLegalLinearPositions(gameBoard, this.getPosition()));
        return rookLegalMoves;
    }

    @Override
    public String name() { 
        return "(R)"; 
    }
    
}