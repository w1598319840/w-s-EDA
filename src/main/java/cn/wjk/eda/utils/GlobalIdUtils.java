package cn.wjk.eda.utils;

import java.util.concurrent.atomic.LongAdder;

/**
 * @Package: cn.wjk.entity.utils
 * @ClassName: GlobalIdUtils
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/11 12:56
 * @Description:
 */
public class GlobalIdUtils {
    private static final LongAdder longAdder = new LongAdder();

    public static long generateGlobalId() {
        longAdder.increment();
        return longAdder.longValue();
    }

    public static void init(long val){
        longAdder.reset();
        longAdder.add(val);
    }
}
