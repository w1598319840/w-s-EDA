package cn.wjk.eda.view.panel;

import cn.wjk.eda.entity.element.ComplexElement;
import cn.wjk.eda.entity.element.Pin;
import cn.wjk.eda.entity.entity.Point;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: cn.wjk.eda.view.panel
 * @ClassName: ElementInfoPanel
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/22 12:08
 * @Description:
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ElementInfoPanel extends JPanel implements Runnable, ActionListener {
    private final JFrame frame;
    private final ComplexElement complexElement;
    private final JButton addButton = new JButton("+");
    private JTextField nameTextField;
    private JTextField xTextField;
    private JTextField yTextField;
    private int count = -1;
    private final Map<JTextField, JTextField> otherAttrsMap = new HashMap<>();

    public ElementInfoPanel(ComplexElement complexElement, JFrame frame) {
        this.complexElement = complexElement;
        this.frame = frame;
        setLayout(null);
        loadOtherAttrs();
        loadElementAttribute();
        initButton();
        complexElement.setStartX(complexElement.getMetaX());
        complexElement.setStartY(complexElement.getMetaY());
    }

    private void initButton() {
        addButton.addActionListener(this);
        add(addButton);

        JButton confirmButton = new JButton("confirm");
        confirmButton.setBounds(770, 510, 90, 40);
        confirmButton.addActionListener(this);
        add(confirmButton);
    }

    private void loadElementAttribute() {
        loadCommonAttrs();
        loadPinAttrs();
        refreshAddButton();
    }

    private void loadOtherAttrs() {
        Map<String, String> info = complexElement.getInfo();
        for (Map.Entry<String, String> entry : info.entrySet()) {
            JTextField key = new JTextField();
            JTextField value = new JTextField();
            key.setText(entry.getKey());
            value.setText(entry.getValue());
            otherAttrsMap.put(key, value);
            add(key);
            add(value);
            count++;
            key.setBounds(10, 170 + count * 30, 60, 30);
            value.setBounds(80, 170 + count * 30, 100, 30);
        }
    }

    private void refreshAddButton() {
        addButton.setBounds(10, 170 + ++count * 30, 45, 30);
    }

    private void loadPinAttrs() {
        List<Pin> pinList = complexElement.getPinList();
        for (int i = 0; i < pinList.size(); i++) {
            Pin pin = pinList.get(i);
            JLabel pinLabel = new JLabel("pin" + (i + 1));
            pinLabel.setBounds(200, 10 + i * 30, 30, 30);
            JLabel linkedLabel = new JLabel(pin.isLinked() ? "linked" : "unlinked");
            linkedLabel.setBounds(240, 10 + i * 30, 100, 30);
            add(pinLabel);
            add(linkedLabel);
        }
    }

    private void loadCommonAttrs() {
        JLabel idLabel = new JLabel("id");
        JLabel idInfoLabel = new JLabel(String.valueOf(complexElement.getId()));
        add(idLabel);
        add(idInfoLabel);
        idLabel.setBounds(10, 10, 60, 30);
        idInfoLabel.setBounds(80, 10, 100, 30);

        JLabel nameLabel = new JLabel("name");
        nameTextField = new JTextField(complexElement.getName().getText());
        add(nameLabel);
        add(nameTextField);
        nameLabel.setBounds(10, 40, 60, 30);
        nameTextField.setBounds(80, 40, 100, 30);

        JLabel xLabel = new JLabel("x");
        xTextField = new JTextField(String.valueOf(complexElement.getMetaX()));
        add(xLabel);
        add(xTextField);
        xLabel.setBounds(10, 70, 60, 30);
        xTextField.setBounds(80, 70, 100, 30);

        JLabel yLabel = new JLabel("y");
        yTextField = new JTextField(String.valueOf(complexElement.getMetaY()));
        add(yLabel);
        add(yTextField);
        yLabel.setBounds(10, 100, 60, 30);
        yTextField.setBounds(80, 100, 100, 30);

        JLabel typeLabel = new JLabel("type");
        JLabel typeInfoLabel = new JLabel(complexElement.getType().getName());
        add(typeLabel);
        add(typeInfoLabel);
        typeLabel.setBounds(10, 130, 60, 30);
        typeInfoLabel.setBounds(80, 130, 100, 30);
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
        if (e.getSource() == addButton) {
            handleAdd();
        } else if (e.getActionCommand().equals("confirm")) {
            handleConfirm();
        }
    }

    private void handleConfirm() {
        Map<String, String> info = complexElement.getInfo();
        info.clear();
        for (Map.Entry<JTextField, JTextField> entry : otherAttrsMap.entrySet()) {
            info.put(entry.getKey().getText(), entry.getValue().getText());
        }

        complexElement.setName(nameTextField.getText());
        Point point = IndexPanel.gridAlign(Integer.parseInt(xTextField.getText()), Integer.parseInt(yTextField.getText()));
        if (point.getX() < 0) {
            point.setX(0);
        }
        if (point.getY() < 0) {
            point.setY(0);
        }
        complexElement.setMetaX(point.getX());
        complexElement.setMetaY(point.getY());

        complexElement.refresh();
        this.frame.dispose();
    }

    private void handleAdd() {
        JTextField key = new JTextField();
        JTextField value = new JTextField();
        key.setBounds(10, 170 + count * 30, 60, 30);
        value.setBounds(80, 170 + count * 30, 100, 30);
        add(key);
        add(value);
        otherAttrsMap.put(key, value);
        refreshAddButton();
    }
}
