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
        jTextField.setBounds(270, 90, 120, 30);
        jTextField.setText("Input text here");
        add(jTextField);
        fieldMap.put(ElementType.TEXT, jTextField);
    }

    private void initButtons() {
        initBasicElementButton();
        initComplexElementButton();
    }

    private void initComplexElementButton() {
        initResistanceButton();
        initCapacitanceButton();
        initInductanceButton();
    }

    private void initBasicElementButton() {
        initLineButton();
        initRectangleButton();
        initTextButton();
    }

    private void initInductanceButton() {
        JButton inductanceButton = new JButton("Inductance");
        inductanceButton.setBounds(10, 90, 120, 30);
        inductanceButton.addActionListener(this);
        add(inductanceButton);
    }

    private void initCapacitanceButton() {
        JButton capacitanceButton = new JButton("Capacitance");
        capacitanceButton.setBounds(10, 50, 120, 30);
        capacitanceButton.addActionListener(this);
        add(capacitanceButton);
    }

    private void initResistanceButton() {
        JButton resistanceButton = new JButton("Resistance");
        resistanceButton.setBounds(10, 10, 120, 30);
        resistanceButton.addActionListener(this);
        add(resistanceButton);
    }

    private void initTextButton() {
        JButton textButton = new JButton("Text");
        textButton.setBounds(140, 90, 120, 30);
        textButton.addActionListener(this);
        add(textButton);
    }

    private void initRectangleButton() {
        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.setBounds(140, 50, 120, 30);
        rectangleButton.addActionListener(this);
        add(rectangleButton);
    }

    private void initLineButton() {
        JButton lineButton = new JButton("Line");
        lineButton.setBounds(140, 10, 120, 30);
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

    private void handleCapacitance() {
        elementType = ElementType.CAPACITANCE;
    }

    private void handleInductance() {
        elementType = ElementType.INDUCTANCE;
    }
}