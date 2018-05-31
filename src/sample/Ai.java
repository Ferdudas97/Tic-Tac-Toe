package sample;

import javafx.util.Pair;

import java.util.*;

public class Ai {
    private String[][] currentTable;
    private Integer[][] tableWithPotentialMoves;
    private Character humanSymbol;
    private Character aiSymbol;
    private int size = 3;
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


    public int makePotentialMovesTable(Character symb,int x, int y) {
        int max = Integer.MIN_VALUE;
        tableWithPotentialMoves[x][y]++;
      //  max = tableWithPotentialMoves[x][y];
        int [] allySymbols=howManySymbols(symb,x,y);

        for (int i = 1; i < 5; i++) {
            if (x + i < size && y + i < size && currentTable[x + i][y + i].isEmpty()) {
                tableWithPotentialMoves[x + i][y + i]=allySymbols[0]+5;
                allySymbols[0]/=4;
                if (tableWithPotentialMoves[x + i][y + i] > max) max = tableWithPotentialMoves[x + i][y + i];
            }
            if (x - i >= 0 && y - i >= 0 && currentTable[x - i][y - i].isEmpty()) {
                tableWithPotentialMoves[x - i][y - i]=allySymbols[1]+5;
                allySymbols[1]/=4;
                if (tableWithPotentialMoves[x - i][y - i] > max) max = tableWithPotentialMoves[x - i][y - i];

            }

            if (x + i < size && y - i >= 0 && currentTable[x + i][y - i].isEmpty()) {
                tableWithPotentialMoves[x + i][y - i]=allySymbols[2]+5;
                allySymbols[2]/=4;
                if (tableWithPotentialMoves[x + i][y - i] > max) max = tableWithPotentialMoves[x + i][y - i];

            }
            if (x - i >= 0 && y + i < size && currentTable[x - i][y + i].isEmpty()) {
                tableWithPotentialMoves[x - i][y + i]=allySymbols[3]+5;
                allySymbols[3]/=4;
                if (tableWithPotentialMoves[x - i][y + i] > max) max = tableWithPotentialMoves[x - i][y + i];

            }
            if (x + i < size && currentTable[x + i][y].isEmpty()) {
                tableWithPotentialMoves[x + i][y]=allySymbols[4]+5;
                allySymbols[4]/=4;
                if (tableWithPotentialMoves[x + i][y] > max) max = tableWithPotentialMoves[x + i][y];

            }

            if (x - i >= 0 && currentTable[x - i][y].isEmpty()) {
                tableWithPotentialMoves[x - i][y]=allySymbols[5]+5;
                allySymbols[5]/=4;
                if (tableWithPotentialMoves[x - i][y] > max) max = tableWithPotentialMoves[x - i][y];

            }
            if (y - i >= 0 && currentTable[x][y - i].isEmpty()) {
                tableWithPotentialMoves[x][y - i]=allySymbols[6]+5;
                allySymbols[6]/=4;
                if (tableWithPotentialMoves[x][y - i] > max) max = tableWithPotentialMoves[x][y - i];

            }
            if (y + i < size && currentTable[x][y + i].isEmpty()) {
                tableWithPotentialMoves[x][y + i]=allySymbols[7]+5;
                allySymbols[7]/=4;
                if (tableWithPotentialMoves[x][y + i] > max) max = tableWithPotentialMoves[x][y + i];
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
                if (currentTable[x+k][y+k].equals(allySymbol)) symbols[0]++;
                else blocked[0]=true;


            }
            if (x - k >= 0 && y - k >= 0 &&  !blocked[1] ) {
                if (currentTable[x-k][y-k].equals(allySymbol)) symbols[1]++;
                else blocked[1]=true;

            }
            if (x + k < size && y - k >= 0 &&  !blocked[2]) {
                if (currentTable[x+k][y-k].equals(allySymbol)) symbols[2]++;
                else blocked[2]=true;
            }
            if (x - k >= 0 && y + k < size && !blocked[3]) {
                if (currentTable[x-k][y+k].equals(allySymbol)) symbols[3]++;
                else blocked[3]=true;
            }
            if (x + k < size &&  !blocked[4]) {
                if (currentTable[x+k][y].equals(allySymbol)) symbols[4]++;
                else blocked[4]=true;
            }
            if (x - k >= 0 &&  !blocked[5] ) {
                if (currentTable[x-k][y].equals(allySymbol)) symbols[5]++;
                else blocked[5]=true;
            }


            if (y - k >= 0 &&  !blocked[6] ) {
                if (currentTable[x][y-k].equals(allySymbol)) symbols[6]++;
                else blocked[6]=true;
            }
            if (y + k < size &&  !blocked[7]) {
                if (currentTable[x][y+k].equals(allySymbol)) symbols[7]++;
                else blocked[7]=true;
            }
            for (int i = 0; i <symbols.length ; i+=2) {
                symbols[i]+=symbols[i+1];
                symbols[i+1]=symbols[i];
            }

        }
        for (int i = 0; i <symbols.length ; i++) {
            symbols[i]*=5;
        }

    return symbols;
    }

    public void deletePotentialOfMoves(Character symb,int x, int y) {
        int [] allySymbols=howManySymbols(symb,x,y);
        tableWithPotentialMoves[x][y]--;

        for (int i = 1; i < 5; i++) {
            if (x + i < size && y + i < size && currentTable[x + i][y + i].isEmpty()) {
                tableWithPotentialMoves[x + i][y + i]=allySymbols[0]+5;
                allySymbols[0]/=4;
            }
            if (x - i >= 0 && y - i >= 0 && currentTable[x - i][y - i].isEmpty()) {
                tableWithPotentialMoves[x - i][y - i]=allySymbols[1]+5;
                allySymbols[1]/=4;

            }
            if (x + i < size && y - i >= 0 && currentTable[x + i][y - i].isEmpty()) {
                tableWithPotentialMoves[x + i][y - i]=allySymbols[2]+5;
                allySymbols[2]/=4;

            }
            if (x - i >= 0 && y + i < size && currentTable[x - i][y + i].isEmpty()) {
                tableWithPotentialMoves[x - i][y + i]=allySymbols[3]+5;
                allySymbols[3]/=4;

            }
            if (x + i < size && currentTable[x + i][y].isEmpty()) {
                tableWithPotentialMoves[x + i][y]=allySymbols[4]+5;
                allySymbols[4]/=4;

            }

            if (x - i >= 0 && currentTable[x - i][y].isEmpty()) {
                tableWithPotentialMoves[x - i][y]=allySymbols[5]+5;
                allySymbols[5]/=4;

            }
            if (y - i >= 0 && currentTable[x][y - i].isEmpty()) {
                tableWithPotentialMoves[x][y - i]=allySymbols[6]+5;
                allySymbols[6]/=4;

            }
            if (y + i < size && currentTable[x][y + i].isEmpty()) {
                tableWithPotentialMoves[x][y + i]=allySymbols[7]+5;
                allySymbols[7]/=4;
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
        Character someoneWon;
        List<Pair<Integer, Integer>> list=new ArrayList<>();

        for (Pair<Integer, Integer> pair : listWithMaxPairs) {
            currValue = makePotentialMovesTable(aiSymbol,pair.getKey(), pair.getValue());
            list=findMax();
            for (Pair<Integer,Integer> pair2: listWithMaxPairs) {
                int curr2=makePotentialMovesTable(aiSymbol,pair2.getKey(),pair2.getValue());
                if (currValue +curr2 > max && currentTable[pair.getKey()][pair.getValue()].isEmpty()) {

                    max = currValue+curr2;

                    maxPair = pair;
                    list.clear();
                }
                deletePotentialOfMoves(aiSymbol,pair.getKey(), pair.getValue());
            }
            /*someoneWon=checkWin();
            if (someoneWon!=null){
                if (someoneWon.equals(aiSymbol)) {
                    currValue=9999;
                    System.out.println("komputer");
                }
                else {
                    currValue=1000;
                    System.out.println("człowiek");
                }
                System.out.println("max = " + currValue);

                System.out.println("WYGRANA !!!!!!!!!!!!!!!!!!!!");
            }*/
           /* if (currValue > max && currentTable[pair.getKey()][pair.getValue()].isEmpty()) {

                max = currValue;

                maxPair = pair;
                list.clear();
            }

            if (currValue==max) list.add(pair);
            deletePotentialOfMoves(aiSymbol,pair.getKey(), pair.getValue());*/


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
            makePotentialMovesTable(aiSymbol,x, y);
           // deletePotentialOfMoves(humanSymbol,x,y);
            printCurrentTable();
            printTableOfPotentials();
        }


    }

    public void moveEnemy(int x, int y) {
        if (currentTable[x][y].isEmpty()) {
            currentTable[x][y] = humanSymbol.toString();
            makePotentialMovesTable(humanSymbol,x,y);
           // deletePotentialOfMoves(aiSymbol,x,y);

            printCurrentTable();
            printTableOfPotentials();
        }
    }

    public Character checkWin() {
        int tab[] = new int[8];
        int tab2[] = new int[8];
        Arrays.fill(tab, 0);
        Arrays.fill(tab2, 0);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Arrays.fill(tab, 0);
                Arrays.fill(tab2, 0);
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
                    if (tab[i] ==2) return aiSymbol;
                    if (tab2[i]==2) return humanSymbol;
                }
            }
        }
        return null;
    }
}
