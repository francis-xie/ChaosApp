package com.basic.router.register.launch

import com.basic.router.register.utils.Logger
import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.basic.router.register.utils.ScanSetting
import com.basic.router.register.core.RegisterTransform
import org.gradle.api.Plugin
import org.gradle.api.Project
/**
 * Simple version of AutoRegister plugin for Router
 */
public class PluginLaunch implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        def isApp = project.plugins.hasPlugin(AppPlugin)
        //only application module needs this plugin to generate register code
        if (isApp) {
            Logger.make(project)

            Logger.i('Project enable router-register plugin')

            def android = project.extensions.getByType(AppExtension)
            def transformImpl = new RegisterTransform(project)

            //init router-auto-register settings
            ArrayList<ScanSetting> list = new ArrayList<>(3)
            list.add(new ScanSetting('IRouteRoot'))
            list.add(new ScanSetting('IInterceptorGroup'))
            list.add(new ScanSetting('IProviderGroup'))
            RegisterTransform.registerList = list
            //register this plugin
            android.registerTransform(transformImpl)
        }
    }

}
