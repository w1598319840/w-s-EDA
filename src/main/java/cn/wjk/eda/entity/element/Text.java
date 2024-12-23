package cn.wjk.eda.entity.element;

import cn.wjk.eda.enumeration.ElementType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;

/**
 * @Package: cn.wjk.entity.element
 * @ClassName: Text
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/11 12:38
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Text extends Element {
    private int x, y;
    private String text;
    private Font font;

    public Text(Color color, int x, int y, String text, Font font) {
        super(ElementType.TEXT, color, x, y);
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.color);
        g.setFont(this.font);
        g.drawString(this.text, this.x, this.y);
    }

    @Override
    public void move(int currentX, int currentY) {
        this.x += currentX - startX;
        this.y += currentY - startY;
    }
}
