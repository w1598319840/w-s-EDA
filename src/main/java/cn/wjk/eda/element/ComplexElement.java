package cn.wjk.eda.element;

import cn.wjk.eda.enumration.ElementType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: cn.wjk.eda.element
 * @ClassName: ComplexElement
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/13 12:24
 * @Description:
 */
public class ComplexElement extends Element {
    protected final java.util.List<Element> elementList = new ArrayList<>();
    protected final List<Pin> pinList = new ArrayList<>();

    public ComplexElement(ElementType type, int metaX, int metaY) {
        super(type, metaX, metaY);
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