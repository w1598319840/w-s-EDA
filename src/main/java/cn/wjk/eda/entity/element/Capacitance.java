package cn.wjk.eda.entity.element;

import cn.wjk.eda.enumeration.ElementType;

import java.awt.*;

/**
 * @Package: cn.wjk.entity.element
 * @ClassName: Capacitance
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/13 12:08
 * @Description:
 */
public class Capacitance extends ComplexElement {
    public Capacitance(int x, int y) {
        super(ElementType.CAPACITANCE, x, y,
                new Text(Color.BLACK, x - 10, y + 60, ElementType.CAPACITANCE.getDesc(),
                        new Font("New Roman", Font.BOLD, 20)));
        elementList.add(new Line(Color.BLACK, x, y, x, y + 40));
        elementList.add(new Line(Color.BLACK, x + 20, y, x + 20, y + 40));
        elementList.add(new Line(Color.BLACK, x - 20, y + 20, x, y + 20));
        elementList.add(new Line(Color.BLACK, x + 20, y + 20, x + 20 + 20, y + 20));
        pinList.add(new Pin(x - 20, y + 20, this));
        pinList.add(new Pin(x + 20 + 20, y + 20, this));
    }

    @SuppressWarnings("unused")
    public static Element getCapacitance() {
        return loadComplexElement(ElementType.CAPACITANCE, Capacitance.class);
    }
}
