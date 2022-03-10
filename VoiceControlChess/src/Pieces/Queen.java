package Pieces;

import java.util.ArrayList;

import BoardComponents.Position;
import Information.Tag.*;

public class Queen extends Piece {
    public Queen(Color side, Position start, String imageFileName) { 
        super(side, start, imageFileName);
    }

    @Override
    public ArrayList<Position> getLegalMoves(Position[][] gameBoard) {
        ArrayList<Position> queenLegalMoves = new ArrayList<Position>();
        queenLegalMoves.addAll(getLegalLinearPositions(gameBoard, this.getPosition()));
        queenLegalMoves.addAll(getLegalDiagonalPositions(gameBoard, this.getPosition()));
        return queenLegalMoves;
    }
    
    @Override
    public String name() { 
        return "(Q)"; 
    }
}