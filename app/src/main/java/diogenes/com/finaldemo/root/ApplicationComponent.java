package diogenes.com.finaldemo.root;

import javax.inject.Singleton;


import dagger.Component;
import diogenes.com.finaldemo.topMovies.view.TopMoviesActivity;
import diogenes.com.finaldemo.topMovies.TopMoviesModule;

@Singleton
@Component(modules = {ApplicationModule.class, TopMoviesModule.class})
public interface ApplicationComponent {

    void inject(TopMoviesActivity target);


}
