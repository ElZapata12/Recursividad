import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;

public class TrianguloFractal extends JPanel {
    public int trianguloFractal(int num) {
        if (num == 0) {
            return 3;
        } else {
            return trianguloFractal(num - 1) + (3 * (int) Math.pow(2, 2 * (num - 1)));
        }
    }

    public void dibujarTriangulo(Graphics2D g, int iteraciones, double x1, double y1, double x2, double y2, double x3, double y3) {
        if (iteraciones == 0) {
            g.draw(new Line2D.Double(x1, y1, x2, y2));
            g.draw(new Line2D.Double(x2, y2, x3, y3));
            g.draw(new Line2D.Double(x3, y3, x1, y1));
        } else {
            double x4 = (x1 + x2) / 2;
            double y4 = (y1 + y2) / 2;
            double x5 = (x2 + x3) / 2;
            double y5 = (y2 + y3) / 2;
            double x6 = (x3 + x1) / 2;
            double y6 = (y3 + y1) / 2;

            dibujarTriangulo(g, iteraciones - 1, x1, y1, x4, y4, x6, y6);
            dibujarTriangulo(g, iteraciones - 1, x4, y4, x2, y2, x5, y5);
            dibujarTriangulo(g, iteraciones - 1, x6, y6, x5, y5, x3, y3);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int iteraciones = 4;

        double ancho = getWidth();
        double alto = getHeight();

        double x1 = ancho / 2;
        double y1 = alto / 4;
        double x2 = ancho * 3 / 4;
        double y2 = alto * 3 / 4;
        double x3 = ancho / 4;
        double y3 = alto * 3 / 4;

        dibujarTriangulo(g2, iteraciones, x1, y1, x2, y2, x3, y3);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fractal Triángulo de Sierpinski");
        TrianguloFractal panel = new TrianguloFractal();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}