package cn.wjk.eda.element;

import cn.wjk.eda.enumration.ElementType;
import lombok.Getter;

import java.awt.*;

/**
 * @Package: cn.wjk.eda.element
 * @ClassName: Line
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/10 21:44
 * @Description:
 */
@Getter
public class Line extends Element {
    private final int x1, x2, y1, y2;

    public Line(ElementType type, Color color, int x1, int y1, int x2, int y2) {
        super(type, color, x1, y1);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.getColor());
        g.drawLine(this.x1, this.y1, this.x2, this.y2);
    }
}
