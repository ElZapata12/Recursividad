import java.awt.*;
import javax.swing.*;
//* Miguel Angel Zapata Rosales
// Marco Antonio Marcos Bonifacio
// Brian Jose De Gante Fidel,
// Ashlyn Annete Miranda Hernandez
//Danna Johanna Perez Mendoza


public class FractalArbol extends JPanel {
    private int depth;

    public FractalArbol(int depth) {
        this.depth = depth;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, getWidth() / 2, getHeight() - 50, -90, depth);
    }

    public void drawTree(Graphics g, int x1, int y1, double angle, int depth) {
        if (depth == 0) return;
        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 10);
        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 10);
        g.drawLine(x1, y1, x2, y2);
        drawTree(g, x2, y2, angle - 20, depth - 1);
        drawTree(g, x2, y2, angle + 20, depth - 1);
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Ingrese la cantidad de iteraciones:");
        int depth = Integer.parseInt(input);

        JFrame frame = new JFrame("Árbol Fractal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new FractalArbol(depth));
        frame.setVisible(true);
    }
}