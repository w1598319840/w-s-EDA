package cn.wjk.eda.element;

import cn.wjk.eda.enumration.ElementType;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @Package: cn.wjk.eda.element
 * @ClassName: Pin
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/11 12:52
 * @Description:
 */
@Setter
@Getter
public class Pin extends Element {
    private int x, y;
    private boolean linked;

    public Pin(int x, int y) {
        super(ElementType.PIN, null, x, y);
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        if (linked) {
            g.fillOval(x - 5, y - 5, 10, 10);
        } else {
            g.drawOval(x - 5, y - 5, 10, 10);
        }
    }

    @Override
    public void move(int currentX, int currentY) {
        this.x += currentX - startX;
        this.y += currentY - startY;
    }
}
