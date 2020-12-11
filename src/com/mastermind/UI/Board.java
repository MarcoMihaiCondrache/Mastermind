package com.mastermind.UI;

import com.mastermind.Game.Generator;
import com.mastermind.Utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Board extends JPanel {
    static final long serialVersionUID = 3L;

    private final Container appContainer;
    private Graphics2D board;
    private final HashMap<Integer, HashMap<Integer, Position>> placeholderPositions = new HashMap<>();
    private int clickedI = 0;
    private int clickedJ = 0;
    private final Generator generator;
    private Boolean painting = false;

    public int boardWidth = 0;
    public int boardHeight = 0;
    public int fullWidth = 0;
    public int fullHeight = 0;

    public HashMap<Integer, Color> sequence = new HashMap<>();
    public HashMap<Integer, HashMap<Integer, Color>> placeholder = new HashMap<>();
    public HashMap<Integer, HashMap<Integer, Color>> indicators = new HashMap<>();
    public Boolean sequenceHidden = true;

    public Board(Container container, JFrame frame, Generator generator) {
        this.appContainer = container;
        this.generator = generator;

        // Enable double buffer (more performance but more memory usage)
        this.setDoubleBuffered(true);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                for (int i = 0; i < 8; i++) {
                    HashMap<Integer, Position> single = placeholderPositions.get(i);
                    for (int j = 0; j < generator.size; j++) {
                        Position position = single.get(j);

                        if (mouseX >= position.x && mouseX <= position.x + 50) {
                            if (mouseY >= position.y && mouseY <= position.y + 50) {
                                Logger.info("Clicked: " + i + ", " + j);

                                boolean available = true;

                                if (isRowCompleted(i) || !sequenceHidden) {
                                    available = false;
                                }

                                if (i + 1 <= 7) {
                                    int beforeRow = i + 1;

                                    for (int k = 0; k < generator.size; k++) {
                                        if (placeholder.get(beforeRow).get(k) == Color.GRAY) {
                                            available = false;
                                        }
                                    }
                                }

                                if (available) {
                                    clickedI = i;
                                    clickedJ = j;

                                    ColorSelector selector = new ColorSelector(frame);

                                    selector.addWindowListener(new WindowAdapter() {
                                        @Override
                                        public void windowClosed(WindowEvent e) {
                                            if (selector.save) {
                                                Logger.info("Dialog Closed: " + selector.selectedColor);
                                                updatePlaceholder(selector.selectedColor);

                                                if (isRowCompleted(clickedI)) {
                                                    rowCompleted(clickedI);
                                                }
                                            }

                                            frame.setEnabled(true);
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        fullWidth = appContainer.getWidth();
        fullHeight = appContainer.getHeight();
        boardWidth = Layout.getRatio(fullWidth, 0.8);
        boardHeight = fullHeight;

        board = (Graphics2D) g;
        board.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        board.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        board.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        board.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        board.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        board.setColor(new Color(139, 69, 19));

        // draw board background
        board.fillRect(0, 0, boardWidth, boardHeight);

        if (painting) {
            this.drawSequence();
            this.drawPlaceholder();
            this.drawIndicators();
        }
    }

    public void setPainting(Boolean arg) {
        if (this.sequence.size() > 0 && this.placeholder.size() > 0 && this.indicators.size() > 0) {
            this.painting = arg;
            this.repaint();
        }

        Logger.info("\nPaint request\n\nTrying to set painting with sizes: " + this.sequence.size() + ", " + this.placeholder.size() + ", " + this.indicators.size() + "\nPaint status: " + this.painting);
    }

    public void setSequence(HashMap<Integer, Color> newSequence) {
        this.sequence = newSequence;
    }

    public void setIndicators(HashMap<Integer, HashMap<Integer, Color>> newIndicators) {
        this.indicators = newIndicators;
    }

    public void setPlaceholder(HashMap<Integer, HashMap<Integer, Color>> placeholder) {
        this.placeholder = placeholder;
    }

    /*
        Update placeholder and repaint only portion
     */
    public void updatePlaceholder(Color color) {
        this.placeholder.get(clickedI).put(clickedJ, color);

        int x = this.placeholderPositions.get(clickedI).get(clickedJ).x;
        int y = this.placeholderPositions.get(clickedI).get(clickedJ).y;

        this.repaint(x, y, 60, 60);
    }

    private void drawOval(int x, int y, int xRadius, int yRadius, Color color, Boolean stroke) {
        Ellipse2D shape = new Ellipse2D.Double(x, y, xRadius, yRadius);
        board.setColor(color);
        board.fill(shape);

        if (stroke) {
            board.setColor(Color.WHITE);
            board.setStroke(new BasicStroke(3));
            board.draw(shape);
        }
    }

    private void drawRect(int x, int y, int xW) {
        board.setColor(Color.GRAY);
        board.fillRect(x, y, xW, 50);
    }

    private void drawSequence() {
        int cost = Layout.getRatio(boardWidth, Layout.calculateRatio(generator.size));
        int distances = Layout.getRatio(boardWidth, Layout.calculateRatio(generator.size));
        for (int i = 0; i < sequence.size(); i++) {
            drawOval(distances - (50 / 2), Layout.getRatio(boardHeight, Layout.calculateRatio(14)) - (50 / 2), 50, 50,
                    sequenceHidden ? Color.BLACK : sequence.get(i), true);
            distances += cost;
        }
    }

    private void drawPlaceholder() {
        int costY = Layout.getRatio(boardHeight, Layout.calculateRatio(9));
        int distancesY = Layout.getRatio(boardHeight, Layout.calculateRatio(9)) * 2;
        int costX = Layout.getRatio(boardWidth, Layout.calculateRatio(generator.size));
        int distancesX = Layout.getRatio(boardWidth, Layout.calculateRatio(generator.size));

        for (int i = 0; i < 8; i++) {
            HashMap<Integer, Position> single = new HashMap<>();

            for (int j = 0; j < generator.size; j++) {
                int x = distancesX - (50 / 2);
                int y = distancesY - (50 / 2);
                single.put(j, new Position(x, y));
                drawOval(x, y, 50, 50, placeholder.get(i).get(j), true);
                distancesX += costX;
            }

            distancesX = costX;
            distancesY += costY;
            placeholderPositions.put(i, single);
        }
    }

    private void drawIndicators() {
        int costY = Layout.getRatio(boardHeight, Layout.calculateRatio(9));
        int distancesY = Layout.getRatio(boardHeight, Layout.calculateRatio(9)) * 2;

        for (int i = 0; i < 8; i++) {
            HashMap<Integer, Color> indicator = indicators.get(7 - i);
            int spacer = (50 - (17 * 2)) / 3;
            int boxWidth = (17 * (generator.size / 2) + (spacer * ((generator.size / 2) + 1)));
            int rectX = (((fullWidth - boardWidth) - boxWidth) / 2) + boardWidth;
            int rectY = distancesY - (50 / 2);

            drawRect(rectX, rectY, boxWidth);

            int indicatorX = rectX + spacer;
            int indicatorY = rectY + spacer;
            for (int j = 0; j < generator.size; j++) {
                if (j > generator.size / 2) {
                    // Draw 3
                    indicatorX += spacer + 17;
                    drawOval(indicatorX, indicatorY, 17, 17, indicator.get(j), false);
                } else if (j == generator.size / 2) {
                    // Draw 2
                    indicatorX = rectX + spacer;
                    indicatorY += spacer + 17;
                    drawOval(indicatorX, indicatorY, 17, 17, indicator.get(j), false);
                } else {
                    // Draw from 0 to 1
                    drawOval(indicatorX, indicatorY, 17, 17, indicator.get(j), false);
                    indicatorX += spacer + 17;
                }
            }

            distancesY += costY;
        }
    }

    private void addIndicator(int row, Color color) {
        for (int i = 0; i < generator.size; i++) {
            if (indicators.get(row).get(i).equals(new Color(72, 72, 72))) {
                indicators.get(row).put(i, color);
                break;
            }
        }
    }

    private boolean isRowCompleted(int i) {
        boolean completed = true;

        for (int j = 0; j < generator.size; j++) {
            if (placeholder.get(i).get(j) == Color.GRAY) {
                completed = false;
            }
        }

        return completed;
    }

    private void rowCompleted(int i) {
        Logger.info("The row at: " + i + " was completed");

        if (i == 0) {
            sequenceHidden = false;
        }

        Logger.info(String.valueOf(placeholder.get(i)));

        HashMap<Integer, Color> sequenceCopy = sequence;
        HashMap<Integer, Color> rowCopy = placeholder.get(i);

        for (int j = 0; j < sequenceCopy.size(); j++) {
            if (rowCopy.get(j) == sequenceCopy.get(j)) {
                sequenceCopy.put(j, null);
                addIndicator(7 - i, Color.RED);
            } else if (sequenceCopy.containsValue(rowCopy.get(j))) {
                for (Map.Entry<Integer, Color> entry : sequenceCopy.entrySet()) {
                    if (entry != null && entry.getValue().equals(rowCopy.get(j))) {
                        sequenceCopy.put(entry.getKey(), null);
                        addIndicator(7 - i, Color.WHITE);
                        break;
                    }
                }
            }
        }

        if (placeholder.get(i).equals(sequence)) {
            sequenceHidden = false;

            Logger.info("Sequence match, hashcode: " + sequence.hashCode());
            return;
        }

        this.repaint();
    }
}
