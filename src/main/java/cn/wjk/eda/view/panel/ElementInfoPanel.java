package cn.wjk.eda.view.panel;

import cn.wjk.eda.entity.element.ComplexElement;
import cn.wjk.eda.entity.element.Pin;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.util.List;

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
public class ElementInfoPanel extends JPanel implements Runnable {
    private ComplexElement complexElement;

    public ElementInfoPanel(ComplexElement complexElement) {
        this.complexElement = complexElement;
        setLayout(null);
        loadElementAttribute();
    }

    private void loadElementAttribute() {
        loadCommonAttrs();
        loadPinAttrs();
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
        JTextField nameTextField = new JTextField(complexElement.getName());
        add(nameLabel);
        add(nameTextField);
        nameLabel.setBounds(10, 40, 60, 30);
        nameTextField.setBounds(80, 40, 100, 30);

        JLabel xLabel = new JLabel("x");
        JTextField xTextField = new JTextField(String.valueOf(complexElement.getMetaX()));
        add(xLabel);
        add(xTextField);
        xLabel.setBounds(10, 70, 60, 30);
        xTextField.setBounds(80, 70, 100, 30);

        JLabel yLabel = new JLabel("y");
        JTextField yTextField = new JTextField(String.valueOf(complexElement.getMetaY()));
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
}
