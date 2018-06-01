package sample;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.RecursiveAction;

public class Ai extends RecursiveAction {
    private int[][] tableBefore;
    private String[][] currentTable;

    private int[][] tableWithAiPotentialMoves;
    private int[][] tableWithHumanPotentialMoves;

    private Character humanSymbol;
    private Character aiSymbol;
    private int size = 25;
    private Pair<Integer, Integer> previousMove;

    public Ai() {
        currentTable = new String[size][size];
        tableWithAiPotentialMoves = new int[size][size];
        tableWithHumanPotentialMoves = new int[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(currentTable[i], "");
            Arrays.fill(tableWithAiPotentialMoves[i],0);
            Arrays.fill(tableWithHumanPotentialMoves[i],0);
        }

        humanSymbol = 'x';
        aiSymbol = 'o';

    }

    @Override
    protected void compute() {
        moveToMax();
    }


    public int makePotentialMovesTable(Character symb, int x, int y) {
        int max = Integer.MIN_VALUE;
        int[] allySymbols = howManySymbols(symb, x, y);
        String enemySymbol = symb.equals(aiSymbol) ? humanSymbol.toString() : aiSymbol.toString();
        int[][] tableWithPotentialMoves=symb.equals(aiSymbol)? tableWithAiPotentialMoves:tableWithHumanPotentialMoves;
        boolean[] added = new boolean[8];
        Arrays.fill(added, false);

        for (int i = 1; i < 5; i++) {

            if (!added[0] && x + i < size && y + i < size && currentTable[x + i][y + i].isEmpty()) {
                if (currentTable[x + i][y + i].equals(enemySymbol)) added[0] = true;
                else {
                    if (tableWithPotentialMoves[x + i][y + i] < allySymbols[0]) tableWithPotentialMoves[x + i][y + i] = allySymbols[0];
                    else if (tableWithPotentialMoves[x + i][y + i] == allySymbols[0]) tableWithPotentialMoves[x + i][y + i] ++;

                    added[0] = true;
                    if (tableWithPotentialMoves[x + i][y + i] > max) max = tableWithPotentialMoves[x + i][y + i];
                }
            }
            if (currentTable[x - i][y - i].equals(enemySymbol)) added[1] = true;

            if (!added[1] && x - i >= 0 && y - i >= 0 && currentTable[x - i][y - i].isEmpty()) {
                if (currentTable[x - i][y - i].equals(enemySymbol)) added[1] = true;
                else {
                    if (tableWithPotentialMoves[x - i][y - i] < allySymbols[1]) tableWithPotentialMoves[x - i][y - i] = allySymbols[1];
                    if (tableWithPotentialMoves[x - i][y - i] == allySymbols[1]) tableWithPotentialMoves[x - i][y - i] ++;

                    added[1] = true;

                    if (tableWithPotentialMoves[x - i][y - i] > max) max = tableWithPotentialMoves[x - i][y - i];
                }
            }

            if (!added[2] && x + i < size && y - i >= 0 && currentTable[x + i][y - i].isEmpty()) {
                if (!currentTable[x + i][y - i].equals(enemySymbol)) {
                    if (tableWithPotentialMoves[x + i][y - i] < allySymbols[2]) tableWithPotentialMoves[x + i][y - i] = allySymbols[2];
                    else if (tableWithPotentialMoves[x + i][y - i] == allySymbols[2]) tableWithPotentialMoves[x + i][y - i] ++;


                    if (tableWithPotentialMoves[x + i][y - i] > max) max = tableWithPotentialMoves[x + i][y - i];
                }
                added[2] = true;
            }
            if (!added[3] && x - i >= 0 && y + i < size && currentTable[x - i][y + i].isEmpty()) {
                if (!currentTable[x - i][y - i].equals(enemySymbol)) {
                    if (tableWithPotentialMoves[x - i][y + i] < allySymbols[3]) tableWithPotentialMoves[x - i][y + i] = allySymbols[3];
                    else if (tableWithPotentialMoves[x - i][y + i] == allySymbols[3]) tableWithPotentialMoves[x - i][y + i] ++;

                    if (tableWithPotentialMoves[x - i][y + i] > max) max = tableWithPotentialMoves[x - i][y + i];
                }
                added[3] = true;

            }
            if (!added[4] && x + i < size && currentTable[x + i][y].isEmpty()) {
                if (!currentTable[x + i][y].equals(enemySymbol)) {
                    if (tableWithPotentialMoves[x + i][y] < allySymbols[4]) tableWithPotentialMoves[x + i][y] = allySymbols[4];
                    else if (tableWithPotentialMoves[x + i][y] == allySymbols[4]) tableWithPotentialMoves[x + i][y] ++;

                    if (tableWithPotentialMoves[x + i][y] > max) max = tableWithPotentialMoves[x + i][y];
                }
                added[4] = true;

            }

            if (!added[5] && x - i >= 0 && currentTable[x - i][y].isEmpty()) {
                if (!currentTable[x - i][y].equals(enemySymbol)) {
                    if (tableWithPotentialMoves[x - i][y] < allySymbols[5]) tableWithPotentialMoves[x - i][y] = allySymbols[5];
                    else if (tableWithPotentialMoves[x - i][y] == allySymbols[5]) tableWithPotentialMoves[x - i][y] ++;

                    if (tableWithPotentialMoves[x - i][y] > max) max = tableWithPotentialMoves[x - i][y];

                }
                added[5] = true;

            }
            if (!added[6] && y - i >= 0 && currentTable[x][y - i].isEmpty()) {
                if (!currentTable[x][y - i].equals(enemySymbol)) {
                    if (tableWithPotentialMoves[x][y - i] < allySymbols[6]) tableWithPotentialMoves[x][y - i] = allySymbols[6];
                     else if (tableWithPotentialMoves[x][y - i] == allySymbols[6]) tableWithPotentialMoves[x][y - i] ++;

                    if (tableWithPotentialMoves[x][y - i] > max) max = tableWithPotentialMoves[x][y - i];
                }
                added[6] = true;

            }
            if (!added[7] && y + i < size && currentTable[x][y + i].isEmpty()) {
                if (!currentTable[x][y + i].equals(enemySymbol)) {
                    if (tableWithPotentialMoves[x][y + i] < allySymbols[7]) tableWithPotentialMoves[x][y + i] = allySymbols[7];
                    else if (tableWithPotentialMoves[x][y + i] == allySymbols[7]) tableWithPotentialMoves[x][y + i] ++;

                    if (tableWithPotentialMoves[x][y + i] > max) max = tableWithPotentialMoves[x][y + i];
                }
                added[7] = true;

            }
            /*if (!added[0] && x + i < size && y + i < size && currentTable[x + i][y + i].isEmpty()) {
                if (tableWithPotentialMoves[x + i][y + i] < allySymbols[0] ) tableWithPotentialMoves[x + i][y + i] = allySymbols[0] ;
                added[0]=true;
                if (tableWithPotentialMoves[x + i][y + i] > max) max = tableWithPotentialMoves[x + i][y + i];
            }
            if (!added[1] &&x - i >= 0 && y - i >= 0 && currentTable[x - i][y - i].isEmpty()) {
                if (tableWithPotentialMoves[x - i][y - i] < allySymbols[1] )   tableWithPotentialMoves[x - i][y - i] = allySymbols[1] ;
                added[1]=true;

                if (tableWithPotentialMoves[x - i][y - i] > max) max = tableWithPotentialMoves[x - i][y - i];

            }

            if (!added[2] && x + i < size && y - i >= 0 && currentTable[x + i][y - i].isEmpty()) {
                if (tableWithPotentialMoves[x + i][y - i] < allySymbols[2] )  tableWithPotentialMoves[x + i][y - i] = allySymbols[2] ;
                added[2]=true;

                if (tableWithPotentialMoves[x + i][y - i] > max) max = tableWithPotentialMoves[x + i][y - i];

            }
            if (!added[3] && x - i >= 0 && y + i < size && currentTable[x - i][y + i].isEmpty()) {
                if (tableWithPotentialMoves[x - i][y + i] < allySymbols[3] )tableWithPotentialMoves[x - i][y + i] = allySymbols[3] ;
                added[3]=true;

                if (tableWithPotentialMoves[x - i][y + i] > max) max = tableWithPotentialMoves[x - i][y + i];

            }
            if (!added[4] && x + i < size && currentTable[x + i][y].isEmpty()) {
                if (tableWithPotentialMoves[x +i][y] < allySymbols[4] )tableWithPotentialMoves[x + i][y] = allySymbols[4] ;
                added[4]=true;

                if (tableWithPotentialMoves[x + i][y] > max) max = tableWithPotentialMoves[x + i][y];

            }

            if (!added[5] && x - i >= 0 && currentTable[x - i][y].isEmpty()) {
                if (tableWithPotentialMoves[x - i][y ] < allySymbols[5] )tableWithPotentialMoves[x - i][y] = allySymbols[5];
                added[5]=true;

                if (tableWithPotentialMoves[x - i][y] > max) max = tableWithPotentialMoves[x - i][y];

            }
            if (!added[6] && y - i >= 0 && currentTable[x][y - i].isEmpty()) {
                if (tableWithPotentialMoves[x][y - i] < allySymbols[6] )tableWithPotentialMoves[x][y - i] = allySymbols[6];
                added[6]=true;

                if (tableWithPotentialMoves[x][y - i] > max) max = tableWithPotentialMoves[x][y - i];

            }
            if (!added[7] && y + i < size && currentTable[x][y + i].isEmpty()) {
                if (tableWithPotentialMoves[x ][y + i] < allySymbols[7] ) tableWithPotentialMoves[x][y + i] = allySymbols[7] ;
                added[7]=true;

                if (tableWithPotentialMoves[x][y + i] > max) max = tableWithPotentialMoves[x][y + i];
            }*/


        }
        //  printCurrentTable();
        // printTableOfPotentials();
        return max;

    }

    public void deletePotentialOfMoves(Character symb, int x, int y) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

            }
        }
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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (currentTable[i][j].isEmpty()) System.out.print('_');
                System.out.print(currentTable[i][j] + "|");

            }
            System.out.print("\n");

        }
    }

    public void printTableOfPotentials() {
        System.out.println("Tablica potencjałow");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(tableWithHumanPotentialMoves[i][j]);
                if (tableWithHumanPotentialMoves[i][j] < 10) System.out.print(" ");
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
        Character someoneWon;
        List<Pair<Integer, Integer>> list = new ArrayList<>();
       // tableBefore = tableWithPotentialMoves.clone();
        ThreadLocal<String[][]> threadLocalTable = new ThreadLocal<>();
        threadLocalTable.set(currentTable);
        for (Pair<Integer, Integer> pair : listWithMaxPairs) {
            if (currentTable[pair.getKey()][pair.getValue()].isEmpty()) {

                currValue = Arrays.stream(howManySymbols(aiSymbol, pair.getKey(), pair.getValue())).max().getAsInt();
                curr2 = Arrays.stream(howManySymbols(humanSymbol, pair.getKey(), pair.getValue())).max().getAsInt();

                someoneWon = checkWin();

                if (curr2 >= currValue) currValue = curr2;
                currentTable[pair.getKey()][pair.getValue()] = aiSymbol.toString();
                currValue += moveToMax(0);
                currentTable[pair.getKey()][pair.getValue()] = "";

                if (someoneWon != null) {
                    if (someoneWon.equals(aiSymbol)) currValue = 9999;
                    else currValue = 8888;
                    System.out.println("wygrana !!! " + currValue);
                }
                if (currValue > max) {
                    max = currValue;
                    maxPair = pair;
                    list.clear();
                }

                if (currValue == max) list.add(pair);
                //deletePotentialOfMoves(aiSymbol,pair.getKey(), pair.getValue());
                // tableWithPotentialMoves=tableBefore;
            }
        }
        System.out.println("max ==" + list.size());

        Random random = new Random();
        maxPair = list.get(Math.abs(random.nextInt() % list.size()));
        moveMe(maxPair.getKey(), maxPair.getValue());
        return maxPair;
    }

    public int moveToMax(int level) {
        List<Pair<Integer, Integer>> listWithMaxPairs = findMax();
        int max = Integer.MIN_VALUE;
        Pair<Integer, Integer> maxPair = new Pair<>(0, 0);
        int currValue = 0;
        int curr2 = 0;
        List<Pair<Integer, Integer>> list = new ArrayList<>();

        for (Pair<Integer, Integer> pair : listWithMaxPairs) {
            if (currentTable[pair.getKey()][pair.getValue()].isEmpty()) {

                currValue = Arrays.stream(howManySymbols(aiSymbol, pair.getKey(), pair.getValue())).max().getAsInt();
                curr2 = Arrays.stream(howManySymbols(humanSymbol, pair.getKey(), pair.getValue())).max().getAsInt();
                if (curr2 > currValue) currValue = curr2;

                if (level < 2) {
                    currentTable[pair.getKey()][pair.getValue()] = level % 2 == 0 ? humanSymbol.toString() : aiSymbol.toString();
                    currValue += moveToMax(level + 1);
                    currentTable[pair.getKey()][pair.getValue()] = "";

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

    public List<Pair<Integer, Integer>> findMax() {


        List<Pair<Integer, Integer>> listWithMaxAiValues = new ArrayList<>();
        List<Pair<Integer, Integer>> listWithMaxHumanValues = new ArrayList<>();

        int maxAi = Integer.MIN_VALUE;
        int maxHuman = Integer.MIN_VALUE;

        int x = (size / 2);
        int y = (size / 2);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (currentTable[i][j].isEmpty() && tableWithAiPotentialMoves[i][j] > 0 && tableWithAiPotentialMoves[i][j] > maxAi) {
                    maxAi = tableWithAiPotentialMoves[i][j];
                    listWithMaxAiValues.clear();

                }
                if (maxAi == tableWithAiPotentialMoves[i][j]) {
                    listWithMaxAiValues.add(new Pair<>(i, j));
                }
                if (currentTable[i][j].isEmpty() && tableWithHumanPotentialMoves[i][j] > 0 && tableWithHumanPotentialMoves[i][j] > maxAi) {
                    maxAi = tableWithHumanPotentialMoves[i][j];
                    listWithMaxHumanValues.clear();

                }
                if (maxAi == tableWithHumanPotentialMoves[i][j]) {
                    listWithMaxHumanValues.add(new Pair<>(i, j));
                }
            }
        }
        //    previousMove=new Pair<>(x,y);
        //     if (listWithMaxValues.size() == 0) listWithMaxValues.add(new Pair<>(x, y));
        listWithMaxAiValues.addAll(listWithMaxHumanValues);
        return listWithMaxAiValues;
    }


    public void moveMe(int x, int y) {
        if (currentTable[x][y].isEmpty()) {
            currentTable[x][y] = aiSymbol.toString();
            makePotentialMovesTable(aiSymbol, x, y);
            tableWithAiPotentialMoves[x][y] = 0;
            // deletePotentialOfMoves(humanSymbol,x,y);
            printCurrentTable();
            printTableOfPotentials();
        }


    }

    public void moveEnemy(int x, int y) {
        if (currentTable[x][y].isEmpty()) {
            currentTable[x][y] = humanSymbol.toString();
            makePotentialMovesTable(humanSymbol, x, y);
            tableWithHumanPotentialMoves[x][y] = -1;

            // deletePotentialOfMoves(aiSymbol,x,y);

            printCurrentTable();
            printTableOfPotentials();
        }
    }

    public Character checkWin() {
        int tab[] = new int[8];
        int tab2[] = new int[8];
        Arrays.fill(tab, 1);
        Arrays.fill(tab2, 1);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Arrays.fill(tab, 1);
                Arrays.fill(tab2, 1);
                for (int k = 1; k < 5; k++) {
                    if (x + k < size && y + k < size && !currentTable[x + k][y + k].isEmpty()) {
                        if (currentTable[x + k][y + k].equals(aiSymbol.toString())) tab[0]++;
                        if (currentTable[x + k][y + k].equals(humanSymbol.toString())) tab2[0]++;

                    }
                    if (x + k < size && y - k >= 0 && !currentTable[x + k][y - k].isEmpty()) {
                        if (currentTable[x + k][y - k].equals(aiSymbol.toString())) tab[1]++;
                        if (currentTable[x + k][y - k].equals(humanSymbol.toString())) tab2[1]++;

                    }
                    if (x + k < size && !currentTable[x + k][y].isEmpty()) {
                        if (currentTable[x + k][y].equals(aiSymbol.toString())) tab[2]++;
                        if (currentTable[x + k][y].equals(humanSymbol.toString())) tab2[2]++;

                    }
                    if (x - k >= 0 && y - k >= 0 && !currentTable[x - k][y - k].isEmpty()) {
                        if (currentTable[x - k][y - k].equals(aiSymbol.toString())) tab[3]++;
                        if (currentTable[x - k][y - k].equals(humanSymbol.toString())) tab2[3]++;

                    }
                    if (x - k >= 0 && !currentTable[x - k][y].isEmpty()) {
                        if (currentTable[x - k][y].equals(humanSymbol.toString())) tab2[4]++;

                        if (currentTable[x - k][y].equals(aiSymbol.toString())) tab[4]++;
                    }
                    if (y - k >= 0 && !currentTable[x][y - k].isEmpty()) {
                        if (currentTable[x][y - k].equals(aiSymbol.toString())) tab[5]++;
                        if (currentTable[x][y - k].equals(humanSymbol.toString())) tab2[5]++;

                    }
                    if (y + k < size && !currentTable[x][y + k].isEmpty()) {

                        if (currentTable[x][y + k].equals(aiSymbol.toString())) tab[6]++;
                        if (currentTable[x][y + k].equals(humanSymbol.toString())) tab2[6]++;

                    }
                    if (x - k >= 0 && y + k < size && (!currentTable[x - k][y + k].isEmpty())) {
                        if (currentTable[x - k][y + k].equals(aiSymbol.toString())) tab[7]++;
                        if (currentTable[x - k][y + k].equals(humanSymbol.toString())) tab2[7]++;

                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (tab[i] == 5) return aiSymbol;
                    if (tab2[i] == 5) return humanSymbol;
                }
            }
        }
        return null;
    }

    public String[][] getCurrentTable() {
        return currentTable;
    }
}
