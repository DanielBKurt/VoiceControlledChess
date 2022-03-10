package Pieces;

import java.util.ArrayList;

import BoardComponents.Position;
import Information.Tag;
import Information.Tag.Color;

public class Pawn extends Piece {
    private int up;
    private Position firsPosition;
    
    public Pawn(Color side, Position start, String imageFileName) {
        super(side, start, imageFileName);
        this.firsPosition = start;
        if(this.getSide() == Tag.Color.BLACK) this.up = 1;
        else this.up = -1;
    }

    private boolean isFirstMove() { return this.getPosition() == firsPosition; }

    @Override
    public ArrayList<Position> getLegalMoves(Position[][] gameBoard) {
        ArrayList<Position> pawnLegalMoves = new ArrayList<Position>();
        final int startX = this.getPosition().getPosY(); // swaping x with y
        final int startY = this.getPosition().getPosX(); // swaping y with x
        final int moveYPos = (startY + 1 * this.up);
        final int moveYNeg = (startY - 1 * this.up);
        final int moveX = (startX + 1 * this.up);

        // first move condition
        if(isFirstMove()) {
            for(int i = 1; i <= 2; i++ ) {
                if(gameBoard[startX  + i * this.up][startY].isFree())
                    pawnLegalMoves.add(gameBoard[startX  + i * this.up][startY]);
                else
                    break;
            }
        }
        // check one spot in front, left, and right
        if(positionInBounds(moveX)) {
            if(gameBoard[moveX][startY].isFree())
                pawnLegalMoves.add(gameBoard[moveX][startY]);
            if(positionInBounds(moveYNeg) && complexLegalPostion(gameBoard, moveX, moveYNeg))
                pawnLegalMoves.add(gameBoard[moveX][moveYNeg]);
            if(positionInBounds(moveYPos) && complexLegalPostion(gameBoard, moveX, moveYPos))
                pawnLegalMoves.add(gameBoard[moveX][moveYPos]);
        }
        return pawnLegalMoves;
    }

    @Override
    public String name() {
        return "(P)";
    }
}