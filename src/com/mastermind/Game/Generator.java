package com.mastermind.Game;

import com.mastermind.Utils.Logger;

/**
 * Generator class
 *
 * <p>
 * Transforms a level into relative sizes
 * </p>
 *
 * @see Level
 */
public class Generator {
    /**
     * The passed level used to find the size
     */
    public Level level;

    /**
     * Field that contains the size based on the level
     */
    public int size;

    /**
     * Constructor that finds the size based on the level
     *
     * @param level Level selected by the user
     */
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

        Logger.debug("Used level: " + level.toString());
    }
}
