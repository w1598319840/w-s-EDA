package cn.wjk.eda.panel;

import cn.wjk.eda.element.Element;
import cn.wjk.eda.element.Line;
import cn.wjk.eda.element.Rectangle;
import cn.wjk.eda.enumration.ElementType;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: cn.wjk.eda.view.panel
 * @ClassName: IndexPanel
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/10 20:28
 * @Description:
 */
@SuppressWarnings("unused")
public class IndexPanel extends JPanel implements Runnable {
    public static final List<Element> elements = new ArrayList<>();

    @Override
    @SuppressWarnings("all")
    public void run() {
        while (true) {
            repaint(30);
            coreMethod();
        }
    }

    private void coreMethod() {
        handleNewElement();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Element element : elements) {
            element.paint(g);
        }
    }

    private void handleNewElement() {
        if (!ElementLibraryPanel.modified) {
            return;
        }
        if (ElementLibraryPanel.elementType == null) {
            return;
        }
        try {
            String methodName = "createNew" + ElementLibraryPanel.elementType.getName();
            Method method = this.getClass().getDeclaredMethod(methodName);
            method.invoke(this);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        ElementLibraryPanel.elementType = null;
        ElementLibraryPanel.modified = false;
    }

    private void createNewLine() {
        elements.add(new Line(ElementType.LINE, Color.BLACK, 10, 10, 100, 100));
    }

    private void createNewRectangle() {
        elements.add(new Rectangle(ElementType.RECTANGLE, Color.blue,
                10, 10, 100, 100, true, Color.green));
    }
}
