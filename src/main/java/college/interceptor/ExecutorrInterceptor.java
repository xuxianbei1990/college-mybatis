package college.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 更新时间默认取本项目时间
 *
 * @author: xuxianbei
 * Date: 2020/9/3
 * Time: 14:08
 * Version:V1.0
 */
@Slf4j
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
@Component
public class ExecutorrInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //这里有两个参数：第一个参数放的语句，第二个参数放的是目标对象例如:商品实体
        Object object = invocation.getArgs()[1];
        //获取对象的类
        Class<?> clazz = object.getClass();

        List<Consumer<Field>> consumerList = new ArrayList();
        //更新时间
        Consumer<Field> updateDateConsumer = updateDate(object);
        //去空格
        Consumer<Field> trimStringConsumer = trimString(object);

        consumerList.add(updateDateConsumer);
        consumerList.add(trimStringConsumer);

        forProperty(clazz, consumerList);
        return invocation.proceed();
    }

    private Consumer<Field> trimString(Object object) {
        return field -> {
            if (field.getType().equals(String.class)) {
                setPropertyValue(object, field, (oldValue) -> ((String) oldValue).trim());
            }
        };
    }

    private void setPropertyValue(Object object, Field field, Function<Object, Object> function) {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(object, function.apply(field.get(object)));
        } catch (IllegalAccessException e) {
            log.error("属性设置失败：", e);
        }
        field.setAccessible(accessible);
    }

    private Consumer<Field> updateDate(Object object) {
        return (declaredField) -> {
            if ("updateDate".equals(declaredField.getName())) {
                if (declaredField.getType().equals(LocalDateTime.class)) {
                    setPropertyValue(object, declaredField, (oldValue) -> LocalDateTime.now());
                } else if (declaredField.getType().equals(Date.class)) {
                    setPropertyValue(object, declaredField, (oldValue) -> new Date());
                }
            }
        };
    }

    private void forProperty(Class<?> clazz, List<Consumer<Field>> consumers) {
        for (Field declaredField : clazz.getDeclaredFields()) {
            consumers.forEach(consumer -> consumer.accept(declaredField));
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
