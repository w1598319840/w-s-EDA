package cn.wjk.eda.enumration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Package: cn.wjk.eda.enumration
 * @ClassName: ElementType
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/10 21:17
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum ElementType {
    LINE("Line", "线"),
    RECTANGLE("Rectangle", "矩形"),
    TEXT("Text", "文本"),
    PIN("Pin", "引脚"),
    RESISTANCE("Resistance", "电阻");

    private final String name;
    private final String desc;
}
