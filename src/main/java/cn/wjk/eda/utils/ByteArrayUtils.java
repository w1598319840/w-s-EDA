package cn.wjk.eda.utils;

import java.io.*;

/**
 * @Package: cn.wjk.eda.utils
 * @ClassName: ByteArrayUtils
 * @Version: 1.0
 * @Author: 温嘉凯
 * @Datetime: 2024/12/12 12:48
 * @Description:
 */
@SuppressWarnings("all")
public class ByteArrayUtils {
    /**
     * 对象转数组
     *
     * @param obj 传入对象
     * @return byte[]数组
     */
    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * 数组转对象
     */
    public static <T> T toObject(byte[] bytes, Class<T> clazz) {
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (T) obj;
    }
}
