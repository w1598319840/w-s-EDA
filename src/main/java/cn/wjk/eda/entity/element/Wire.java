package cn.wjk.eda.entity.element;

import cn.wjk.eda.enumeration.ElementType;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @Package: cn.wjk.eda.element
 * @ClassName: Wire
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/17 19:39
 * @Description:
 */
@Getter
@Setter
public class Wire extends Element {
    private boolean enabled;
    private Pin pin1, pin2;
    private int mouseX, mouseY;
    private Polyline polyline;

    public Wire(Pin pin1, int mouseX, int mouseY) {
        super(ElementType.WIRE, null);
        this.pin1 = pin1;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.polyline = generatePolyLine();
    }

    private Polyline generatePolyLine() {
        int x1 = pin1.getMetaX();
        int y1 = pin1.getMetaY();
        int x2 = enabled ? pin2.getMetaX() : mouseX;
        int y2 = enabled ? pin2.getMetaY() : mouseY;
        int[] xPoints = new int[4];
        int[] yPoints = new int[4];
        xPoints[0] = x1;
        yPoints[0] = y1;
        xPoints[3] = x2;
        yPoints[3] = y2;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        if (dx > dy) {
            xPoints[1] = (x1 + x2) / 2;
            yPoints[1] = y1;
            xPoints[2] = (x1 + x2) / 2;
            yPoints[2] = y2;
        } else {
            xPoints[1] = x1;
            yPoints[1] = (y1 + y2) / 2;
            xPoints[2] = x2;
            yPoints[2] = (y1 + y2) / 2;
        }

        return new Polyline(enabled ? Color.GREEN : Color.RED, xPoints, yPoints);
    }

    @Override
    public void paint(Graphics g) {
        polyline.paint(g);
    }

    @Override
    public void move(int currentX, int currentY) {
        mouseX = currentX;
        mouseY = currentY;
        this.polyline = generatePolyLine();
    }

    public void move() {
        this.polyline = generatePolyLine();
    }

    public void enable(Pin pin2) {
        this.pin2 = pin2;
        this.enabled = true;
        this.polyline = generatePolyLine();
    }
}