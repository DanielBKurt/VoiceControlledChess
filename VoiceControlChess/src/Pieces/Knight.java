package Pieces;

import java.util.ArrayList;

import BoardComponents.Position;
import Information.Tag.Color;

public class Knight extends Piece {
    public Knight(Color side, Position start, String imageFileName) { super(side, start, imageFileName); }

    @Override
    public ArrayList<Position> getLegalMoves(Position[][] gameBoard) {
        ArrayList<Position> knightLegalMoves = new ArrayList<Position>();
        final int startX = this.getPosition().getPosY(); // swaping x with y
        final int startY = this.getPosition().getPosX(); // swaping y with x
        final int upDownTwo[] = {startY + 2, startY - 2};
        final int upDown[] = { startY + 1, startY - 1};
        final int leftRightTwo[] = { startX + 2, startX - 2};
        final int leftRight[] = {startX + 1, startX - 1};

         // check up down
         for(int i = 0; i < 2; i++) {
             for(int j = 0; j < 2; j++) {
                if(positionInBounds(upDownTwo[i]) && positionInBounds(leftRight[j])) {
                    if(basicLegalPosition(gameBoard, leftRight[j], upDownTwo[i])) {
                        knightLegalMoves.add(gameBoard[leftRight[j]][upDownTwo[i]]);
                    }
                }
             }
         }
        // check left right
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
               if(positionInBounds(leftRightTwo[i]) && positionInBounds(upDown[j])) {
                   if(basicLegalPosition(gameBoard, leftRightTwo[i], upDown[j])) {
                       knightLegalMoves.add(gameBoard[leftRightTwo[i]][upDown[j]]);
                   }
               }
            }
        }  
        return knightLegalMoves;
    }

    @Override
    public String name() {
        return "(N)";
    }
}
