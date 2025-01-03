package cn.wjk.eda.entity.element;

import cn.wjk.eda.enumeration.ElementType;
import lombok.Getter;

import java.awt.*;

/**
 * @Package: cn.wjk.entity.element
 * @ClassName: Line
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/10 21:44
 * @Description:
 */
@Getter
public class Line extends Element {
    private int x1, x2, y1, y2;

    public Line(Color color, int x1, int y1, int x2, int y2) {
        super(ElementType.LINE, color, x1, y1);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.color);
        g.drawLine(this.x1, this.y1, this.x2, this.y2);
    }

    @Override
    public void move(int currentX, int currentY) {
        int dx = currentX - startX;
        int dy = currentY - startY;
        this.x2 += dx;
        this.y2 += dy;
        this.x1 += dx;
        this.y1 += dy;
    }
}
