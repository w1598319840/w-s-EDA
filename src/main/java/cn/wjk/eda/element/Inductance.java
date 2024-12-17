package cn.wjk.eda.element;

import cn.wjk.eda.enumeration.ElementType;

import java.awt.*;

/**
 * @Package: cn.wjk.eda.element
 * @ClassName: Inductance
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/13 12:23
 * @Description:
 */
public class Inductance extends ComplexElement {
    public Inductance(int x, int y) {
        super(ElementType.INDUCTANCE, x, y);
        elementList.add(new Arc(Color.BLACK, x, y, 20, 20, 0, 180));
        elementList.add(new Arc(Color.BLACK, x + 20, y, 20, 20, 0, 180));
        elementList.add(new Arc(Color.BLACK, x + 40, y, 20, 20, 0, 180));
        elementList.add(new Arc(Color.BLACK, x + 60, y, 20, 20, 0, 180));
        elementList.add(new Line(Color.BLACK, x - 30, y + 10, x, y + 10));
        elementList.add(new Line(Color.BLACK, x + 80, y + 10, x + 110, y + 10));
        pinList.add(new Pin(x - 30, y + 10,this));
        pinList.add(new Pin(x + 110, y + 10,this));
        elementList.add(new Text(Color.BLACK, x + 20, y + 30, this.getType().getDesc(),
                new Font("New Roman", Font.BOLD, 20)));
    }
}
