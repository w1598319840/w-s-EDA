package cn.wjk.eda.view.frame;

import cn.wjk.eda.view.panel.IndexPanel;
import cn.wjk.eda.utils.ByteArrayUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
        menuBar.add(createElementMenu());
        setJMenuBar(menuBar);
    }

    private JMenu createElementMenu() {
        JMenu elementMenu = new JMenu("Element");
        JMenuItem addElementItem = new JMenuItem("Add Element");
        addElementItem.addActionListener(this);
        elementMenu.add(addElementItem);
        return elementMenu;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(saveMenuItem);
        saveMenuItem.addActionListener(this);

        JMenuItem importMenuItem = new JMenuItem("Import");
        fileMenu.add(importMenuItem);
        importMenuItem.addActionListener(this);
        return fileMenu;
    }

    private void initPanel() {
        IndexPanel indexPanel = new IndexPanel();
        add(indexPanel);
        addMouseMotionListener(indexPanel);
        addMouseListener(indexPanel);
        addKeyListener(indexPanel);
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

    @SuppressWarnings("unused")
    private void handleAddElement() {
        new ElementLibraryFrame("ElementLibrary");
    }

    @SuppressWarnings("unused")
    private void handleSave() {
        File file = chooseFile(true);
        if (file == null) {
            return;
        }
        String elementsString = JSON.toJSONString(ByteArrayUtils.toByteArray(IndexPanel.elements));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(elementsString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unused")
    private void handleImport() {
        File file = chooseFile(false);
        if (file == null) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            char[] buffer = new char[1024 * 1024 * 10];
            int length;
            StringBuilder stringBuilder = new StringBuilder();
            while ((length = reader.read(buffer)) != -1) {
                stringBuilder.append(buffer, 0, length);
            }
            IndexPanel.elements = ByteArrayUtils.toObject(JSON.parseObject(stringBuilder.toString(), byte[].class),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File chooseFile(boolean flag) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("select a file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (flag) {
            fileChooser.setApproveButtonText("保存");
        }
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
}
