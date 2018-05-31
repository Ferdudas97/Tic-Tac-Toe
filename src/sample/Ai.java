package sample;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Ai {
    private String[][] currentTable;
    private Integer[][] tableWithPotentialMoves;
    private Character humanSymbol;
    private Character aiSymbol;
    private int size = 25;
    private Pair<Integer,Integer> previousMove;

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
      //  max = tableWithPotentialMoves[x][y];
        int [] aiSymbols=howManySymbols(aiSymbol,x,y);

        for (int i = 1; i < 5; i++) {
            if (x + i < size && y + i < size && currentTable[x + i][y + i].isEmpty()) {
                tableWithPotentialMoves[x + i][y + i]+=aiSymbols[3]+5;
                aiSymbols[3]--;
                if (tableWithPotentialMoves[x + i][y + i] > max) max = tableWithPotentialMoves[x + i][y + i];
            }
            if (x + i < size && y - i >= 0 && currentTable[x + i][y - i].isEmpty()) {
                tableWithPotentialMoves[x + i][y - i]+=aiSymbols[7]+5;
                aiSymbols[7]--;
                if (tableWithPotentialMoves[x + i][y - i] > max) max = tableWithPotentialMoves[x + i][y - i];

            }
            if (x + i < size && currentTable[x + i][y].isEmpty()) {
                tableWithPotentialMoves[x + i][y]+=aiSymbols[4]+5;
                aiSymbols[4]--;
                if (tableWithPotentialMoves[x + i][y] > max) max = tableWithPotentialMoves[x + i][y];

            }
            if (x - i >= 0 && y - i >= 0 && currentTable[x - i][y - i].isEmpty()) {
                tableWithPotentialMoves[x - i][y - i]+=aiSymbols[0]+5;
                aiSymbols[0]--;
                if (tableWithPotentialMoves[x - i][y - i] > max) max = tableWithPotentialMoves[x - i][y - i];

            }
            if (x - i >= 0 && currentTable[x - i][y].isEmpty()) {
                tableWithPotentialMoves[x - i][y]+=aiSymbols[2]+5;
                aiSymbols[2]--;
                if (tableWithPotentialMoves[x - i][y] > max) max = tableWithPotentialMoves[x - i][y];

            }
            if (y - i >= 0 && currentTable[x][y - i].isEmpty()) {
                tableWithPotentialMoves[x][y - i]+=aiSymbols[6]+5;
                aiSymbols[6]--;
                if (tableWithPotentialMoves[x][y - i] > max) max = tableWithPotentialMoves[x][y - i];

            }
            if (y + i < size && currentTable[x][y + i].isEmpty()) {
                tableWithPotentialMoves[x][y + i]+=aiSymbols[5]+5;
                aiSymbols[5]--;
                if (tableWithPotentialMoves[x][y + i] > max) max = tableWithPotentialMoves[x][y + i];
            }
            if (x - i >= 0 && y + i < size && currentTable[x - i][y + i].isEmpty()) {
                tableWithPotentialMoves[x - i][y + i]+=aiSymbols[1]+5;
                aiSymbols[1]--;
                if (tableWithPotentialMoves[x - i][y + i] > max) max = tableWithPotentialMoves[x - i][y + i];

            }
        }
        System.out.println(max);
        return max;

    }
    private int [] howManySymbols(Character symbol,int x, int y){
        int symbols[]=new int[8];
        boolean blocked[]=new boolean[8];
        Arrays.fill(blocked, false);
        Arrays.fill(symbols,1);
        String allySymbol= symbol.toString();
        String enemySymbol= allySymbol.equals(humanSymbol.toString()) ? aiSymbol.toString(): humanSymbol.toString();
        for (int k = 1; k < 5; k++) {
            if (  x + k < size && y + k < size && !blocked[0]) {
                if (currentTable[x+k][y+k].equals(enemySymbol)) blocked[0]=true;
                if (currentTable[x+k][y+k].equals(allySymbol)) symbols[0]++;


            }
            if (x + k < size && y - k >= 0 &&  !blocked[0]) {
                if (currentTable[x+k][y-k].equals(enemySymbol)) blocked[1]=true;
                if (currentTable[x+k][y-k].equals(allySymbol)) symbols[1]++;
            }
            if (x + k < size &&  !blocked[2]) {
                if (currentTable[x+k][y].equals(enemySymbol)) blocked[2]=true;
                if (currentTable[x+k][y].equals(allySymbol)) symbols[2]++;
            }
            if (x - k >= 0 && y - k >= 0 &&  !blocked[3] ) {
                if (currentTable[x-k][y-k].equals(enemySymbol)) blocked[3]=true;
                if (currentTable[x-k][y-k].equals(allySymbol)) symbols[3]++;

            }
            if (x - k >= 0 &&  !blocked[4] ) {
                if (currentTable[x-k][y].equals(enemySymbol)) blocked[4]=true;
                if (currentTable[x-k][y].equals(allySymbol)) symbols[4]++;
            }
            if (y - k >= 0 &&  !blocked[5] ) {
                if (currentTable[x][y-k].equals(enemySymbol)) blocked[5]=true;
                if (currentTable[x][y-k].equals(allySymbol)) symbols[5]++;

            }
            if (y + k < size &&  !blocked[6]) {
                if (currentTable[x][y+k].equals(enemySymbol)) blocked[6]=true;
                if (currentTable[x][y+k].equals(allySymbol)) symbols[6]++;
            }
            if (x - k >= 0 && y + k < size && !blocked[7]) {
                if (currentTable[x-k][y+k].equals(enemySymbol)) blocked[7]=true;
                if (currentTable[x-k][y+k].equals(allySymbol)) symbols[7]++;
            }
        }
        for (int i = 0; i <symbols.length ; i++) {
            symbols[i]*=2;
        }
    return symbols;
    }

    public void deletePotentialOfAiMoves(int x, int y) {
        int [] aiSymbols=howManySymbols(aiSymbol,x,y);
        tableWithPotentialMoves[x][y]--;

        for (int i = 1; i < 5; i++) {
            if (x + i < size && y + i < size && currentTable[x + i][y + i].isEmpty()) {
                tableWithPotentialMoves[x + i][y + i]-=aiSymbols[3]+5;
                aiSymbols[3]--;
            }
            if (x + i < size && y - i >= 0 && currentTable[x + i][y - i].isEmpty()) {
                tableWithPotentialMoves[x + i][y - i]-=aiSymbols[7]+5;
                aiSymbols[7]--;

            }
            if (x + i < size && currentTable[x + i][y].isEmpty()) {
                tableWithPotentialMoves[x + i][y]-=aiSymbols[4]+5;
                aiSymbols[4]--;

            }
            if (x - i >= 0 && y - i >= 0 && currentTable[x - i][y - i].isEmpty()) {
                tableWithPotentialMoves[x - i][y - i]-=aiSymbols[0]+5;
                aiSymbols[0]--;

            }
            if (x - i >= 0 && currentTable[x - i][y].isEmpty()) {
                tableWithPotentialMoves[x - i][y]-=aiSymbols[2]+5;
                aiSymbols[2]--;

            }
            if (y - i >= 0 && currentTable[x][y - i].isEmpty()) {
                tableWithPotentialMoves[x][y - i]-=aiSymbols[6]+5;
                aiSymbols[6]--;

            }
            if (y + i < size && currentTable[x][y + i].isEmpty()) {
                tableWithPotentialMoves[x][y + i]-=aiSymbols[5]+5;
                aiSymbols[5]--;
            }
            if (x - i >= 0 && y + i < size && currentTable[x - i][y + i].isEmpty()) {
                tableWithPotentialMoves[x - i][y + i]-=aiSymbols[1]+5;
                aiSymbols[1]--;

            }
            /*if (x + i < size && y + i < size && currentTable[x + i][y + i].isEmpty()) {
                tableWithPotentialMoves[x + i][y + i]-=aiSymbols[3];
                aiSymbols[3]--;
            }
            if (x + i < size && y - i >= 0 && currentTable[x + i][y - i].isEmpty()) {
                tableWithPotentialMoves[x + i][y - i]-=aiSymbols[7];
                aiSymbols[7]--;

            }
            if (x + i < size && currentTable[x + i][y].isEmpty()) {
                tableWithPotentialMoves[x + i][y]-=aiSymbols[4];
                aiSymbols[4]--;

            }
            if (x - i >= 0 && y - i >= 0 && currentTable[x - i][y - i].isEmpty()) {
                tableWithPotentialMoves[x - i][y - i]-=aiSymbols[0];
                aiSymbols[0]--;

            }
            if (x - i >= 0 && currentTable[x - i][y].isEmpty()) {
                tableWithPotentialMoves[x - i][y]-=aiSymbols[2];
                aiSymbols[2]--;

            }
            if (y - i >= 0 && currentTable[x][y - i].isEmpty()) {
                tableWithPotentialMoves[x][y - i]-=aiSymbols[6];
                aiSymbols[6]--;

            }
            if (y + i < size && currentTable[x][y + i].isEmpty()) {
                tableWithPotentialMoves[x][y + i]-=aiSymbols[5];
                aiSymbols[5]--;
            }
            if (x - i >= 0 && y + i < size && currentTable[x - i][y + i].isEmpty()) {
                tableWithPotentialMoves[x - i][y + i]-=aiSymbols[1];
                aiSymbols[1]--;

            }*/

        }
    }


    public void printCurrentTable() {
        System.out.println("Plansza");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (currentTable[i][j].isEmpty())System.out.print('_');
                System.out.print(currentTable[i][j] + "|");

            }
            System.out.print("\n");

        }
    }

    public void printTableOfPotentials() {
        System.out.println("Tablica potencjałow");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(tableWithPotentialMoves[i][j] );
                if (tableWithPotentialMoves[i][j]<10) System.out.print(" ");
                System.out.print("|");
            }
            System.out.print("\n");

        }
    }

    public Pair<Integer,Integer> moveToMax() {
        List<Pair<Integer, Integer>> listWithMaxPairs = findMax();
        int max = Integer.MIN_VALUE;
        Pair<Integer, Integer> maxPair = new Pair<>(0, 0);
        int currValue = 0;
        List<Pair<Integer, Integer>> list=new ArrayList<>();

        for (Pair<Integer, Integer> pair : listWithMaxPairs) {
            currValue = makePotentialMovesTable(pair.getKey(), pair.getValue());
            System.out.println("max = " + currValue);
            list=findMax();
            /*for (Pair<Integer,Integer> pair2: listWithMaxPairs) {
                int curr2=makePotentialMovesTable(pair2.getKey(),pair2.getValue());
                if (currValue +curr2 > max && currentTable[pair.getKey()][pair.getValue()].isEmpty()) {

                    max = currValue+curr2;

                    maxPair = pair;
                    list.clear();
                }
                deletePotentialOfAiMoves(pair2.getKey(), pair2.getValue());
            }*/
            if (currValue > max && currentTable[pair.getKey()][pair.getValue()].isEmpty()) {

                max = currValue;

                maxPair = pair;
                list.clear();
            }

            if (currValue==max) list.add(pair);
            deletePotentialOfAiMoves(pair.getKey(), pair.getValue());


        }
        previousMove=maxPair;
        Random random= new Random();
//        maxPair=list.get(Math.abs(random.nextInt()%list.size()));
        moveMe(maxPair.getKey(), maxPair.getValue());
        return maxPair;
    }

    public List<Pair<Integer, Integer>> findMax() {
        List<Pair<Integer, Integer>> listWithMaxValues = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        int x = (size / 2);
        int y = (size / 2);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (currentTable[i][j].isEmpty() &&  tableWithPotentialMoves[i][j]>0 && tableWithPotentialMoves[i][j] > max) {
                    max = tableWithPotentialMoves[i][j];
                    listWithMaxValues.clear();

                }
                if (max == tableWithPotentialMoves[i][j]) {
                    listWithMaxValues.add(new Pair<>(i, j));
                }
            }
        }
    //    previousMove=new Pair<>(x,y);
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
           // deletePotentialOfAiMoves(x,y);
            makePotentialMovesTable(x,y);
            printCurrentTable();
            printTableOfPotentials();
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
