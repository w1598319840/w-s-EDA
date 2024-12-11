package cn.wjk.eda.panel;

import cn.wjk.eda.enumration.ElementType;
import cn.wjk.eda.frame.ElementLibraryFrame;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: cn.wjk.eda.view.panel
 * @ClassName: ElementLibraryPanel
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/10 20:52
 * @Description:
 */

@Setter
@SuppressWarnings("unused")
public class ElementLibraryPanel extends JPanel implements Runnable, ActionListener {
    private ElementLibraryFrame elementLibraryFrame;
    private final Map<ElementType, JTextField> fieldMap = new HashMap<>();
    public static ElementType elementType;
    public static boolean modified;
    public static String content;

    public ElementLibraryPanel() {
        setLayout(null);
        initButtons();
        initText();
    }

    private void initText() {
        initTextText();
    }

    private void initTextText() {
        JTextField jTextField = new JTextField(20);
        jTextField.setEditable(true);
        jTextField.setBounds(120, 90, 100, 30);
        add(jTextField);
        fieldMap.put(ElementType.TEXT, jTextField);
    }

    private void initButtons() {
        initLineButton();
        initRectangleButton();
        initTextButton();
        initResistanceButton();
    }

    private void initResistanceButton() {
        JButton resistanceButton = new JButton("Resistance");
        resistanceButton.setBounds(10, 130, 100, 30);
        resistanceButton.addActionListener(this);
        add(resistanceButton);
    }

    private void initTextButton() {
        JButton textButton = new JButton("Text");
        textButton.setBounds(10, 90, 100, 30);
        textButton.addActionListener(this);
        add(textButton);
    }

    private void initRectangleButton() {
        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.setBounds(10, 50, 100, 30);
        rectangleButton.addActionListener(this);
        add(rectangleButton);
    }

    private void initLineButton() {
        JButton lineButton = new JButton("Line");
        lineButton.setBounds(10, 10, 100, 30);
        lineButton.addActionListener(this);
        add(lineButton);
    }

    @SuppressWarnings("all")
    @Override
    public void run() {
        while (true) {
            repaint(30);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String methodName = "handle" + e.getActionCommand();
            Method method = this.getClass().getDeclaredMethod(methodName);
            method.invoke(this);
            modified = true;
            this.elementLibraryFrame.dispose();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void handleLine() {
        elementType = ElementType.LINE;
    }

    private void handleRectangle() {
        elementType = ElementType.RECTANGLE;
    }

    private void handleText() {
        elementType = ElementType.TEXT;
        content = fieldMap.get(ElementType.TEXT).getText();
    }

    private void handleResistance() {
        elementType = ElementType.RESISTANCE;
    }
}