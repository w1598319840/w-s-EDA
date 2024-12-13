package cn.wjk.eda.element;

import cn.wjk.eda.enumration.ElementType;

import java.awt.*;

/**
 * @Package: cn.wjk.eda.element
 * @ClassName: Polyline
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/13 12:36
 * @Description:
 */
public class Polyline extends Element {
    private final int[] xPoints, yPoints;

    public Polyline(Color color, int[] xPoints, int[] yPoints) {
        super(ElementType.POLYLINE, color, xPoints[0], yPoints[0]);
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.color);
        g.drawPolyline(xPoints, yPoints, xPoints.length);
    }

    @Override
    public void move(int currentX, int currentY) {
        for (int i = xPoints.length - 1; i >= 0; i--) {
            xPoints[i] = currentX + xPoints[i] - xPoints[0];
        }
        for (int i = yPoints.length - 1; i >= 0; i--) {
            yPoints[i] = currentY + yPoints[i] - yPoints[0];
        }
    }
}
