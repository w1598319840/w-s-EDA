package cn.wjk.eda.entity.eda;

import cn.wjk.eda.entity.element.Element;
import cn.wjk.eda.entity.element.Wire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Package: cn.wjk.eda.entity.eda
 * @ClassName: EDAEntity
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/17 21:49
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EDAEntity implements Serializable {
    private List<Element> elements;
    private List<Wire> wires;
}
