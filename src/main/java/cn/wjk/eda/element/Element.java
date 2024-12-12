package cn.wjk.eda.element;

import cn.wjk.eda.enumration.ElementType;
import cn.wjk.eda.utils.GlobalIdUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.Serializable;

/**
 * @Package: cn.wjk.eda.element
 * @ClassName: Element
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/10 21:36
 * @Description:
 */
@Getter
@NoArgsConstructor
public class Element implements Serializable {
    private long id;
    private int metaX, metaY;
    private ElementType type;
    private Color color;

    public Element(ElementType type, Color color, int metaX, int metaY) {
        this.type = type;
        this.color = color;
        this.metaX = metaX;
        this.metaY = metaY;
        this.id = GlobalIdUtils.generateGlobalId();
    }

    public void paint(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}