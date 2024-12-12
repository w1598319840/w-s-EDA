package cn.wjk.eda.element;


import cn.wjk.eda.enumration.ElementType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: cn.wjk.eda.element
 * @ClassName: Resistance
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/11 12:52
 * @Description:
 */
public class Resistance extends Element {
    private final List<Element> elementList = new ArrayList<>();

    public Resistance(int x, int y) {
        super(ElementType.RESISTANCE, null, x, y);
        elementList.add(new Rectangle(Color.YELLOW, x, y, 100, 50, true, Color.GREEN));
        elementList.add(new Line(Color.BLACK, x - 20, y + 25, x, y + 25));
        elementList.add(new Line(Color.BLACK, x + 100, y + 25, x + 100 + 20, y + 25));
        elementList.add(new Pin(x - 20, y + 25));
        elementList.add(new Pin(x + 100 + 20, y + 25));
        elementList.add(new Text(Color.BLACK, x + 30, y + 70, ElementType.RESISTANCE.getDesc(),
                new Font("New Roman", Font.BOLD, 20)));
    }

    @Override
    public void paint(Graphics g) {
        for (Element element : elementList) {
            element.paint(g);
        }
    }
}
