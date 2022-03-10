package Information;

public class Tag {
    /**
     * Not to confuse with java.awt.Color, this Color
     * is an enum helping to determine the side of 
     * the player and pieces. The two possible choice 
     * of the enum is BLACK and WHITE.
     */
    public static enum Color { BLACK, WHITE, OVER }
    public static enum Status { ACTIVE, CHECK, CHECKMATE, STAKEMATE, SURRENDER }

    // image size
    public static final int IMAGE_WIDTH =75;
    public static final int IMAGE_HEIGHT = 75;
    
    // lazy chess icon
    public static final String LAZY_ICON = "VoiceControlChess\\assets\\lazy_chess_icon.jpg";
    public static final String TITLE = "Voice Controlled Chess";

    // white piece images
    public static final String WHITE_KING = "VoiceControlChess\\assets\\white_king.png";
    public static final String WHITE_QUEEN = "VoiceControlChess\\assets\\white_queen.png";
    public static final String WHITE_KNIGHT = "VoiceControlChess\\assets\\white_knight.png";
    public static final String WHITE_ROOK = "VoiceControlChess\\assets\\white_rook.png";
    public static final String WHITE_BISHOP = "VoiceControlChess\\assets\\white_bishop.png";
    public static final String WHITE_PAWN = "VoiceControlChess\\assets\\white_pawn.png";

    // black piece images
    public static final String BLACK_KING = "VoiceControlChess\\assets\\black_king.png";
    public static final String BLACK_QUEEN = "VoiceControlChess\\assets\\black_queen.png";
    public static final String BLACK_KNIGHT = "VoiceControlChess\\assets\\black_knight.png";
    public static final String BLACK_ROOK = "VoiceControlChess\\assets\\black_rook.png";
    public static final String BLACK_BISHOP = "VoiceControlChess\\assets\\black_bishop.png";
    public static final String BLACK_PAWN = "VoiceControlChess\\assets\\black_pawn.png";

    // const for board size
    public static final int SIZE_MAX = 8;
    public static final int SIZE_MIN = 0;
}