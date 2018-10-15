package reversi;

// このままだとPieceInputHandlerは正しくない
public interface PieceInputHandler {
    // 座標を返す処理を書く
    Point getPoint(Board board ,Piece piece);
    void onFinish(Board board);
}
