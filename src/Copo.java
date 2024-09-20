import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;

public class Copo extends JPanel {
    public int copoNieve(int num) {
        if (num == 0) {
            return 3;
        } else {
            return copoNieve(num - 1) + (3 * (int) Math.pow(2, 2 * (num - 1)));
        }
    }

    public void dibujarKoch(Graphics2D g, int iteraciones, double x1, double y1, double x2, double y2) {
        if (iteraciones == 0) {
            g.draw(new Line2D.Double(x1, y1, x2, y2));
        } else {
            double deltaX = x2 - x1;
            double deltaY = y2 - y1;

            double x3 = x1 + deltaX / 3;
            double y3 = y1 + deltaY / 3;
            double x4 = x1 + 2 * deltaX / 3;
            double y4 = y1 + 2 * deltaY / 3;

            double x5 = (x1 + x2) / 2 + Math.sqrt(3) * (y1 - y2) / 6;
            double y5 = (y1 + y2) / 2 + Math.sqrt(3) * (x2 - x1) / 6;

            dibujarKoch(g, iteraciones - 1, x1, y1, x3, y3);
            dibujarKoch(g, iteraciones - 1, x3, y3, x5, y5);
            dibujarKoch(g, iteraciones - 1, x5, y5, x4, y4);
            dibujarKoch(g, iteraciones - 1, x4, y4, x2, y2);
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int iteraciones = 4;

        double ancho = getWidth();
        double alto = getHeight();

        double x1 = ancho / 4;
        double y1 = alto / 2;
        double x2 = ancho * 3 / 4;
        double y2 = alto / 2;

        dibujarKoch(g2, iteraciones, x1, y1, x2, y2);
        dibujarKoch(g2, iteraciones, x2, y2, (x1 + x2) / 2, y1 - (x2 - x1) * Math.sqrt(3) / 2);
        dibujarKoch(g2, iteraciones, (x1 + x2) / 2, y1 - (x2 - x1) * Math.sqrt(3) / 2, x1, y1);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fractal Copo de Nieve de Koch");
        Copo panel = new Copo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}