package cn.wjk.eda.entity.element;

import cn.wjk.eda.enumeration.ElementType;
import cn.wjk.eda.utils.GlobalIdUtils;
import com.alibaba.fastjson2.JSON;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: cn.wjk.entity.element
 * @ClassName: ComplexElement
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/13 12:24
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ComplexElement extends Element {
    protected final List<Element> elementList = new ArrayList<>();
    protected final List<Pin> pinList = new ArrayList<>();
    protected final Map<String, String> info = new HashMap<>();
    protected final Text name;

    public ComplexElement(ElementType type, int metaX, int metaY, Text name) {
        super(type, metaX, metaY);
        this.name = name;
    }

    @Override
    public void paint(Graphics g) {
        for (Element element : elementList) {
            element.paint(g);
        }
        for (Pin pin : pinList) {
            pin.paint(g);
        }
        name.paint(g);
    }

    @Override
    public void move(int currentX, int currentY) {
        for (Element element : elementList) {
            element.startX = startX;
            element.startY = startY;
            element.move(currentX, currentY);
        }
        for (Pin pin : pinList) {
            pin.startX = startX;
            pin.startY = startY;
            pin.move(currentX, currentY);
        }
        name.startX = startX;
        name.startY = startY;
        name.move(currentX, currentY);
    }

    protected static <T extends ComplexElement> T loadComplexElement(ElementType type, Class<T> clazz) {
        URL url = ComplexElement.class.getResource("/");
        if (url == null) {
            return null;
        }
        String filePath = url.getPath() + "\\elements\\" + type.getName() + ".json";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            char[] buffer = new char[1024 * 1024 * 10];
            int length;
            StringBuilder stringBuilder = new StringBuilder();
            while ((length = reader.read(buffer)) != -1) {
                stringBuilder.append(buffer, 0, length);
            }
            T complexElement = JSON.parseObject(stringBuilder.toString(), clazz);
            complexElement.setId(GlobalIdUtils.generateGlobalId());
            for (Pin pin : complexElement.pinList) {
                pin.setId(GlobalIdUtils.generateGlobalId());
                pin.setOwner(complexElement);
            }
            for (Element element : complexElement.elementList) {
                element.setId(GlobalIdUtils.generateGlobalId());
            }
            return complexElement;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void refresh() {
        move(metaX, metaY);
    }

    public void setName(String text) {
        this.name.setText(text);
    }
}