package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class DrawArea extends JPanel {
    double defaultSize = 0;
    public DrawArea() {
        super();
        addHierarchyListener(e -> this.defaultSize = this.getWidth()); // фиксируем начальный размер формы в поле, при добавлении DrawArea на основную форму
    }
    private double radius= 0;
    //int padding = 20; // просто отступы слева/справа/снизу/сверху от края области для рисования


    public void setCircleSize(double radius) {
        this.radius= radius;
        // запрашиваем перерисовку панели (чтобы подпись обновилась, тут вызовется метод paintComponent)
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // преобразуем контекст устройства в контекст устройства для рисования 2D график
        Graphics2D g2 = (Graphics2D) g;

        // заполняем всю область панельки белым цветом
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());

        // чтобы при изменении размеров формы пересчитывался размер
        // в качестве стороны используем минимальную сторону панели
        int height = (int)((1.0 * this.getHeight() / this.defaultSize) * ((int) radius * 2 * 65));


        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(rh);

        g2.setPaint(Color.GREEN);
        Area a1 = new Area(createSimpleStar(height/2,height,height));
        Area a2 = new Area(new Ellipse2D.Double(height/2,  height/2, height, height));

        a2.subtract(a1);
        g2.fill(a2);


        g2.setPaint(Color.BLACK);
        g2.drawString(String.valueOf(this.radius) + " см", height / 2, height + 12);
    }

    private static Shape createSimpleStar(double radius, double koordX, double koordY)
    {
        return drawStar(koordX, koordY, radius/2.65, radius , 5,
                Math.toRadians(-18));
    }



    private static Shape drawStar(double koordX, double koordY,
                                  double innerRadius, double outerRadius, int numRays,
                                  double startAngleR)
    {
        Path2D p = new Path2D.Double();
        double deltaAngleR = Math.PI / numRays;
        for (int i = 0; i < numRays * 2; i++)
        {
            double angleR = startAngleR + i * deltaAngleR;
            double ca = Math.cos(angleR);
            double sa = Math.sin(angleR);
            double relX = ca;
            double relY = sa;
            if ((i & 1) == 0)
            {
                relX *= outerRadius;
                relY *= outerRadius;
            }
            else
            {
                relX *= innerRadius;
                relY *= innerRadius;
            }
            if (i == 0)
            {
                p.moveTo(koordX + relX, koordY + relY);
            }
            else
            {
                p.lineTo(koordX + relX, koordY + relY);
            }
        }
        p.closePath();
        return p;
    }
}
