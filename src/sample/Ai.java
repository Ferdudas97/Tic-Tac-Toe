package sample;

import javafx.util.Pair;

import java.util.*;

public class Ai {
    private String[][] currentTable;
    private int[][] tableWithPotentialMoves;
    private Character humanSymbol;
    private Character aiSymbol;
    private int size = 25;
    private Pair<Integer, Integer> previousMove;

    public Ai() {
        currentTable = new String[size][size];
        tableWithPotentialMoves = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(currentTable[i], "");
            Arrays.fill(tableWithPotentialMoves[i], 0);

        }

        humanSymbol = 'x';
        aiSymbol = 'o';

    }


    public int makePotentialMovesTable(Character symb, int x, int y) {
        int max = Integer.MIN_VALUE;
        int[] allySymbols = howManySymbols(symb, x, y);
        String enemySymbol = symb.equals(aiSymbol) ? humanSymbol.toString() : aiSymbol.toString();
        boolean[] added = new boolean[8];
        Arrays.fill(added, false);

        for (int i = 1; i < 5; i++) {
            if (x + i < size && y + i < size && currentTable[x + i][y + i].equals(enemySymbol)) added[0] = true;
            if (x - i >= 0 && y - i >= 0 && currentTable[x - i][y - i].equals(enemySymbol)) added[1] = true;
            if (x + i < size && y - i >= 0 && currentTable[x + i][y - i].equals(enemySymbol)) added[2] = true;
            if (x - i >= 0 && y + i < size && currentTable[x - i][y + i].equals(enemySymbol)) added[3] = true;
            if (x + i < size && currentTable[x + i][y].equals(enemySymbol)) added[4] = true;
            if (x - i >= 0 && currentTable[x - i][y].equals(enemySymbol)) added[5] = true;
            if (y - i >= 0 && currentTable[x][y - i].equals(enemySymbol)) added[6] = true;
            if (y + i < size && currentTable[x][y + i].equals(enemySymbol)) added[7] = true;

            if (!added[0] && x + i < size && y + i < size && currentTable[x + i][y + i].isEmpty()) {
                int j = 1;
                while (x + i + j < size && y + i + j < size && currentTable[x + i + j][y + i + j].equals(symb.toString())) {
                    allySymbols[0]++;
                    j++;
                }
                if (tableWithPotentialMoves[x + i][y + i] < allySymbols[0])
                    tableWithPotentialMoves[x + i][y + i] = allySymbols[0];
                added[0] = true;
                if (tableWithPotentialMoves[x + i][y + i] > max) max = tableWithPotentialMoves[x + i][y + i];
            }
            if (!added[1] && x - i >= 0 && y - i >= 0 && currentTable[x - i][y - i].isEmpty()) {
                int j = 1;
                while (x - i - j >= 0 && y - i - j >= 0 && currentTable[x - i - j][y - i - j].equals(symb.toString())) {
                    allySymbols[1]++;
                    j++;
                }
                if (tableWithPotentialMoves[x - i][y - i] < allySymbols[1])
                    tableWithPotentialMoves[x - i][y - i] = allySymbols[1];
                added[1] = true;

                if (tableWithPotentialMoves[x - i][y - i] > max) max = tableWithPotentialMoves[x - i][y - i];

            }

            if (!added[2] && x + i < size && y - i >= 0 && currentTable[x + i][y - i].isEmpty()) {
                int j = 1;
                while (x + i + j < size && y - i - j >= 0 && currentTable[x + i + j][y - i - j].equals(symb.toString())) {
                    allySymbols[2]++;
                    j++;
                }

                if (tableWithPotentialMoves[x + i][y - i] < allySymbols[2])
                    tableWithPotentialMoves[x + i][y - i] = allySymbols[2];
                added[2] = true;

                if (tableWithPotentialMoves[x + i][y - i] > max) max = tableWithPotentialMoves[x + i][y - i];

            }
            if (!added[3] && x - i >= 0 && y + i < size && currentTable[x - i][y + i].isEmpty()) {
                int j = 1;
                while (x - i - j >= 0 && y + i + j < size && currentTable[x - i - j][y + i + j].equals(symb.toString())) {
                    allySymbols[3]++;
                    j++;
                }
                if (tableWithPotentialMoves[x - i][y + i] < allySymbols[3])
                    tableWithPotentialMoves[x - i][y + i] = allySymbols[3];
                added[3] = true;

                if (tableWithPotentialMoves[x - i][y + i] > max) max = tableWithPotentialMoves[x - i][y + i];

            }
            if (!added[4] && x + i < size && currentTable[x + i][y].isEmpty()) {
                int j = 1;
                while (x + i + j < size && currentTable[x + i + j][y].equals(symb.toString())) {
                    allySymbols[4]++;
                    j++;
                }
                if (tableWithPotentialMoves[x + i][y] < allySymbols[4])
                    tableWithPotentialMoves[x + i][y] = allySymbols[4];
                added[4] = true;

                if (tableWithPotentialMoves[x + i][y] > max) max = tableWithPotentialMoves[x + i][y];

            }

            if (!added[5] && x - i >= 0 && currentTable[x - i][y].isEmpty()) {
                int j = 1;
                while (x - i - j >= 0 && currentTable[x - i - j][y].equals(symb.toString())) {
                    allySymbols[5]++;
                    j++;
                }

                if (tableWithPotentialMoves[x - i][y] < allySymbols[5])
                    tableWithPotentialMoves[x - i][y] = allySymbols[5];
                added[5] = true;

                if (tableWithPotentialMoves[x - i][y] > max) max = tableWithPotentialMoves[x - i][y];

            }
            if (!added[6] && y - i >= 0 && currentTable[x][y - i].isEmpty()) {
                int j = 1;
                while (y - i - j >= 0 && currentTable[x][y - i - j].equals(symb.toString())) {
                    allySymbols[6]++;
                    j++;
                }
                if (tableWithPotentialMoves[x][y - i] < allySymbols[6])
                    tableWithPotentialMoves[x][y - i] = allySymbols[6];
                added[6] = true;

                if (tableWithPotentialMoves[x][y - i] > max) max = tableWithPotentialMoves[x][y - i];

            }
            if (!added[7] && y + i < size && currentTable[x][y + i].isEmpty()) {
                int j = 1;
                while (y + j + i < size && currentTable[x][y + j + i].equals(symb.toString())) {
                    allySymbols[7]++;
                    j++;

                }

                if (tableWithPotentialMoves[x][y + i] < allySymbols[7])
                    tableWithPotentialMoves[x][y + i] = allySymbols[7];
                added[7] = true;

                if (tableWithPotentialMoves[x][y + i] > max) max = tableWithPotentialMoves[x][y + i];
            }

        }
        //  printCurrentTable();
        // printTableOfPotentials();
        return max;

    }


    private int[] howManySymbols(Character symbol, int x, int y) {
        int symbols[] = new int[8];
        boolean blocked[] = new boolean[8];
        Arrays.fill(blocked, false);
        Arrays.fill(symbols, 1);
        String allySymbol = symbol.toString();
        String enemySymbol = allySymbol.equals(humanSymbol.toString()) ? aiSymbol.toString() : humanSymbol.toString();
        for (int k = 1; k < 5; k++) {
            if (x + k < size && y + k < size && !blocked[0]) {
                if (currentTable[x + k][y + k].equals(allySymbol)) symbols[0]++;
                else blocked[0] = true;


            }
            if (x - k >= 0 && y - k >= 0 && !blocked[1]) {
                if (currentTable[x - k][y - k].equals(allySymbol)) symbols[1]++;
                else blocked[1] = true;

            }
            if (x + k < size && y - k >= 0 && !blocked[2]) {
                if (currentTable[x + k][y - k].equals(allySymbol)) symbols[2]++;
                else blocked[2] = true;
            }
            if (x - k >= 0 && y + k < size && !blocked[3]) {
                if (currentTable[x - k][y + k].equals(allySymbol)) symbols[3]++;
                else blocked[3] = true;
            }
            if (x + k < size && !blocked[4]) {
                if (currentTable[x + k][y].equals(allySymbol)) symbols[4]++;
                else blocked[4] = true;
            }
            if (x - k >= 0 && !blocked[5]) {
                if (currentTable[x - k][y].equals(allySymbol)) symbols[5]++;
                else blocked[5] = true;
            }


            if (y - k >= 0 && !blocked[6]) {
                if (currentTable[x][y - k].equals(allySymbol)) symbols[6]++;
                else blocked[6] = true;
            }
            if (y + k < size && !blocked[7]) {
                if (currentTable[x][y + k].equals(allySymbol)) symbols[7]++;
                else blocked[7] = true;
            }


        }
        for (int i = 0; i < symbols.length; i += 2) {
            if (symbols[i] == 3 && symbols[i + 1] == 3 && allySymbol.equals(humanSymbol.toString())) {
                symbols[i] = 5;
                symbols[i + 1] = 5;
            } else {
                symbols[i] += symbols[i + 1];

                symbols[i + 1] = symbols[i];

            }

        }

        for (int i = 0; i < symbols.length; i++) {
            //   symbols[i] = (int) Math.pow(symbols[i],);

        }


        return symbols;
    }


    public void printCurrentTable() {
        System.out.print("  |");
        for (int i = 0; i < size; i++) {
            System.out.print(i);
            if (i < 10) System.out.print(" ");
            System.out.print('|');

        }
        System.out.print("\n");
        for (int i = 0; i < size; i++) {

            System.out.print(i);
            if (i < 10) System.out.print(" ");
            System.out.print("|");
            for (int j = 0; j < size; j++) {
                if (currentTable[i][j].isEmpty()) System.out.print('_');
                System.out.print(currentTable[i][j] + "|");

            }
            System.out.print("\n");

        }
    }

    public void printTableOfPotentials() {
        System.out.println("Tablica potencjałow");
        System.out.print("  |");
        for (int i = 0; i < size; i++) {
            System.out.print(i);
            if (i < 10) System.out.print(" ");
            System.out.print('|');

        }
        System.out.print("\n");
        for (int i = 0; i < size; i++) {
            System.out.print(i);
            if (i < 10) System.out.print(" ");
            System.out.print("|");

            for (int j = 0; j < size; j++) {
                System.out.print(tableWithPotentialMoves[i][j]);
                if (tableWithPotentialMoves[i][j] < 10 && tableWithPotentialMoves[i][j] >= 0) System.out.print(" ");
                System.out.print("|");
            }
            System.out.print("\n");

        }
    }

    public Pair<Integer, Integer> moveToMax() {
        List<Pair<Integer, Integer>> listWithMaxPairs = findMax();
        int max = Integer.MIN_VALUE;
        Pair<Integer, Integer> maxPair = new Pair<>(0, 0);
        int currValue = 0;
        int curr2 = 0;
        int[][] tableBefore;
        Winner someoneWon;
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        tableBefore = copyTable(tableWithPotentialMoves);
        for (Pair<Integer, Integer> pair : listWithMaxPairs) {

            currValue = Arrays.stream(howManySymbols(aiSymbol, pair.getKey(), pair.getValue())).max().getAsInt();
            curr2 = Arrays.stream(howManySymbols(humanSymbol, pair.getKey(), pair.getValue())).max().getAsInt();

            someoneWon = checkWin();

            if (curr2 > currValue) currValue = curr2;
            currentTable[pair.getKey()][pair.getValue()] = aiSymbol.toString();
            makePotentialMovesTable(aiSymbol, pair.getKey(), pair.getValue());
            currValue -= moveToMax(0);
            tableWithPotentialMoves = copyTable(tableBefore);
            currentTable[pair.getKey()][pair.getValue()] = "";

            if (someoneWon != null) {
                if (someoneWon.getSymbol().equals(aiSymbol)) currValue = 9999;
                else currValue = 8888;
                // pair=new Pair<>(someoneWon.x,someoneWon.y);
                System.out.println("wygrana !!! " + someoneWon.getX() + " " + someoneWon.getY());
            }
            if (currValue > max) {
                System.out.println("max ==" + currValue);
                max = currValue;
                list.clear();
            }

            if (currValue == max) list.add(pair);
            if (currValue == 9999) break;
            //deletePotentialOfMoves(aiSymbol,pair.getKey(), pair.getValue());
            // tableWithPotentialMoves=tableBefore;

        }
        Random random = new Random();
        maxPair = list.get(Math.abs(random.nextInt() % list.size()));
        moveMe(maxPair.getKey(), maxPair.getValue());
        return maxPair;
    }

    private int[][] copyTable(int [][] arr) {
        int copy[][] = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(arr[i], 0, copy[i], 0, size);
        }
        return copy;
    }

    public int moveToMax(int level) {
        if (level < 3) {

            List<Pair<Integer, Integer>> listWithMaxPairs = findMax();
            int max = level % 2 == 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            Pair<Integer, Integer> maxPair = new Pair<>(0, 0);
            int currValue = 0;
            int curr2 = 0;
            int[][] tableBefore = copyTable(tableWithPotentialMoves);
            Winner someoneWon;
            List<Pair<Integer, Integer>> list = new ArrayList<>();

            for (Pair<Integer, Integer> pair : listWithMaxPairs) {


                if (level % 2 == 0) {
                    currValue = Arrays.stream(howManySymbols(aiSymbol, pair.getKey(), pair.getValue())).max().getAsInt();
                    curr2 = Arrays.stream(howManySymbols(humanSymbol, pair.getKey(), pair.getValue())).max().getAsInt();

                    if (curr2 > currValue) currValue = curr2;
                    currentTable[pair.getKey()][pair.getValue()] = level % 2 == 0 ? humanSymbol.toString() : aiSymbol.toString();
                    makePotentialMovesTable(humanSymbol, pair.getKey(), pair.getValue());
                    currValue -= moveToMax(level + 1);
                    someoneWon = checkWin();
                    tableWithPotentialMoves = copyTable(tableBefore);
                    currentTable[pair.getKey()][pair.getValue()] = "";

                    if (someoneWon != null) {
                        if (someoneWon.getSymbol().equals(aiSymbol)) currValue = -8888;
                        else currValue = 9999;
                        //pair=new Pair<>(someoneWon.x,someoneWon.y);
                        System.out.println("wygrana !!! " + someoneWon.getX() + " " + someoneWon.getY());
                    }

                    if (currValue > max && currentTable[pair.getKey()][pair.getValue()].isEmpty()) {

                        max = currValue;

                        maxPair = pair;
                        list.clear();
                    }


                    if (currValue == max) list.add(pair);
                } else {
                    currValue = Arrays.stream(howManySymbols(aiSymbol, pair.getKey(), pair.getValue())).max().getAsInt();
                    curr2 = Arrays.stream(howManySymbols(humanSymbol, pair.getKey(), pair.getValue())).max().getAsInt();
                    if (curr2 > currValue) currValue = curr2;
                    currValue = 0;
                    currentTable[pair.getKey()][pair.getValue()] = level % 2 == 0 ? humanSymbol.toString() : aiSymbol.toString();
                    makePotentialMovesTable(humanSymbol, pair.getKey(), pair.getValue());
                    currValue -= moveToMax(level + 1);
                    someoneWon = checkWin();
                    tableWithPotentialMoves = copyTable(tableBefore);

                    currentTable[pair.getKey()][pair.getValue()] = "";
                    if (someoneWon != null) {
                        if (someoneWon.getSymbol().equals(aiSymbol)) currValue = 9999;
                        else currValue = 8888;
                        //  pair=new Pair<>(someoneWon.x,someoneWon.y);
                        max = currValue;
                        System.out.println("wygrana !!! " + someoneWon.getX() + " " + someoneWon.getY());
                    }
                    if (currValue > max && currentTable[pair.getKey()][pair.getValue()].isEmpty()) {

                        max = currValue;

                        maxPair = pair;
                        list.clear();
                    }

                    if (currValue == max) list.add(pair);

                }


            }
            return max;

        }
        return 0;
    }

    public List<Pair<Integer, Integer>> findMax() {
        List<Pair<Integer, Integer>> listWithMaxValues = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        int x = (size / 2);
        int y = (size / 2);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (currentTable[i][j].isEmpty() && tableWithPotentialMoves[i][j] > 0 && tableWithPotentialMoves[i][j] > max) {
                    max = tableWithPotentialMoves[i][j];
                    listWithMaxValues.clear();

                }
                if (max == tableWithPotentialMoves[i][j] && currentTable[i][j].isEmpty()) {
                    listWithMaxValues.add(new Pair<>(i, j));
                }
            }
        }
        //    previousMove=new Pair<>(x,y);
        if (listWithMaxValues.size() == 0 && currentTable[x][y].isEmpty()) listWithMaxValues.add(new Pair<>(x, y));

        return listWithMaxValues;
    }


    public void moveMe(int x, int y) {
        if (currentTable[x][y].isEmpty()) {
            currentTable[x][y] = aiSymbol.toString();
            makePotentialMovesTable(aiSymbol, x, y);
            tableWithPotentialMoves[x][y] = 0;
            // deletePotentialOfMoves(humanSymbol,x,y);
            printCurrentTable();
            printTableOfPotentials();
        }


    }

    public void moveEnemy(int x, int y) {
        if (currentTable[x][y].isEmpty()) {
            currentTable[x][y] = humanSymbol.toString();
            makePotentialMovesTable(humanSymbol, x, y);
            tableWithPotentialMoves[x][y] = -1;

            // deletePotentialOfMoves(aiSymbol,x,y);

            printCurrentTable();
            printTableOfPotentials();
        }
    }

    public Winner checkWin() {
        int tab[] = new int[8];
        int tab2[] = new int[8];
        boolean[] aiBlocked = new boolean[8];
        boolean[] humanBlocked = new boolean[8];

        Arrays.fill(tab, 0);
        Arrays.fill(tab2, 0);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Arrays.fill(tab, 0);
                Arrays.fill(tab2, 0);
                Arrays.fill(aiBlocked, false);
                Arrays.fill(humanBlocked, false);

                for (int k = 1; k < 6; k++) {
                    if (x + k < size && y + k < size) {
                        if (!aiBlocked[0] && currentTable[x + k][y + k].equals(aiSymbol.toString())) tab[0]++;
                        else aiBlocked[0] = true;
                        if (!humanBlocked[0] && currentTable[x + k][y + k].equals(humanSymbol.toString())) tab2[0]++;
                        else humanBlocked[0] = true;
                    }
                    if (x - k >= 0 && y - k >= 0) {
                        if (!aiBlocked[1] && currentTable[x - k][y - k].equals(aiSymbol.toString())) tab[1]++;
                        else aiBlocked[1] = true;

                        if (!humanBlocked[1] && currentTable[x - k][y - k].equals(humanSymbol.toString())) tab2[1]++;
                        else humanBlocked[1] = true;


                    }
                    if (x + k < size && y - k >= 0) {
                        if (!aiBlocked[2] && currentTable[x + k][y - k].equals(aiSymbol.toString())) tab[2]++;
                        else aiBlocked[2] = true;
                        if (!humanBlocked[2] && currentTable[x + k][y - k].equals(humanSymbol.toString())) tab2[2]++;
                        else humanBlocked[2] = true;

                    }
                    if (x - k >= 0 && y + k < size) {
                        if (!aiBlocked[3] && currentTable[x - k][y + k].equals(aiSymbol.toString())) tab[3]++;
                        else aiBlocked[3] = true;
                        if (!humanBlocked[3] && currentTable[x - k][y + k].equals(humanSymbol.toString())) tab2[3]++;
                        else humanBlocked[3] = true;

                    }
                    if (x + k < size) {
                        if (!aiBlocked[4] && currentTable[x + k][y].equals(aiSymbol.toString())) tab[4]++;
                        else aiBlocked[4] = true;
                        if (!humanBlocked[4] && currentTable[x + k][y].equals(humanSymbol.toString())) tab2[4]++;
                        else humanBlocked[4] = true;

                    }


                    if (x - k >= 0) {
                        if (!humanBlocked[5] && currentTable[x - k][y].equals(humanSymbol.toString())) tab2[5]++;
                        else humanBlocked[5] = true;

                        if (!aiBlocked[5] && currentTable[x - k][y].equals(aiSymbol.toString())) tab[5]++;
                        else aiBlocked[5] = true;

                    }
                    if (y - k >= 0) {
                        if (!aiBlocked[6] && currentTable[x][y - k].equals(aiSymbol.toString())) tab[6]++;
                        else aiBlocked[6] = true;
                        if (!humanBlocked[6] && currentTable[x][y - k].equals(humanSymbol.toString())) tab2[6]++;
                        else humanBlocked[6] = true;

                    }
                    if (y + k < size) {

                        if (!aiBlocked[7] && currentTable[x][y + k].equals(aiSymbol.toString())) tab[7]++;
                        else aiBlocked[7] = true;
                        if (!humanBlocked[7] && currentTable[x][y + k].equals(humanSymbol.toString())) tab2[7]++;
                        else humanBlocked[7] = true;

                    }





                    /*for (int i = 0; i < 8; i+=2) {
                        //if (tab[i] +tab[i+1] >= 4) return new Winner(aiSymbol,x,y);
                        //if (tab2[i]+tab2[i+1] >= 4) return new Winner(humanSymbol,x,y);
                    }*/
                }
                for (int i = 0; i < 8; i++) {

                    if (tab[i] == 5) return new Winner(aiSymbol, x, y);
                    if (tab2[i] == 5) return new Winner(humanSymbol, x, y);
                    if (!aiBlocked[i]) tab[i]++;
                    if (!humanBlocked[i]) tab2[i]++;

                }
            }

        }
        return null;
    }

    public String[][] getCurrentTable() {
        return currentTable;
    }

}
