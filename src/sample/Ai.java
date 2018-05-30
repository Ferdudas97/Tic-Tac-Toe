package sample;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ai {
    private String[][] currentTable;
    private Integer[][] tableWithPotentialMoves;
    private Character humanSymbol;
    private Character aiSymbol;
    private int size = 25;

    public Ai() {
        currentTable = new String[size][size];
        tableWithPotentialMoves = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(currentTable[i], "");
            Arrays.fill(tableWithPotentialMoves[i], 0);

        }

        humanSymbol = 'x';
        aiSymbol = 'o';

    }

    public int makePotentialMovesTable(int x, int y) {
        int max = Integer.MIN_VALUE;
        tableWithPotentialMoves[x][y]++;
        max = tableWithPotentialMoves[x][y];
        int aiSymbols[]=new int[4];

        for (int i = 1; i < 5; i++) {
            if (x + i < size && y + i < size && !currentTable[x + i][y + i].equals(humanSymbol.toString())) {
                tableWithPotentialMoves[x + i][y + i]++;
                if (tableWithPotentialMoves[x + i][y + i] > max) max = tableWithPotentialMoves[x + i][y + i];
            }
            if (x + i < size && y - i >= 0 && !currentTable[x + i][y - i].equals(humanSymbol.toString())) {
                tableWithPotentialMoves[x + i][y - i]++;
                if (tableWithPotentialMoves[x + i][y - i] > max) max = tableWithPotentialMoves[x + i][y - i];

            }
            if (x + i < size && !currentTable[x + i][y].equals(humanSymbol.toString())) {
                tableWithPotentialMoves[x + i][y]++;
                if (tableWithPotentialMoves[x + i][y] > max) max = tableWithPotentialMoves[x + i][y];

            }
            if (x - i >= 0 && y - i >= 0 && !currentTable[x - i][y - i].equals(humanSymbol.toString())) {
                tableWithPotentialMoves[x - i][y - i]++;
                if (tableWithPotentialMoves[x - i][y - i] > max) max = tableWithPotentialMoves[x - i][y - i];

            }
            if (x - i >= 0 && !currentTable[x - i][y].equals(humanSymbol.toString())) {
                tableWithPotentialMoves[x - i][y]++;
                if (tableWithPotentialMoves[x - i][y] > max) max = tableWithPotentialMoves[x - i][y];

            }
            if (y - i >= 0 && !currentTable[x][y - i].equals(humanSymbol.toString())) {
                tableWithPotentialMoves[x][y - i]++;
                if (tableWithPotentialMoves[x][y - i] > max) max = tableWithPotentialMoves[x][y - i];

            }
            if (y + i < size && !currentTable[x][y + i].equals(humanSymbol.toString())) {
                tableWithPotentialMoves[x][y + i]++;
                if (tableWithPotentialMoves[x][y + i] > max) max = tableWithPotentialMoves[x][y + i];
            }
            if (x - i >= 0 && y + i < size && !currentTable[x - i][y + i].equals(humanSymbol.toString())) {
                tableWithPotentialMoves[x - i][y + i]++;
                if (tableWithPotentialMoves[x - i][y + i] > max) max = tableWithPotentialMoves[x - i][y + i];

            }
        }
        System.out.println(max);
        return max;

    }

    public void deletePotentialOfAiMoves(int x, int y) {

        tableWithPotentialMoves[x][y]--;

        for (int i = 0; i < 5; i++) {
            if (i > 0 && x + i < size && y + i < size && (!currentTable[x + i][y + i].equals(humanSymbol.toString()))) {
                tableWithPotentialMoves[x + i][y + i]--;
            }
            if (i > 0 && x + i < size && y - i >= 0 && (!currentTable[x + i][y - i].equals(humanSymbol.toString()))) {
                tableWithPotentialMoves[x + i][y - i]--;

            }
            if (i > 0 && x + i < size && (!currentTable[x + i][y].equals(humanSymbol.toString()))) {
                tableWithPotentialMoves[x + i][y]--;

            }
            if (i > 0 && x - i >= 0 && y - i >= 0 && (!currentTable[x - i][y - i].equals(humanSymbol.toString()))) {
                tableWithPotentialMoves[x - i][y - i]--;

            }
            if (i > 0 && x - i >= 0 && (!currentTable[x - i][y].equals(humanSymbol.toString()))) {
                tableWithPotentialMoves[x - i][y]--;

            }
            if (i > 0 && y - i >= 0 && (!currentTable[x][y - i].equals(humanSymbol.toString()))) {
                tableWithPotentialMoves[x][y - i]--;

            }
            if (i > 0 && y + i < size && (!currentTable[x][y + i].equals(humanSymbol.toString()))) {
                tableWithPotentialMoves[x][y + i]--;

            }
            if (i > 0 && x - i >= 0 && y + i < size && (!currentTable[x - i][y + i].equals(humanSymbol.toString()))) {
                tableWithPotentialMoves[x - i][y + i]--;

            }
        }
    }


    public void printCurrentTable() {
        System.out.println("Plansza");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(currentTable[i][j] + "|");

            }
            System.out.print("\n");

        }
    }

    public void printTableOfPotentials() {
        System.out.println("Tablica potencjałow");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(tableWithPotentialMoves[i][j] + "|");

            }
            System.out.print("\n");

        }
    }

    public void moveToMax() {
        List<Pair<Integer, Integer>> listWithMaxPairs = findMax();
        int max = Integer.MIN_VALUE;
        Pair<Integer, Integer> maxPair = new Pair<>(0, 0);
        int currValue = 0;

        for (Pair<Integer, Integer> pair : listWithMaxPairs) {
            currValue = makePotentialMovesTable(pair.getKey(), pair.getValue());
            System.out.println("max = " + currValue);

            if (currValue > max) {
                max = currValue;

                maxPair = pair;
            }
            deletePotentialOfAiMoves(pair.getKey(), pair.getValue());


        }
        moveMe(maxPair.getKey(), maxPair.getValue());
    }

    public List<Pair<Integer, Integer>> findMax() {
        List<Pair<Integer, Integer>> listWithMaxValues = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        int x = size / 2;
        int y = size / 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (currentTable[i][j].isEmpty() && tableWithPotentialMoves[i][j] > 0 && tableWithPotentialMoves[i][j] > max) {
                    max = tableWithPotentialMoves[i][j];
                    listWithMaxValues.clear();

                }
                if (max == tableWithPotentialMoves[i][j]) {
                    listWithMaxValues.add(new Pair<>(i, j));
                }
            }
        }
        if (listWithMaxValues.size() == 0) listWithMaxValues.add(new Pair<>(x, y));

        return listWithMaxValues;
    }


    public void moveMe(int x, int y) {
        if (currentTable[x][y].isEmpty()) {
            currentTable[x][y] = aiSymbol.toString();
            makePotentialMovesTable(x, y);
            printCurrentTable();
            printTableOfPotentials();
        }

    }

    public void moveEnemy(int x, int y) {
        if (currentTable[x][y].isEmpty()) {
            currentTable[x][y] = humanSymbol.toString();
            deletePotentialOfAiMoves(x,y);
        }
    }

    public boolean checkWin() {
        int tab[] = new int[8];
        int tab2[] = new int[8];
        Arrays.fill(tab, 0);
        Arrays.fill(tab2, 0);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Arrays.fill(tab, 0);
                Arrays.fill(tab2, 0);
                for (int k = 0; k < 5; k++) {
                    if (k > 0 && x + k < size && y + k < size && (!currentTable[x + k][y + k].isEmpty())) {
                        if (currentTable[x + k][y + k].equals(aiSymbol.toString())) tab[0]++;
                        if (currentTable[x + k][y + k].equals(humanSymbol.toString())) tab2[0]++;

                    }
                    if (k > 0 && x + k < size && y - k >= 0 && (!currentTable[x + k][y - k].isEmpty())) {
                        if (currentTable[x + k][y - k].equals(aiSymbol.toString())) tab[1]++;
                        if (currentTable[x + k][y - k].equals(humanSymbol.toString())) tab2[1]++;

                    }
                    if (k > 0 && x + k < size && (!currentTable[x + k][y].isEmpty())) {
                        if (currentTable[x + k][y].equals(aiSymbol.toString())) tab[2]++;
                        if (currentTable[x + k][y].equals(humanSymbol.toString())) tab2[2]++;

                    }
                    if (k > 0 && x - k >= 0 && y - k >= 0 && (!currentTable[x - k][y - k].isEmpty())) {
                        if (currentTable[x - k][y - k].equals(aiSymbol.toString())) tab[3]++;
                        if (currentTable[x - k][y - k].equals(humanSymbol.toString())) tab2[3]++;

                    }
                    if (k > 0 && x - k >= 0 && (!currentTable[x - k][y].isEmpty())) {
                        if (currentTable[x - k][y].equals(humanSymbol.toString())) tab2[4]++;

                        if (currentTable[x - k][y].equals(aiSymbol.toString())) tab[4]++;
                    }
                    if (k > 0 && y - k >= 0 && (!currentTable[x][y - k].isEmpty())) {
                        if (currentTable[x][y - k].equals(aiSymbol.toString())) tab[5]++;
                        if (currentTable[x][y - k].equals(humanSymbol.toString())) tab2[5]++;

                    }
                    if (k > 0 && y + k < size && (!currentTable[x][y + k].isEmpty())) {

                        if (currentTable[x][y + k].equals(aiSymbol.toString())) tab[6]++;
                        if (currentTable[x][y + k].equals(humanSymbol.toString())) tab2[6]++;

                    }
                    if (x - k >= 0 && y + k < size && (!currentTable[x - k][y + k].isEmpty())) {
                        if (currentTable[x - k][y + k].equals(aiSymbol.toString())) tab[7]++;
                        if (currentTable[x - k][y + k].equals(humanSymbol.toString())) tab2[7]++;

                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (tab[i] == 4 || tab2[i] == 4) return true;
                }
            }
        }
        return false;
    }
}
