package college.framework.ibatis.cache.impl;

import college.framework.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author: xuxianbei
 * Date: 2020/3/24
 * Time: 15:52
 * Version:V1.0
 */
public class PerpetualCache implements Cache {
    @Override
    public String getId() {
        return null;
    }

    @Override
    public void putObject(Object key, Object value) {

    }

    @Override
    public Object getObject(Object key) {
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
