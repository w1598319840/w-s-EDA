package cn.wjk.eda.enumeration;

import cn.wjk.eda.element.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;

import static cn.wjk.eda.view.panel.IndexPanel.DEFAULT_FONT;

/**
 * @Package: cn.wjk.eda.enumeration
 * @ClassName: LinkType
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/17 20:08
 * @Description:
 */
@AllArgsConstructor
@Getter
public enum LinkType {
    NOT_SELECTED(0, "selected 0",
            new Text(Color.BLACK, 1000, 650, "link status: selected 0",
                    new Font(DEFAULT_FONT, Font.BOLD, 16))),
    SELECTED_ONE(1, "selected 1",
            new Text(Color.BLACK, 1000, 650, "link status: selected 1",
                    new Font(DEFAULT_FONT, Font.BOLD, 16))),

    DISABLED(2, "disabled", new Text(Color.BLACK, 1000, 650, "link status: disabled",
            new Font(DEFAULT_FONT, Font.BOLD, 16)));

    private final int status;
    private final String description;
    private final Text text;
}
