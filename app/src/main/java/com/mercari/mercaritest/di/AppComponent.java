package com.mercari.mercaritest.di;

import com.mercari.mercaritest.MercariApp;
import com.mercari.mercaritest.ui.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class
})
public interface AppComponent {
    void inject(MercariApp app);
    void inject(HomeActivity activity);

    final class Initializer {
        public static AppComponent init(MercariApp app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .networkModule(new NetworkModule("http://dummyimage.com/"))
                    .build();
        }

        private Initializer() {}
    }
}
