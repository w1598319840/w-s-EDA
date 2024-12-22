package cn.wjk.eda.view.frame;

import cn.wjk.eda.entity.element.ComplexElement;
import cn.wjk.eda.view.panel.ElementInfoPanel;

import javax.swing.*;

/**
 * @Package: cn.wjk.eda.view.frame
 * @ClassName: ElementInfoFrame
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/22 12:08
 * @Description:
 */
public class ElementInfoFrame extends JFrame {
    public ElementInfoFrame(String title, ComplexElement complexElement) {
        super(title);
        setBounds(500, 200, 900, 600);
        initPanel(complexElement);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initPanel(ComplexElement complexElement) {
        ElementInfoPanel elementInfoPanel = new ElementInfoPanel(complexElement);
        add(elementInfoPanel);
        new Thread(elementInfoPanel).start();
    }
}
