package com.basic.router.facade.service;

import com.basic.router.facade.template.IProvider;

/**
 * Service for autowired.
 */
public interface AutowiredService extends IProvider {

    /**
     * Autowired core.
     * @param instance the instance who need autowired.
     */
    void autowire(Object instance);
}
