package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int x = 0;

    public static void main(String[] args) {
        String start = "A1";
        int size = 5;
        int[][] squares = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                squares[i][j] = 0;
            }
        }
        int letter = start.charAt(0) - 65;
        int number = start.charAt(1) - 49;
        squares[letter][number] = 1;
        int[][] jumps = generateJumps(squares, letter, number, 1);
        for(int i = 0; i < size; i++){
            System.out.print(i+1+"  ");
            for(int j = 0; j < size; j++){
                System.out.printf("%3d ",jumps[j][size-1-i]);
            }
            System.out.print("\n");
        }
        System.out.print("\n  ");
        for(int i = 0; i < size; i++){
            System.out.printf("   %c", 65+i);
        }
        System.out.println("\n"+x);
    }

    private static int[][] generateJumps(int[][] squares, int letter, int number, int move){
        for(int i = 0; i < 8; i++){
            int number2 = number;
            int letter2 = letter;
            switch (i){
                case 0:
                    number2 += 2;
                    letter2-= 1;
                    break;
                case 1:
                    number2 += 2;
                    letter2 += 1;
                    break;
                case 2:
                    letter2 += 2;
                    number2 += 1;
                    break;
                case 3:
                    letter2 += 2;
                    number2 -= 1;
                    break;
                case 4:
                    number2 -= 2;
                    letter2 += 1;
                    break;
                case 5:
                    number2 -= 2;
                    letter2 -= 1;
                    break;
                case 6:
                    letter2 -= 2;
                    number2 -= 1;
                    break;
                case 7:
                    letter2 -= 2;
                    number2 += 1;
                    break;
            }

            if(letter2 >= 0 && letter2 < squares.length && number2 >= 0 && number2 < squares.length && squares[letter2][number2] == 0){
                int[][] squares2 = new int[squares.length][squares.length];
                for(int j = 0; j < squares.length; j++){
                    for(int k = 0; k < squares.length; k++){
                        squares2[j][k] = squares[j][k];
                    }
                }
                squares2[letter2][number2] = move + 1;
                x++;
//                System.out.println(move);
//                for(int k = 0; k < squares.length; k++){
//                    for(int j = 0; j < squares.length; j++){
//                        System.out.print(squares2[j][squares.length-1-k] + ",");
//                    }
//                    System.out.print("\n");
//                }
//                System.out.println("");

                if(move + 1 < squares.length * squares.length){
                    squares2 = generateJumps(squares2, letter2, number2, move + 1);
                }

                if(isFull(squares2)){
                    return squares2;
                }

            }


        }
        return squares;
    }

    private static boolean isFull(int[][] squares) {
        for(int i = 0; i < squares.length; i++){
            for(int j = 0; j < squares.length; j++){
                if(squares[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    private static String scan() {
        return new Scanner(System.in).nextLine();
    }
}
