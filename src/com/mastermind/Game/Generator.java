package com.mastermind.Game;

import com.mastermind.Utils.Logger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Generator {
    public Level level;
    public int size;

    public Generator(Level level) {
        this.level = level;

        switch (this.level) {
            case LEVEL_1:
                size = 4;
                break;
            case LEVEL_2:
                size = 6;
                break;
            case LEVEL_3:
                size = 8;
                break;
        }
    }
}
