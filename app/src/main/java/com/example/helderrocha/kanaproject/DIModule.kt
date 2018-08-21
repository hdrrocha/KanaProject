package com.example.helderrocha.kanaproject

import android.content.Context
import com.example.helderrocha.kanaproject.api.NetworkModule
import com.example.helderrocha.kanaproject.model.PullRequest
import com.example.helderrocha.kanaproject.view.repositories.RepositoriesActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [
    NetworkModule::class,
    SchedulerModule::class
])
class AppModule

@Module
abstract class AndroidInjectorsModule {
    @ContributesAndroidInjector
    abstract fun repositoriesActivity(): RepositoriesActivity
    @ContributesAndroidInjector
    abstract fun pullRequestsActivity(): PullRequest

}

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        AndroidInjectorsModule::class
))
interface AppComponent : AndroidInjector<MyApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApp>() {
        @BindsInstance
        abstract fun appContext(appContext: Context): Builder
    }
}