package cn.wjk.eda.entity.element;


import cn.wjk.eda.enumeration.ElementType;

import java.awt.*;

/**
 * @Package: cn.wjk.entity.element
 * @ClassName: Resistance
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/11 12:52
 * @Description:
 */
public class Resistance extends ComplexElement {
    public Resistance(int x, int y) {
        super(ElementType.RESISTANCE, x, y,
                new Text(Color.BLACK, x + 30, y + 60, ElementType.RESISTANCE.getDesc(),
                        new Font("New Roman", Font.BOLD, 20)));
        elementList.add(new Rectangle(Color.YELLOW, x, y, 100, 40, true, Color.GREEN));
        elementList.add(new Line(Color.BLACK, x - 20, y + 20, x, y + 20));
        elementList.add(new Line(Color.BLACK, x + 100, y + 20, x + 100 + 20, y + 20));
        pinList.add(new Pin(x - 20, y + 20, this));
        pinList.add(new Pin(x + 100 + 20, y + 20, this));
    }

    @SuppressWarnings("unused")
    public static Resistance getResistance() {
        return loadComplexElement(ElementType.RESISTANCE, Resistance.class);
    }
}
