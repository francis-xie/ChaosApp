package com.basic.router.core;

import android.content.Context;
import android.util.LruCache;

import com.basic.router.facade.annotation.Route;
import com.basic.router.facade.service.AutowiredService;
import com.basic.router.facade.template.ISyringe;

import java.util.ArrayList;
import java.util.List;

import static com.basic.router.utils.Consts.SUFFIX_AUTOWIRED;

/**
 * param inject service impl.
 */
@Route(path = "/router/service/autowired")
public class AutowiredServiceImpl implements AutowiredService {
    private LruCache<String, ISyringe> classCache;
    private List<String> blackList;

    @Override
    public void init(Context context) {
        classCache = new LruCache<>(50);
        blackList = new ArrayList<>();
    }

    @Override
    public void autowire(Object instance) {
        doInject(instance, null);
    }

    /**
     * Recursive injection
     *
     * @param instance who call me.
     * @param parent   parent of me.
     */
    private void doInject(Object instance, Class<?> parent) {
        Class<?> clazz = null == parent ? instance.getClass() : parent;

        ISyringe syringe = getSyringe(clazz);
        if (null != syringe) {
            syringe.inject(instance);
        }

        Class<?> superClazz = clazz.getSuperclass();
        // has parent and its not the class of framework.
        if (null != superClazz && !superClazz.getName().startsWith("android")) {
            doInject(instance, superClazz);
        }
    }

    private ISyringe getSyringe(Class<?> clazz) {
        String className = clazz.getName();

        try {
            if (!blackList.contains(className)) {
                ISyringe syringeHelper = classCache.get(className);
                if (null == syringeHelper) {  // No cache.
                    syringeHelper = (ISyringe) Class.forName(clazz.getName() + SUFFIX_AUTOWIRED).getConstructor().newInstance();
                }
                classCache.put(className, syringeHelper);
                return syringeHelper;
            }
        } catch (Exception e) {
            blackList.add(className);    // This instance need not autowired.
        }

        return null;
    }
}
