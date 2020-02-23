package core;

public interface IGameBoard {
    int GetRows();
    int GetColumns();
    int SetPiece(int column, Player player);
    String GetPiece(int row, int column);
    Player GetWhoseTurn();
    void SetWhoseTurn(Player player);
}
