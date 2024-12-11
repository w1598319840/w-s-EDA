package cn.wjk.eda.panel;

import cn.wjk.eda.element.Rectangle;
import cn.wjk.eda.element.*;

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
        elements.add(new Line(Color.BLACK, 10, 10, 100, 100));
    }

    private void createNewRectangle() {
        elements.add(new Rectangle(Color.blue, 10, 10, 100, 100, true, Color.green));
    }

    private void createNewText() {
        if (ElementLibraryPanel.content == null || ElementLibraryPanel.content.isBlank()) {
            return;
        }
        elements.add(new Text(Color.black, 10, 10, ElementLibraryPanel.content,
                new Font("Times New Roman", Font.PLAIN, 20)));
        ElementLibraryPanel.content = null;
    }

    private void createNewResistance() {
        elements.add(new Resistance(100, 100));
    }
}
