package cn.wjk.eda.element;

import cn.wjk.eda.enumration.ElementType;
import lombok.Getter;

import java.awt.*;

/**
 * @Package: cn.wjk.eda.element
 * @ClassName: Rectangle
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/10 22:11
 * @Description:
 */
@Getter
public class Rectangle extends Element {
    private int x, y;
    private final int width, height;
    private final boolean frame;
    private final Color frameColor;

    public Rectangle(Color color, int x, int y, int width, int height, boolean frame, Color frameColor) {
        super(ElementType.RECTANGLE, color, x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frame = frame;
        this.frameColor = frameColor;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.x, this.y, this.width, this.height);
        if (this.frame) {
            g.setColor(this.frameColor);
            g.drawRect(this.x, this.y, this.width, this.height);
        }
    }

    @Override
    public void move(int currentX, int currentY) {
        this.x += currentX - startX;
        this.y += currentY - startY;
    }
}
