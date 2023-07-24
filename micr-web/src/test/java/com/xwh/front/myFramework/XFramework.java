package com.xwh.front.myFramework;

import com.xwh.front.myAnnoation.Value;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者:陈方银
 * 时间:2023/7/24
 */
public class XFramework {

    public <T> T getBean(Class<T> tClass) {
        T t = null;
        try {
            Constructor<T> constructor = tClass.getDeclaredConstructor();
            Field[] fields = tClass.getDeclaredFields();
            t = constructor.newInstance();
            for (Field field : fields) {
                Value value = field.getDeclaredAnnotation(Value.class);
                String xxx = value.value();
                String setMethod = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                Class<?> type = field.getType();
                switch (type.getSimpleName()) {
                    case "String": {
                        Method method = tClass.getDeclaredMethod(setMethod, String.class);
                        method.invoke(t, xxx);
                        break;
                    }
                    case "Integer": {
                        Method method = tClass.getDeclaredMethod(setMethod, Integer.class);
                        method.invoke(t, Integer.parseInt(xxx));
                        break;
                    }
                    case "Date": {
                        Method method = tClass.getDeclaredMethod(setMethod, Date.class);
                        method.invoke(t, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(xxx));
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
