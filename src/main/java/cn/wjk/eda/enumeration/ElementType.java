package cn.wjk.eda.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Package: cn.wjk.eda.enumeration
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
    POLYLINE("Polyline", "折线"),
    ARC("Arc", "圆弧"),
    PIN("Pin", "引脚"),
    RESISTANCE("Resistance", "电阻"),
    CAPACITANCE("Capacitance", "电容"),
    INDUCTANCE("Inductance", "电感"),
    WIRE("Wire", "导线");

    private final String name;
    private final String desc;
}
