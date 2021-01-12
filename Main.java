package tictactoe;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter cells:");
        Scanner scanner = new Scanner(System.in);
        String input = "_________";
        int[] move = new int[2];
        char[][] table = new char[3][3];
        char[][] inverted = new char[3][3];
        int player = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = input.charAt(i * 3 + j);
                inverted[j][i] = table[i][j];
            }
        }
        Table(table);
        System.out.println(State(table, inverted, input));
        while (State(table, inverted, input).equals("Enter the coordinates:")) {
            Movement(move,scanner,table,input,player);
            Table(table);
            State(table, inverted, input);
            player++;
        }
        System.out.println(State(table, inverted, input));
    }

    public static char[][] ReInvert(char[][] table) {
        char[][] inverted = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inverted[j][i] = table[i][j];
            }
        }
        return inverted;
    }

    public static void Movement (int[] move,Scanner scanner,char[][] table,String input,int player){
        if (scanner.hasNextInt()) {
            move[0] = scanner.nextInt();
            move[1] = scanner.nextInt();
            if (move[0] > 3 || move[0] < 1 || move[1] > 3 || move[1] < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                Movement(move,scanner,table,input,player);
            } else if ((table[move[0]-1][move[1]-1]=='X')||(table[move[0]-1][move[1]-1]=='O')) {
                System.out.println("This cell is occupied! Choose another one!");
                Movement(move,scanner,table,input,player);
            } else {
                for (int i = 0; i < input.length(); i++) {
                    if ((player % 2) == 0) {
                        table[move[0] - 1][move[1] - 1] = 'X';
                    } else {
                        table[move[0] - 1][move[1] - 1] = 'O';
                    }
                }
            }
        } else if (!scanner.hasNextInt()) {
            System.out.println("You should enter numbers!");
            Movement(move,scanner,table,input,player);
        }
    }

    public static void Table(char[][] table) {
        System.out.println("---------\n| "
                + table[0][0] + " " + table[0][1] + " " + table[0][2] + " |\n| "
                + table[1][0] + " " + table[1][1] + " " + table[1][2] + " |\n| "
                + table[2][0] + " " + table[2][1] + " " + table[2][2] + " |\n"
                + "---------");
    }

    public static String State(char[][] table, char[][] inverted, String input) {
        char[] p1 = new char[]{'X', 'X', 'X'};
        char[] p2 = new char[]{'O', 'O', 'O'};
        String winner = "Draw";
        int countx = 0;
        int counto = 0;
        int count_ = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '_') {
                count_++;
                if (count_ > 0) {
                    winner = "Enter the coordinates:";
                }
            }
        }
        inverted=ReInvert(table);
        for (int i = 0; i < 3; i++) {
            if (Arrays.equals(table[i], p1) || Arrays.equals(inverted[i], p1) ||
                    ((table[0][0] == p1[0]) && (table[2][2] == p1[0]) && (table[1][1] == p1[0])) ||
                    ((table[0][2] == p1[0]) && (table[2][0] == p1[0]) && (table[1][1] == p1[0])))  {
                if (winner.equals("O wins")) {
                    winner = "Impossible";
                } else {
                    winner = "X wins";
                }
            }

            if (Arrays.equals(table[i], p2) || Arrays.equals(inverted[i], p2) ||
                    ((table[0][0] == p2[0]) && (table[2][2] == p2[0]) && (table[1][1] == p2[0]))  ||
                    ((table[0][2] == p2[0]) && (table[2][0] == p2[0]) && (table[1][1] == p2[0]))) {
                if (winner.equals("X wins")) {
                    winner = "Impossible";
                } else {
                    winner = "O wins";
                }

            }
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'X') {
                countx++;
            } else if (input.charAt(i) == 'O') {
                counto++;
            }
        }
        if (countx > counto + 1 || counto > countx + 1) {
            winner = "Impossible";
        }
        return (winner);
    }
}


