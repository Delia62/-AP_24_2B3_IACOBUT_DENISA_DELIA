package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class Board extends JPanel{
    final MainFrame frame;
    final static int W = 410, H = 410;
    final static int nrLines=10;
    public int[][] board = new int[10][10];
    public Ship[] ships = new Ship[4];
    BufferedImage image;
    Graphics2D graphics;

    public Ship[] getShips() {
        return ships;
    }

    public boolean checkBoard(int line, int column){
        if(board[line][column] != 0){
            return true;
        }
        return false;
    }
    public Board(MainFrame frame){
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                board[i][j]=0;
            }
        }
        createRandomShips();
        createBoard();
    }
    private void createRandomShips() {
        Random random = new Random();
        int firstNumber;
        int secondNumber;

        for (int i = 0; i < ships.length; i++) {
            firstNumber = random.nextInt(9) + 1;
            secondNumber = random.nextInt(9) + 1;
            while(board[firstNumber][secondNumber] != 0){
                firstNumber = random.nextInt(9) + 1;
                secondNumber = random.nextInt(9) + 1;
            }
            board[firstNumber][secondNumber] = 1;
            drawShip(firstNumber,secondNumber);
        }
    }

    private void drawShip(int x, int y){
        graphics.setColor(Color.BLACK);
        placeShips(x, y, graphics.getColor());
    }



    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 600);
    }

    final public void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }
    final void createBoard() {
        createOffscreenImage();
        drawLines();
        createRandomShips();
        repaint();
    }
    private void drawLines() {
        graphics.setColor(Color.BLACK);
        int i,coord;
        for(i=0; i <= nrLines; i++){
            coord=i*(H/nrLines);
            graphics.drawLine(coord+5,5,coord+5,H-5);
            graphics.drawLine(5,coord+5,H-5,coord+5);
        }
    }
    public void place(int lin, int col, Color color){
        graphics.setColor(color);
        lin--;
        col--;
        graphics.fillOval(lin*(H/nrLines)+7,col*(H/nrLines)+7,H/nrLines-4, H/nrLines-4);
        repaint();
    }
    public void placeShips(int lin, int col, Color color){
        graphics.setColor(color);
        lin--;
        col--;
        graphics.fillRect(lin*(H/nrLines)+7,col*(H/nrLines)+7,H/nrLines-4, H/nrLines-4);
        repaint();
    }


    @Override
    public void update(Graphics g) {
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }
}
