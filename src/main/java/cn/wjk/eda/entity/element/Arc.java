package cn.wjk.eda.entity.element;

import cn.wjk.eda.enumeration.ElementType;

import java.awt.*;

/**
 * @Package: cn.wjk.entity.element
 * @ClassName: Arc
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/13 12:45
 * @Description:
 */
public class Arc extends Element {
    private int x, y;
    private final int width, height, startAngle, arcAngle;

    public Arc(Color color, int x, int y, int width, int height, int startAngle, int arcAngle) {
        super(ElementType.ARC, color, x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.drawArc(x, y, width, height, startAngle, arcAngle);
    }

    @Override
    public void move(int currentX, int currentY) {
        this.x += currentX - startX;
        this.y += currentY - startY;
    }
}
