package BoardComponents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import GUI.GameGUI;
import Information.Tag;
import Information.Tag.Color;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

public class Board extends JPanel implements MouseListener {
    private static final Dimension FRA_DIMENSION = new Dimension((Tag.IMAGE_WIDTH + 10) * Tag.SIZE_MAX, (Tag.IMAGE_HEIGHT + 10) * Tag.SIZE_MAX);

    private Color turn;
    private GameGUI gameGUI;
    private Position[][] gameBoard;

    private int selectedX;
    private int selectedY;
    private Piece selectedPiece;
    public ArrayList<Position> selectedMovablePositions;
    
    public Board(GameGUI gui) {
        this.setGameGUI(gui);
        this.setGameBoard(new Position[Tag.SIZE_MAX][Tag.SIZE_MAX]);
        setLayout(new GridLayout(Tag.SIZE_MAX, Tag.SIZE_MAX, 0, 0));
        this.addMouseListener(this);
        this.createNewBoardPositions();
        this.initializePiecesToBoard();
        this.setPanelDimensions(FRA_DIMENSION);
        this.setTurn(Color.WHITE);
    }

    /***
     * creation of the board results in x and  y coordinates being fliped,
     * compensated in getting legal moves by altering x and y for y and x 
     */
    private void createNewBoardPositions() {
        for(int i = 0; i < Tag.SIZE_MAX; i++) {
            for(int j = 0; j < Tag.SIZE_MAX; j++){
                if(((i % 2) == 0 && (j % 2) == 0) || ((i % 2) == 1 && (j % 2) == 1)) {
                    this.gameBoard[i][j] = new Position(j, i, false);
                    this.add(gameBoard[i][j]);
                } else {
                    this.gameBoard[i][j] = new Position(j, i, true);
                    this.add(gameBoard[i][j]);
                }
            }
        }
    }

    private void initializePiecesToBoard() {
        // generate rook
        gameBoard[0][0].setPiece(new Rook(Tag.Color.BLACK, gameBoard[0][0], Tag.BLACK_ROOK));
        gameBoard[0][7].setPiece(new Rook(Tag.Color.BLACK, gameBoard[0][7], Tag.BLACK_ROOK));
        gameBoard[7][0].setPiece(new Rook(Tag.Color.WHITE, gameBoard[7][0], Tag.WHITE_ROOK));
        gameBoard[7][7].setPiece(new Rook(Tag.Color.WHITE, gameBoard[7][7], Tag.WHITE_ROOK));
        // generate knight
        gameBoard[0][1].setPiece(new Knight(Tag.Color.BLACK, gameBoard[0][1], Tag.BLACK_KNIGHT));
        gameBoard[0][6].setPiece(new Knight(Tag.Color.BLACK, gameBoard[0][6], Tag.BLACK_KNIGHT));
        gameBoard[7][1].setPiece(new Knight(Tag.Color.WHITE, gameBoard[7][1], Tag.WHITE_KNIGHT));
        gameBoard[7][6].setPiece(new Knight(Tag.Color.WHITE, gameBoard[7][6], Tag.WHITE_KNIGHT));
        // generate bishop
        gameBoard[0][2].setPiece(new Bishop(Tag.Color.BLACK, gameBoard[0][2], Tag.BLACK_BISHOP));
        gameBoard[0][5].setPiece(new Bishop(Tag.Color.BLACK, gameBoard[0][5], Tag.BLACK_BISHOP));
        gameBoard[7][2].setPiece(new Bishop(Tag.Color.WHITE, gameBoard[7][2], Tag.WHITE_BISHOP));
        gameBoard[7][5].setPiece(new Bishop(Tag.Color.WHITE, gameBoard[7][5], Tag.WHITE_BISHOP));
        // generate queen
        gameBoard[0][3].setPiece(new Queen(Tag.Color.BLACK, gameBoard[0][3], Tag.BLACK_QUEEN));
        gameBoard[7][3].setPiece(new Queen(Tag.Color.WHITE, gameBoard[7][3], Tag.WHITE_QUEEN));
        // generate king
        gameBoard[0][4].setPiece(new King(Tag.Color.BLACK, gameBoard[0][4], Tag.BLACK_KING));
        gameBoard[7][4].setPiece(new King(Tag.Color.WHITE, gameBoard[7][4], Tag.WHITE_KING));
        // generate Pawn
        for(int i = 0; i < 8; i++) {
            gameBoard[1][i].setPiece(new Pawn(Tag.Color.BLACK, gameBoard[1][i], Tag.BLACK_PAWN));
            gameBoard[6][i].setPiece(new Pawn(Tag.Color.WHITE, gameBoard[6][i], Tag.WHITE_PAWN));
        }
    }

    private void setPanelDimensions(Dimension size){
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        this.setSize(size);
    }

    // setter
    public void setGameBoard(Position[][] board) { this.gameBoard = board; }
    public void setGameGUI(GameGUI gui) { this.gameGUI = gui; }
    public void setTurn(Color side) { this.turn = side; }
    public void setSelectedPiece(Piece selected) { this.selectedPiece = selected; }
    public void setSelectedX(int selected) { this.selectedX = selected; }
    public void setSelectedY(int selected) { this.selectedY = selected; }
    public void setSelectedMovablePositions(Piece piece) { this.selectedMovablePositions = piece.getLegalMoves(this.gameBoard); }
    public void nextTurn() { turn = (this.turn == Color.BLACK) ? Color.WHITE : Color.BLACK; }
    public void kingWasTaken() { turn = Color.OVER; }

    // getter
    public Color getTurn() { return this.turn; }
    public GameGUI getGameGUI() { return this.gameGUI; }
    public int getSelectedX() { return this.selectedX; }
    public int getSelectedY() { return this.selectedY; }
    public Position[][] getGameBoard() { return this.gameBoard; }
    public Piece getSelectedPiece() { return this.selectedPiece; }
    public ArrayList<Position> getMovablePositions() { return this.selectedMovablePositions; }

    // display / draw
    public void paintComponent(Graphics g) {
        for (int i = 0; i < Tag.SIZE_MAX; i++) 
            for (int j = 0; j < Tag.SIZE_MAX; j++) 
                this.gameBoard[j][i].paintComponent(g);
        if (selectedPiece != null)
            if (selectedPiece.getSide() == turn) 
                g.drawImage(selectedPiece.getImage(), selectedX, selectedY, null);
    }

    private void highlighedLegalPositions(ArrayList<Position> positions) {
        for(int i = 0; i < positions.size(); i++)
            positions.get(i).setHighLight(true);
        repaint();
    }

    private void dehighlightlegalPositions(ArrayList<Position> positions) {
        for(int i = 0; i < positions.size(); i++)
            positions.get(i).setHighLight(false);
        repaint();
    }

    private void deselectPiece() {
        if(selectedPiece != null) {
            dehighlightlegalPositions(selectedMovablePositions);
            selectedPiece = null;
        }
    }

    /***
     * TODO - add checkmate detection
     * controlling the game via mouse
     * left-click to select piece and move
     * right-click to deselect
     */
    @Override
    public void mouseClicked(MouseEvent e) {        
        Position clickedPosition = (Position) this.getComponentAt(new Point(e.getX(), e.getY()));

        if(e.getButton() == MouseEvent.BUTTON1 && selectedPiece == null) {
            if(!clickedPosition.isFree() && clickedPosition.getPiece().getSide() == turn) {
                selectedPiece = clickedPosition.getPiece();
                setSelectedMovablePositions(selectedPiece);
                highlighedLegalPositions(selectedMovablePositions);
            } else {
                deselectPiece();
            }
        } else if (e.getButton() == MouseEvent.BUTTON1 && selectedPiece != null) {
            if(clickedPosition.isFree() || clickedPosition.getPiece().getSide() != turn) {
                if(selectedMovablePositions.contains(clickedPosition)) {
                	boolean kingTaken = false;
                	if (!(clickedPosition.isFree()) && clickedPosition.getPiece().name().equals("(K)"))
                		kingTaken = true;
                    selectedPiece.move(clickedPosition);
                    deselectPiece();
                    if (kingTaken)
                    	kingWasTaken();
                    else
                    	nextTurn();
                }
            }
        } 
        else {
            deselectPiece();
        }
        repaint();
    }

    public void speechCalled(String speechReceived)
    {
    	if (speechReceived.equals("clear")) //say clear to unselect piece, clear because sphinx 4 cant understand unselect
    	{
    		deselectPiece();
    		return;
    	}
    	else if (speechReceived.equals("<unk>"))
    	{
    		//<unk> means recognizer did not understand speech
    		System.out.println("I did not understand what you said");
    		return;
    	}
    	String[] coordinates = speechReceived.split(" ");
        if (coordinates.length == 1)
        {
        	//rarely passes in single word that is not unk, should only be passing in two words, prevents
        	//out of bounds error by accessing coordinates[1] below
        	System.out.println("I did not understand what you said");
    		return;
        }
        String[] xcoords = {"alpha", "bravo", "charlie", "delta", "echo",
                            "foxtrot", "golf", "hotel"};
        String[] ycoords = {"one", "two", "three", "four", "five", "six",
                            "seven", "eight"};
        int x = 0;
        int y = 0;
        for (int i = 0; i < 8; i++)
        {
            if (xcoords[i].equals(coordinates[0]))
                x = i;
            if (ycoords[i].equals(coordinates[1]))
                y = i;
        }

        //board display is flipped on x and y, 7 - y to account for it being reversed
        Position spokenPosition = gameBoard[7 - y][x];

        if(selectedPiece == null) {
            if(!spokenPosition.isFree() && spokenPosition.getPiece().getSide() == turn) {
                selectedPiece = spokenPosition.getPiece();
                setSelectedMovablePositions(selectedPiece);
                highlighedLegalPositions(selectedMovablePositions);
            } else {
                deselectPiece();
            }
        } else if (selectedPiece != null) {
            if(spokenPosition.isFree() || spokenPosition.getPiece().getSide() != turn) {
                if(selectedMovablePositions.contains(spokenPosition)) {
                	boolean kingTaken = false;
                	if (!(spokenPosition.isFree()) && spokenPosition.getPiece().name().equals("(K)"))
                		kingTaken = true;
                    selectedPiece.move(spokenPosition);
                    deselectPiece();
                    if (kingTaken)
                    	kingWasTaken();
                    else
                    	nextTurn();
                }
            }
        } 
        else {
            deselectPiece();
        }
        repaint();
    }


    /**
     * since the board implements MouseListner, 
     * the following methods have to be overridden. 
     * currently left empty as they are not needed
     */
    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

}