package cn.wjk.eda.frame;

import cn.wjk.eda.panel.IndexPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Package: cn.wjk.eda.view
 * @ClassName: IndexFrame
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/10 20:24
 * @Description:
 */
@SuppressWarnings("unused")
public class IndexFrame extends JFrame implements ActionListener {
    public IndexFrame(String title) {
        super(title);
        initFrame();
        initPanel();
        initMenuBar();
    }

    private void initFrame() {
        setBounds(200, 80, 1300, 750);
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createElement());
        setJMenuBar(menuBar);
    }

    private JMenu createElement() {
        JMenu elementMenu = new JMenu("Element");
        JMenuItem addElement = new JMenuItem("Add Element");
        addElement.addActionListener(this);
        elementMenu.add(addElement);
        return elementMenu;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem importMenuItem = new JMenuItem("Import");
        JMenuItem exportMenuItem = new JMenuItem("Export");
        fileMenu.add(saveMenuItem);
        fileMenu.add(importMenuItem);
        fileMenu.add(exportMenuItem);
        return fileMenu;
    }

    private void initPanel() {
        IndexPanel indexPanel = new IndexPanel();
        add(indexPanel);
        new Thread(indexPanel).start();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String methodName = "handle" + e.getActionCommand().replaceAll(" ", "");
            Method method = this.getClass().getDeclaredMethod(methodName);
            method.invoke(this);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void handleAddElement() {
        new ElementLibraryFrame("ElementLibrary");
    }
}
