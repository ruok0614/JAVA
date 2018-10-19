package reversi.system;

// このままだとPieceInputHandlerは正しくない
public interface PieceInputHandler {
    // 座標を返す処理を書く
    Point getPoint(ReadOnlyBoard board , Piece piece);
    void onFinish(ReadOnlyBoard board);
}
