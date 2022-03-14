package com.company;
import java.awt.*;
import java.util.Scanner;

public class Main {

    public static Point pos = new Point(0,0);
    public static Point des = new Point(0,0);
    public static int[][][] board = new int[8][8][2];
    public static int player = 1;
    // pole barva 0white 1black
    // hráči 1white 2black
    public static void main(String[] args) {

        board = Fill();
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println("Hráč " + player + " je na tahu");
             Show(board);
             boolean pick;
             do {
                 System.out.println("Hráči " + player + " zadej pozici fugurky(xy)<11;88>");
                     pos = Translate(Integer.parseInt(scan.nextLine()));
                     if (board[pos.x][pos.y][0] == player) pick = true;
                     else pick = false;
             } while (!pick);
             boolean pick_2;
             Show(board);
             do {
                 System.out.println("Hráči " + player + " zadej pozici tahu(xy)<11;88>");
                 des = Translate(Integer.parseInt(scan.nextLine()));
                 if (Val_move()){
                     pick_2 = true;
                     Move();
                 }
                 else pick_2 = false;
             } while (!pick_2);
             if (player == 1) player = 2;
             else player = 1;
        }

    }
    static void Move(){
        board[des.x][des.y][0] = player;
        board[pos.x][pos.y][0] = 0;
    }
    static boolean Val_move(){
        if (Math.abs( pos.x - des.x) == 1 && Math.abs( pos.y - des.y) == 1 && board[des.x][des.y][0] == 0){
            if (player == 1 && des.y > pos.y) return true;
            if (player == 2 && des.y < pos.y) return true;
        }
        if (Math.abs( pos.x - des.x) == 3 && Math.abs( pos.y - des.y) == 3){
            if (board[des.x][des.y][0] == 0 && board[(des.x+pos.x)/2][(des.y+pos.y)/2][0] != 0 && board[(des.x+pos.x)/2][(des.y+pos.y)/2][0] != player){
                if (player == 1 && des.y > pos.y) return true;
                if (player == 2 && des.y < pos.y) return true;
            }
        }
        return false;
    }
    static int[][][] Fill(){
        int[][][] board = new int[8][8][2];
        for (int y = 0; y < 8; y++){
            for (int x = 0; x < 8; x++){
                if (y % 2 == 0){
                    if (x % 2 == 0) board[x][y][1] = 0;
                    else board[x][y][1] = 1;
                }
                if (y % 2 == 1){
                    if (x % 2 == 0) board[x][y][1] = 1;
                    else board[x][y][1] = 0;
                }

                board[x][y][0] = 0;
                if (y<=2 && board[x][y][1] == 1) board[x][y][0] = 1;
                if (y>=5 && board[x][y][1] == 1) board[x][y][0] = 2;
            }
        }
        return board;
    }
    static void Show(int[][][] board){
        System.out.print("     1   2   3   4   5   6   7   8  ");
        for (int y = 0; y<8; y++){
            System.out.print("\n " + (9-(y+1)) + " |");
            for (int x = 0; x<8; x++){
                System.out.print(" " + board[x][y][0] + " |");
            }
        }
        System.out.println();
    }
    static Point Translate(int i){
        int y = 9 - (i % 10);
        int x = (i - (i % 10))/10;
        return new Point(x-1,y-1);
    }
}
