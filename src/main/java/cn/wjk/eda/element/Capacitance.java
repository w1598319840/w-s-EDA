package cn.wjk.eda.element;

import cn.wjk.eda.enumration.ElementType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: cn.wjk.eda.element
 * @ClassName: Capacitance
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/13 12:08
 * @Description:
 */
public class Capacitance extends Element {
    private final List<Element> elementList = new ArrayList<>();
    private final List<Pin> pinList = new ArrayList<>();

    public Capacitance(int x, int y) {
        super(ElementType.CAPACITANCE, null, x, y);
        elementList.add(new Line(Color.BLACK, x, y, x, y + 50));
        elementList.add(new Line(Color.BLACK, x + 20, y, x + 20, y + 50));
        elementList.add(new Line(Color.BLACK, x - 30, y + 25, x, y + 25));
        elementList.add(new Line(Color.BLACK, x + 20, y + 25, x + 20 + 30, y + 25));
        pinList.add(new Pin(x - 30, y + 25));
        pinList.add(new Pin(x + 20 + 30, y + 25));
        elementList.add(new Text(Color.BLACK, x - 10, y + 70, this.getType().getDesc(),
                new Font("New Roman", Font.BOLD, 20)));
    }

    @Override
    public void paint(Graphics g) {
        for (Element element : elementList) {
            element.paint(g);
        }
        for (Pin pin : pinList) {
            pin.paint(g);
        }
    }
}
