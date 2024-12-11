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
    LINE("Line"),
    RECTANGLE("Rectangle");

    private final String name;
}
