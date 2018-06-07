package sample;

import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.util.Pair;

import java.util.Optional;

public class Controller {
    public GridPane grid;
    public Boolean aiMove = true;
    private Ai ai;
    private Pane[][] panes;
    private Optional<Controller> someoneWon;
    private boolean humanTurn=true;
    private Pair<Integer,Integer> firstMove;

    public void initialize() {
        int numCols = 25;
        int numRows = 25;
        panes=new Pane[numCols][numRows];
        ai = new Ai();
       //  firstMove=ai.moveToMax();

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
        //if (rowIndex==firstMove.getKey() && colIndex==firstMove.getValue()) pane.setStyle("-fx-background-color: red;" + "-fx-border-color: black");
        panes[rowIndex][colIndex]=pane;
        pane.setOnMouseClicked(event ->
                {
                    if (humanTurn && ai.getCurrentTable()[rowIndex][colIndex].isEmpty()){

                        new Thread(() -> {
                            ai.moveEnemy(rowIndex, colIndex);
                            pane.setStyle("-fx-background-color: green;" + "-fx-border-color: black");
                            humanTurn=!humanTurn;
                            Pair<Integer,Integer>pair=ai.moveToMax();

                            panes[pair.getKey()][pair.getValue()].setStyle("-fx-background-color: red;" + "-fx-border-color: black");
                            humanTurn=!humanTurn;
                        }).start();
                    }


                }
        );
        grid.add(pane, colIndex, rowIndex);
    }
    private  void ifSomeOneWon(String who){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(who +" is the winner");

        alert.showAndWait();
    }

}
