package diogenes.com.finaldemo.root;

import android.app.Application;

import diogenes.com.finaldemo.http.api.Modules.MoreInfoApiModule;
import diogenes.com.finaldemo.http.api.Modules.MovieApiModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        //needs to run once to generate it
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .moreInfoApiModule(new MoreInfoApiModule())
                .movieApiModule(new MovieApiModule())
                .build();

    }

    public ApplicationComponent getComponent() {
        return component;
    }

}
