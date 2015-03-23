package com.hdsp.bowling.view;

import java.util.Scanner;

public class RollDialog {
    public RollDialog() {
    }

    public int makeRoll(String player){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tirada del jugador " + player + ", puntos: ");
        int score = scanner.nextInt();
        return score;
    }
}
