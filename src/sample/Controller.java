package sample;

import javafx.scene.layout.*;

public class Controller {
    public GridPane grid;
    public Boolean aiMove = true;
    private Ai ai;

    public void initialize() {
        int numCols = 25;
        int numRows = 25;
        ai = new Ai();

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.SOMETIMES);
            grid.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(i, j);
            }
        }
    }

    private void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        pane.setStyle("-fx-border-style: solid");

        pane.setStyle("-fx-background-color: white;" + "-fx-border-color: black");

        pane.setOnMouseClicked(event ->
                {
                    ai.moveEnemy(rowIndex, colIndex);
                    ai.moveToMax();
                }
        );
        grid.add(pane, colIndex, rowIndex);
    }
}
