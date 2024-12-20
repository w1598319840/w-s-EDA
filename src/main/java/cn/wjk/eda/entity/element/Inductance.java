package cn.wjk.eda.entity.element;

import cn.wjk.eda.enumeration.ElementType;

import java.awt.*;

/**
 * @Package: cn.wjk.entity.element
 * @ClassName: Inductance
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/13 12:23
 * @Description:
 */
public class Inductance extends ComplexElement {
    public Inductance(int x, int y) {
        super(ElementType.INDUCTANCE, x, y);
        elementList.add(new Arc(Color.BLACK, x, y - 10, 20, 20, 0, 180));
        elementList.add(new Arc(Color.BLACK, x + 20, y - 10, 20, 20, 0, 180));
        elementList.add(new Arc(Color.BLACK, x + 40, y - 10, 20, 20, 0, 180));
        elementList.add(new Arc(Color.BLACK, x + 60, y - 10, 20, 20, 0, 180));
        elementList.add(new Line(Color.BLACK, x - 20, y, x, y));
        elementList.add(new Line(Color.BLACK, x + 80, y, x + 100, y));
        pinList.add(new Pin(x - 20, y, this));
        pinList.add(new Pin(x + 100, y, this));
        elementList.add(new Text(Color.BLACK, x + 20, y + 20, this.getType().getDesc(),
                new Font("New Roman", Font.BOLD, 20)));
    }
}
