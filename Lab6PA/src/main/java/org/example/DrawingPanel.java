package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
class DrawingPanel extends JPanel implements MouseListener {
    private final MainFrame frame;
    int rows, cols;

    BufferedImage image;
    Graphics2D offscreen; //the offscreen graphics
    int canvasWidth = 400, canvasHeight = 400;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize = 20;
    List<Node> nodes = new ArrayList<>();
    int stoneX = -1; //linia pe care se afla piesa
    int stoneY = -1; // coloana pe care se afla piesa
    boolean turn = false; // if color= true => Blue, else =>Red
    boolean gameOver = false;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init(frame.configPanel.getRows(), frame.configPanel.getCols());
    }

    private void createOffscreenImage() {
        image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        offscreen.setColor(Color.WHITE); //fill the image with white
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
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
        this.gameOver = false;

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid();
        paintSticks();

       /* for (Node node : nodes) System.out.println(node.toString());
        System.out.println();
*/

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!gameOver) {
                    drawStone(e.getX(), e.getY());
                    repaint();
                }
            }
            //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);

    }

    private void paintGrid() {
        offscreen.setColor(Color.DARK_GRAY);
        //horizontal lines
        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            offscreen.drawLine(x1, y1, x2, y1);
        }
        //vertical lines
        for (int col = 0; col < cols; col++) {
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int y2 = y1 + boardHeight;
            offscreen.drawLine(x1, y1, x1, y2);
        }

        //intersections
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                offscreen.setColor(Color.LIGHT_GRAY);
                offscreen.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
                nodes.add(new Node(row, col));
            }
        }
    }

    private void paintSticks() {
        offscreen.setColor(Color.BLACK);
        offscreen.setStroke(new BasicStroke(5));
        Random rand = new Random();

        //orizontal sitcks
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols - 1; col++) {
                int x1 = padX + col * cellWidth;
                int y1 = padY + row * cellHeight;
                int x2 = padX + (col + 1) * cellWidth;
                if (rand.nextInt(2) == 1) {
                    offscreen.drawLine(x1, y1, x2, y1);
                    //add neighbours
                    nodes.get(findIndex(row, col)).addNeigbour(new Node(row, col + 1));
                    nodes.get(findIndex(row, col + 1)).addNeigbour(new Node(row, col));
                }
            }

        //vertical sticks
        for (int row = 0; row < rows - 1; row++)
            for (int col = 0; col < cols; col++) {
                int x1 = padX + col * cellWidth;
                int y1 = padY + row * cellHeight;
                int y2 = padY + (row + 1) * cellHeight;
                if (rand.nextInt(2) == 1) {
                    offscreen.drawLine(x1, y1, x1, y2);
                    //add neighbours
                    nodes.get(findIndex(row, col)).addNeigbour(new Node(row + 1, col));
                    nodes.get(findIndex(row + 1, col)).addNeigbour(new Node(row, col));
                }
            }

    }

    private void paintStone() {
        offscreen.setColor(getShapeColor());
        offscreen.drawOval(stoneX - stoneSize / 2, stoneY - stoneSize / 2, stoneSize, stoneSize);
        offscreen.fillOval(stoneX - stoneSize / 2, stoneY - stoneSize / 2, stoneSize, stoneSize);
    }


    protected void drawStone(int x, int y) {

        int oldCol = -1, oldRow = -1;
        if (stoneX != -1 && stoneY != -1) {//ne aflam la a doua mutare
            oldCol = (stoneX - padX) / cellWidth;
            oldRow = (stoneY - padY) / cellHeight;
        }


        boolean validNode = true;
        int newCol = (x - padX) / cellWidth;
        int newRow = (y - padY) / cellHeight;

        if (x < padX || y < padY) { //cursorul este in afara tablei de joc
            validNode = false;
        } else {
            int coordX, coordY;
            coordX = (((x - padX) % cellWidth) * 100) / cellWidth; //noul x, in procente
            coordY = (((y - padY) % cellHeight) * 100) / cellHeight; //noul y

            if (coordX > 10 && coordX < 90) { //cursorul se afla pe un spatiu alb (unde nu este pozitie de cerc)
                validNode = false;
            } else {
                if (coordX >= 90) { // se trece la urmatoarea coloana
                    newCol++;
                }
            }

            if (coordY > 10 && coordY < 90) {
                validNode = false;
            } else {
                if (coordY >= 90) { //se trece la urmatoarea linie
                    newRow++;
                }
            }
            if (validNode == true && !nodes.get(findIndex(newRow, newCol)).noNeighbours()) {
                //daca stone ul nu se afla pe un stick nu updatam coordonatele
                stoneX = padX + newCol * cellWidth;
                stoneY = padY + newRow * cellHeight;
            }
        }


        if (validNode) {
            if (nodes.get(findIndex(newRow, newCol)).isVisited()) {  //nodul a fost deja vizitat
                validNode = false;
            } else { //nodul nu se afla pe un stick ( nu are vecini)
                if (nodes.get(findIndex(newRow, newCol)).noNeighbours()) {
                    validNode = false;
                }
            }

            //daca ne aflam la a doua mutare, incepem sa validam
            if (oldRow != -1 && oldCol != -1 && validNode) {
               // System.out.println("are vecini " + nodes.get(findIndex(oldRow, oldCol)).toString());
//            //verificam daca nodul dinainte mai are veicni, daca nu are, atunci a castigat
                if (nodes.get(findIndex(oldRow, oldCol)).noNeighbours()) {
                    validNode = false;
                    gameOver = true;
                    printGameOver();
                } else {
                    //verificam daca nodul nou este vecin cu cel vechi
                    Node node = nodes.get(findIndex(oldRow, oldCol)).findNeighbour(newRow, newCol);
                    if (node == null)
                        validNode = false; //nodul nou nu este vecin a celui vechi
                }
            }
        }


        //verificam daca nodul curent a fost deja vizitat
        if (validNode) {
            paintStone();
            //pun nodul va fiind vizitat
            nodes.get(findIndex(newRow, newCol)).setVisited();
            //sterg nodul din vecinii nodului vechi
            if (oldRow != -1 && oldCol != -1) {
                nodes.get(findIndex(oldRow, oldCol)).removeNeighbour(newRow, newCol);
                nodes.get(findIndex(newRow, newCol)).removeNeighbour(oldRow, oldCol);
               // System.out.println("am eliminat nodul" + nodes.get(findIndex(oldRow, oldCol)).toString());
            }


        }


    }

    private Color getShapeColor() {
        if (!turn) {
            turn = true;
            return Color.RED;
        } else {
            turn = false;
            return Color.BLUE;
        }
    }

    private int findIndex(int row, int col) {
        for (int i = 0; i < nodes.size(); i++)
            if (nodes.get(i).getCol() == col && nodes.get(i).getRow() == row)
                return i;
        return -1;
    }

    private void printGameOver() {
        JFrame frame = new JFrame("Game Over");
        frame.setSize(400, 300);

        JLabel label1;
        if (turn) {
             label1 = new JLabel("Winner of the game: Red" );
        } else {
             label1 = new JLabel("Winner of the game: Blue" );
        }
        label1.setFont(new Font("Arial", Font.BOLD, 23));


        frame.add(label1);

        // Configurare pentru a afișa frame-ul în centrul ecranului
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
