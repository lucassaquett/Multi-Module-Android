package com.example.android.architectureexample

import androidx.multidex.MultiDexApplication
import com.example.android.architectureexample.di.DaggerAppComponent
import com.example.android.core_impl.di.CoreComponentProvider
import com.example.android.core_impl.di.component.CoreComponent
import com.example.android.core_impl.di.component.DaggerCoreComponent
import com.example.android.core_impl.di.module.ContextModule

class CustomApplication : MultiDexApplication(), CoreComponentProvider {

    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        initCoreDependencyInjection()
        initAppDependencyInjection()
    }


    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    override fun provideCoreComponent(): CoreComponent = coreComponent

}