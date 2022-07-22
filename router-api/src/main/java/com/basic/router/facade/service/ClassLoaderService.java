package com.basic.router.facade.service;

import com.basic.router.facade.template.IProvider;

/**
 * Get class by user, maybe someone use InstantRun and other tech will move dex files.
 */
public interface ClassLoaderService extends IProvider {
    Class<?> forName();
}
