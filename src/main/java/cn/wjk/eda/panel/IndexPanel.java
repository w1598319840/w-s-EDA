package cn.wjk.eda.panel;

import cn.wjk.eda.element.Rectangle;
import cn.wjk.eda.element.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
public class IndexPanel extends JPanel implements Runnable, MouseMotionListener, MouseListener, KeyListener {
    public static List<Element> elements = new ArrayList<>();
    private Element selectedElement;
    private static final double MAX_DISTANCE = 100;

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

    @SuppressWarnings("unused")
    private void createNewLine() {
        elements.add(new Line(Color.BLACK, 10, 10, 100, 100));
    }

    @SuppressWarnings("unused")
    private void createNewRectangle() {
        elements.add(new Rectangle(Color.blue, 10, 10, 100, 100, true, Color.green));
    }

    @SuppressWarnings("unused")
    private void createNewText() {
        if (ElementLibraryPanel.content == null || ElementLibraryPanel.content.isBlank()) {
            return;
        }
        elements.add(new Text(Color.black, 10, 10, ElementLibraryPanel.content,
                new Font("Times New Roman", Font.PLAIN, 20)));
        ElementLibraryPanel.content = null;
    }

    @SuppressWarnings("unused")
    private void createNewResistance() {
        elements.add(new Resistance(100, 100));
    }

    @SuppressWarnings("unused")
    private void createNewCapacitance() {
        elements.add(new Capacitance(100, 100));
    }

    @SuppressWarnings("unused")
    private void createNewInductance() {
        elements.add(new Inductance(100, 100));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selectedElement != null) {
            selectedElement.moveWithMetaData(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private Element selectTheNearestComponent(int x, int y) {
        double minDistance = Integer.MAX_VALUE;
        Element nearestElement = null;
        for (Element element : elements) {
            int metaX = element.getMetaX();
            int metaY = element.getMetaY();
            double distance = getDistance(x, y, metaX, metaY);
            if (distance > MAX_DISTANCE) {
                continue;
            }
            if (distance < minDistance) {
                nearestElement = element;
                minDistance = distance;
            }
        }
        return nearestElement;
    }

    private double getDistance(int x1, int y1, int x2, int y2) {
        int x = Math.abs(x1 - x2);
        int y = Math.abs(y1 - y2);
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int startX = e.getX();
        int startY = e.getY();
        selectedElement = selectTheNearestComponent(startX, startY);
        if (selectedElement != null) {
            selectedElement.setStartX(startX);
            selectedElement.setStartY(startY);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            elements.remove(selectedElement);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
