package com.mastermind.Game;

import java.util.TimerTask;

import com.mastermind.UI.Board;

import java.time.LocalTime;

public class Time extends TimerTask {
    private int currentTime = 5;
    private Board board;

    public Time(Board board) {
        this.board = board;
    }

    public void run() {
        LocalTime dayF = LocalTime.ofSecondOfDay(currentTime);
        // board.drawTime(dayF.toString());

        if (currentTime == 0) {
            super.cancel();
            return;
        }

        changeCurrentTime();
    }

    private void changeCurrentTime() {
        currentTime--;
    }
}
