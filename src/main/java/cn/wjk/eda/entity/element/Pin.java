package cn.wjk.eda.entity.element;

import cn.wjk.eda.enumeration.ElementType;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @Package: cn.wjk.entity.element
 * @ClassName: Pin
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/11 12:52
 * @Description:
 */
@Setter
@Getter
public class Pin extends Element {
    private boolean linked;
    @JSONField(serialize = false)
    private ComplexElement owner;

    public Pin(int x, int y, ComplexElement owner) {
        super(ElementType.PIN, null, x, y);
        this.owner = owner;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        if (!linked) {
            g.drawOval(metaX - 5, metaY - 5, 10, 10);
        }
    }

    @Override
    public void move(int currentX, int currentY) {
        this.metaX += currentX - startX;
        this.metaY += currentY - startY;
    }

    public boolean selected(int selectedX, int selectedY) {
        return selectedX <= metaX + 10 && selectedX >= metaX && selectedY <= metaY + 10 && selectedY >= metaY;
    }
}
