package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends JPanel {
    private final GameApplication frame;
    int rows;
    int cols;
    int canvasWidth = 580;
    int canvasHeight = 400;
    int boardWidth;
    int boardHeight;
    int cellWidth;
    int cellHeight;
    int padX;
    int padY;
    int stoneSize = 20;
    private final List<Stick> sticks = new ArrayList<>();
    private final List<Point> intersectionPoints = new ArrayList<>();
    private final List<Point> blueCircles = new ArrayList<>();
    private final List<Point> redCircles = new ArrayList<>();
    private boolean redTurn = true;

    public DrawingPanel(GameApplication frame) {
        this.frame = frame;
        repaint();
        init(5, 5);
        initializeSticks();
        addMouseListener(new CircleClickListener());
        //repaint();
    }
    final void init(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }
    private boolean areAdjacentNodes(int row1, int col1, int row2, int col2) {
        boolean sameRow = Math.abs(row1 - row2) <= 1;
        boolean sameCol = Math.abs(col1 - col2) <= 1;
        // daca diferenta dintre liniile si coloanele lor e maxim 1 atunci sunt noduri vecine
        return sameRow && sameCol;
    }
    private void initializeSticks() {
        sticks.clear(); // sterg stick-urile vechi pentru a pune altele noi
        intersectionPoints.clear();
        Random random = new Random();
        // genereaza si pune pe board stick-uri in mod random
        for (int i = 0; i < 30; i++) {
            int row1 = random.nextInt(rows);
            int col1 = random.nextInt(cols);
            int row2, col2;
            do {
                row2 = random.nextInt(rows);
                col2 = random.nextInt(cols);
            } while (!(row1 == row2 && Math.abs(col1 - col2) == 1) && !(col1 == col2 && Math.abs(row1 - row2) == 1));
            if (areAdjacentNodes(row1, col1, row2, col2)) {
                sticks.add(new Stick(row1, col1, row2, col2));
            }
        }
        // dupa ce am generat stick-urile caut intersectiile lor
        findIntersectionPoints();
        repaint();
    }
    private void findIntersectionPoints() {
        intersectionPoints.clear(); //golesc pentru a personaliza pentru setul curent de stick-uri
        for (int i = 0; i < sticks.size(); i++) {
            for (int j = i + 1; j < sticks.size(); j++) {
                Point intersection = getIntersection(sticks.get(i), sticks.get(j));
                if (intersection != null) {
                    intersectionPoints.add(intersection);
                }
            }
        }
    }

    /**
     * intersectia a 2 sticks-uri
     * @param stick1 segment1
     * @param stick2 segment2
     * @return punctul lor de intersectie
     */
    private Point getIntersection(Stick stick1, Stick stick2) {
        int x1 = stick1.getCol1() * cellWidth + padX;
        int y1 = stick1.getRow1() * cellHeight + padY;
        int x2 = stick1.getCol2() * cellWidth + padX;
        int y2 = stick1.getRow2() * cellHeight + padY;

        int x3 = stick2.getCol1() * cellWidth + padX;
        int y3 = stick2.getRow1() * cellHeight + padY;
        int x4 = stick2.getCol2() * cellWidth + padX;
        int y4 = stick2.getRow2() * cellHeight + padY;

        int numitor = ((x1 - x2) * (y3 - y4)) - ((y1 - y2) * (x3 - x4));

        if (numitor == 0) {
            return null;
        }

        int intersectX = (((x1 * y2 - y1 * x2) * (x3 - x4)) - ((x1 - x2) * (x3 * y4 - y3 * x4))) / numitor;
        int intersectY = (((x1 * y2 - y1 * x2) * (y3 - y4)) - ((y1 - y2) * (x3 * y4 - y3 * x4))) / numitor;

//        // selecteaza un punct intre sticks-uri ca fiind intersectie as well
        if (intersectX < Math.min(x1, x2) || intersectX > Math.max(x1, x2) ||
                intersectX < Math.min(x3, x4) || intersectX > Math.max(x3, x4) ||
                intersectY < Math.min(y1, y2) || intersectY > Math.max(y1, y2) ||
                intersectY < Math.min(y3, y4) || intersectY > Math.max(y3, y4)) {
            //JOptionPane.showMessageDialog(frame, "cannot select this stone", "error selectin stone", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
//        if (x4 >= Math.min(x1, x2) && x3 <= Math.max(x1, x2) && y4 >= Math.min(y1, y2) && y3 <= Math.max(y1, y2)) {
//            return null;
//        }

        return new Point(intersectX, intersectY);
    }

    /**
     * metoda care adauga puncteel rosii intr-o lista
     * @param g boardul curect
     */
    private void paintRedCircles(Graphics2D g) {
        g.setColor(Color.RED);
        for (Point point : redCircles) {
            g.fillOval(point.x - stoneSize / 2, point.y - stoneSize / 2, stoneSize, stoneSize);
        }
    }

    /**
     * metoda care adauga puncteel rosii intr-o lista
     * @param g boardul curent
     */
    private void paintBlueCircles(Graphics2D g) {
        g.setColor(Color.BLUE);
        for (Point point : blueCircles) {
            g.fillOval(point.x - stoneSize / 2, point.y - stoneSize / 2, stoneSize, stoneSize);
        }
    }

    private void drawStick(Graphics2D g, Stick stick) {
        int node1X = padX + stick.getCol1() * cellWidth;
        int node1Y = padY + stick.getRow1() * cellHeight;
        int node2X = padX + stick.getCol2() * cellWidth;
        int node2Y = padY + stick.getRow2() * cellHeight;

        Stroke originalStroke = g.getStroke();
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawLine(node1X, node1Y, node2X, node2Y);
        g.setStroke(originalStroke);
    }

    void paintSticks(Graphics2D g) {
        g.setColor(Color.BLACK);
        sticks.clear(); // Clear the existing list of sticks
        Random random = new Random();
        // Generate and place sticks randomly on the board
        for (int i = 0; i < 100; i++) {
            int row1 = random.nextInt(frame.configPanel.getRows());
            int col1 = random.nextInt(frame.configPanel.getCols());
            int row2, col2;
            do {
                row2 = random.nextInt(frame.configPanel.getRows());
                col2 = random.nextInt(frame.configPanel.getCols());
            } while (!(row1 == row2 && Math.abs(col1 - col2) == 1) && !(col1 == col2 && Math.abs(row1 - row2) == 1));
            if (areAdjacentNodes(row1, col1, row2, col2)) {
                drawStickAt(row1, col1, row2, col2, (Graphics2D) getGraphics());
                sticks.add(new Stick(row1, col1, row2, col2));
            }
        }
    }
    private void drawStickAt(int row1, int col1, int row2, int col2, Graphics2D g) {
        int node1X = padX + col1 * cellWidth;
        int node1Y = padY + row1 * cellHeight;

        int node2X = padX + col2 * cellWidth;
        int node2Y = padY + row2 * cellHeight;

        Stroke originalStroke = g.getStroke();
        g.setStroke(new BasicStroke(3));

        g.setColor(Color.BLACK);
        g.drawLine(node1X, node1Y, node2X, node2Y);

        g.setStroke(originalStroke);
    }

    private void paintGrid(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        //horizontal lines
        for (int row = 0; row < rows; row++) {
            int y = padY + row * cellHeight;
            g.drawLine(padX, y, padX + boardWidth, y);
        }
        //vertical lines
        for (int col = 0; col < cols; col++) {
            int x = padX + col * cellWidth;
            g.drawLine(x, padY, x, padY + boardHeight);
        }
        //intersections
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                g.setColor(Color.LIGHT_GRAY);
                g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics); // call superclass method first
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
        for (Stick stick : sticks) {
            drawStick(g, stick);
        }
        paintRedCircles(g);
        paintBlueCircles(g);
    }

    private class CircleClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Point clickPoint = e.getPoint();
            for (Point intersection : intersectionPoints) {
                int distanceSquared = (clickPoint.x - intersection.x) * (clickPoint.x - intersection.x) +
                        (clickPoint.y - intersection.y) * (clickPoint.y - intersection.y);
                if (distanceSquared <= (stoneSize / 2) * (stoneSize / 2)) {
                    if (redTurn) {
                        redCircles.add(intersection);
                    } else {
                        blueCircles.add(intersection);
                    }
                    repaint();
                    redTurn = !redTurn; // schimba playerul
                    break;
                }
            }
        }
    }
    private static class Stick {
        private final int row1;
        private final int col1;
        private final int row2;
        private final int col2;

        public Stick(int row1, int col1, int row2, int col2) {
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
        }

        public int getRow1() {
            return row1;
        }

        public int getCol1() {
            return col1;
        }

        public int getRow2() {
            return row2;
        }

        public int getCol2() {
            return col2;
        }
    }
}