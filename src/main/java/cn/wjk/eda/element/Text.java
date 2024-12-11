package cn.wjk.eda.element;

import cn.wjk.eda.enumration.ElementType;
import lombok.Getter;

import java.awt.*;

/**
 * @Package: cn.wjk.eda.element
 * @ClassName: Text
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/11 12:38
 * @Description:
 */
@Getter
public class Text extends Element {
    private final int x, y;
    private final String text;
    private final Font font;

    public Text(Color color, int x, int y, String text, Font font) {
        super(ElementType.TEXT, color, x, y);
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.getColor());
        g.setFont(this.font);
        g.drawString(this.text, this.x, this.y);
    }
}
