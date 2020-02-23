package core;

public interface IGameBoard {
    int GetRows();
    int GetColumns();
    String GetValue(int row, int column);
    void SetValue(int row, int column, Player player);
}
