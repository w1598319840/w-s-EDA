package cn.wjk.eda.view.panel;

import cn.wjk.eda.entity.element.Rectangle;
import cn.wjk.eda.entity.element.*;
import cn.wjk.eda.entity.entity.Point;
import cn.wjk.eda.enumeration.LinkType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: cn.wjk.entity.view.panel
 * @ClassName: IndexPanel
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/10 20:28
 * @Description:
 */
public class IndexPanel extends JPanel implements Runnable, MouseMotionListener, MouseListener, KeyListener {
    public static final String DEFAULT_FONT = "Times New Roman";
    private static final int GRID_SIZE = 20;
    private static final float GRID_WIDTH = 0.5f;
    public static List<Element> elements = new ArrayList<>();
    public static List<Wire> wires = new ArrayList<>();
    private Element selectedElement;
    private Element lastSelectedElement;
    private Wire selectedWire;
    private static final double MAX_DISTANCE = 100;
    private LinkType linkType = LinkType.DISABLED;

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
        paintGrid(g);
        paintUserElements(g);
    }

    private void paintGrid(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(GRID_WIDTH));

        // 绘制垂直栅格线
        for (int x = 0; x < width; x += GRID_SIZE) {
            g2d.drawLine(x, 0, x, height);
        }

        // 绘制水平栅格线
        for (int y = 0; y < height; y += GRID_SIZE) {
            g2d.drawLine(0, y, width, y);
        }
    }

    private void paintUserElements(Graphics g) {
        for (Element element : elements) {
            element.paint(g);
        }
        for (Wire wire : wires) {
            wire.paint(g);
        }
        if (linkType != LinkType.DISABLED) {
            linkType.getText().paint(g);
        }
    }

    private void handleNewElement() {
        if (!ElementLibraryPanel.modified || ElementLibraryPanel.elementType == null) {
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
        elements.add(new Text(Color.black, 10, 10, ElementLibraryPanel.content, new Font(DEFAULT_FONT, Font.PLAIN, 20)));
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
        int x = e.getX();
        int y = e.getY();
        if (linkType == LinkType.DISABLED) {
            moveElement(x, y);
        }
    }

    /**
     * 未连接的wire的另一端跟随鼠标
     * @param x 鼠标x
     * @param y 鼠标y
     */
    private void unlinkWireFollow(int x, int y) {
        selectedWire.move(x, y);
    }

    private void moveElement(int x, int y) {
        if (selectedElement == null) {
            return;
        }
        if (!(selectedElement instanceof Wire)) {
            Point point = gridAlign(x, y);
            selectedElement.moveWithMetaData(point.getX(), point.getY());
            for (Wire wire : wires) {
                if ((wire.getPin1().getOwner() == selectedElement && wire.getPin1().isLinked()) ||
                        (wire.getPin2().getOwner() == selectedElement && wire.getPin2().isLinked())) {
                    wire.move();
                }
            }
        } else {
            selectedElement.moveWithMetaData(x, y);
        }
    }

    private Point gridAlign(int x, int y) {
        int nearestX = (int) Math.round((double) x / GRID_SIZE) * GRID_SIZE;
        int nearestY = (int) Math.round((double) y / GRID_SIZE) * GRID_SIZE;

        return new Point(nearestX, nearestY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (linkType == LinkType.SELECTED_ONE) {
            unlinkWireFollow(x, y - 50);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private Element selectTheNearestElement(int x, int y) {
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
        for (Wire wire : wires) {
            int metaX = wire.getMetaX();
            int metaY = wire.getMetaY();
            double distance = getDistance(x, y, metaX, metaY);
            if (distance > MAX_DISTANCE) {
                continue;
            }
            if (distance < minDistance) {
                nearestElement = wire;
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
        if (linkType == LinkType.DISABLED) {
            Point point = gridAlign(startX, startY);
            select(point.getX(), point.getY());
        } else {
            link(startX, startY - 50);
        }
    }

    private void select(int startX, int startY) {
        selectedElement = selectTheNearestElement(startX, startY);
        lastSelectedElement = selectedElement;
        if (selectedElement == null) {
            return;
        }
        selectedElement.setStartX(startX);
        selectedElement.setStartY(startY);
    }

    private void link(int x, int y) {
        Pin pin = selectTheNearestPin(x, y);
        if (pin == null) {
            return;
        }
        if (pin.isLinked()) {
            if (selectedWire != null && selectedWire.getPin1() != pin && selectedWire.getPin2() != pin) {
                return;
            }
            pin.setLinked(false);
            if (linkType == LinkType.SELECTED_ONE) {
                pin.setLinked(false);
                wires.remove(selectedWire);
                selectedWire = null;
                linkType = LinkType.DISABLED;
            } else {
                Pin otherPin = null;
                for (Wire wire : wires) {
                    if (wire.getPin1() == pin) {
                        selectedWire = wire;
                        otherPin = wire.getPin2();
                        break;
                    }
                    if (wire.getPin2() == pin) {
                        selectedWire = wire;
                        otherPin = wire.getPin1();
                        break;
                    }
                }
                if (otherPin != null) {
                    wires.remove(selectedWire);
                    selectedWire = new Wire(otherPin, x, y);
                    wires.add(selectedWire);
                    pin.setLinked(false);
                    linkType = LinkType.SELECTED_ONE;
                }
            }
        } else {
            pin.setLinked(true);
            if (linkType == LinkType.NOT_SELECTED) {
                selectedWire = new Wire(pin, x, y);
                wires.add(selectedWire);
                linkType = LinkType.SELECTED_ONE;
            } else if (linkType == LinkType.SELECTED_ONE) {
                selectedWire.enable(pin);
                linkType = LinkType.DISABLED;
                selectedWire = null;
            }
        }
    }

    private Pin selectTheNearestPin(int x, int y) {
        for (Element element : elements) {
            if (element instanceof ComplexElement) {
                List<Pin> pinList = ((ComplexElement) element).getPinList();
                for (Pin pin : pinList) {
                    if (pin.selected(x, y)) {
                        return pin;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        selectedElement = null;
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
            pressDelete();
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            pressW();
        }
    }

    private void pressW() {
        if (linkType == LinkType.DISABLED) {
            linkType = LinkType.NOT_SELECTED;
        } else {
            if (linkType == LinkType.SELECTED_ONE) {
                wires.remove(selectedWire);
                selectedWire.getPin1().setLinked(false);
                selectedWire = null;
            }
            linkType = LinkType.DISABLED;
        }
    }

    private void pressDelete() {
        if (lastSelectedElement != null) {
            if (lastSelectedElement instanceof Wire wire) {
                wire.getPin1().setLinked(false);
                wire.getPin2().setLinked(false);
                wires.remove(lastSelectedElement);
            } else {
                elements.remove(lastSelectedElement);
                List<Wire> deleteWires = new ArrayList<>();
                for (Wire wire : wires) {
                    if (wire.getPin1() != null && wire.getPin1().getOwner() == lastSelectedElement) {
                        if (wire.getPin2() != null) {
                            wire.getPin2().setLinked(false);
                        }
                        deleteWires.add(wire);
                    }
                    if (wire.getPin2() != null && wire.getPin2().getOwner() == lastSelectedElement) {
                        if (wire.getPin1() != null) {
                            wire.getPin1().setLinked(false);
                        }
                        deleteWires.add(wire);
                    }
                }
                wires.removeAll(deleteWires);
            }
            lastSelectedElement = null;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
