package cn.wjk.eda.element;

import cn.wjk.eda.enumration.ElementType;
import lombok.Getter;

import java.awt.*;

/**
 * @Package: cn.wjk.eda.element
 * @ClassName: Element
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/10 21:36
 * @Description:
 */
@Getter
public abstract class Element {
    private final int metaX, metaY;
    private final ElementType type;
    private final Color color;

    public Element(ElementType type, Color color, int metaX, int metaY) {
        this.type = type;
        this.color = color;
        this.metaX = metaX;
        this.metaY = metaY;
    }

    public abstract void paint(Graphics g);

}