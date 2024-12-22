package cn.wjk.eda.view.frame;

import cn.wjk.eda.view.panel.ElementLibraryPanel;

import javax.swing.*;

/**
 * @Package: cn.wjk.entity.view.frame
 * @ClassName: ElementLibraryFrame
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/10 20:49
 * @Description:
 */
public class ElementLibraryFrame extends JFrame {
    public ElementLibraryFrame(String title) {
        super(title);
        setBounds(500, 200, 900, 600);
        initPanel();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initPanel() {
        ElementLibraryPanel elementLibraryPanel = new ElementLibraryPanel();
        elementLibraryPanel.setElementLibraryFrame(this);
        add(elementLibraryPanel);
        new Thread(elementLibraryPanel).start();
    }
}
