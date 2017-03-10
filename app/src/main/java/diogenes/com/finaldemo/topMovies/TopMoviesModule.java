package diogenes.com.finaldemo.topMovies;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


import diogenes.com.finaldemo.http.api.MoreInfoApiService;
import diogenes.com.finaldemo.http.api.MovieApiService;
import diogenes.com.finaldemo.topMovies.model.Repository;
import diogenes.com.finaldemo.topMovies.model.TopMoviesActivityModel;
import diogenes.com.finaldemo.topMovies.model.TopMoviesRepository;

@Module
public class TopMoviesModule {
    

    @Provides
    public TopMoviesActivityMVP.Presenter provideTopMoviesActivityPresenter(TopMoviesActivityMVP.Model model) {
        return new TopMoviesActivityPresenter(model);
    }

    @Provides
    public TopMoviesActivityMVP.Model provideTopMoviesActivityModel(Repository repository) {
        return new TopMoviesActivityModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService) {
        return new TopMoviesRepository(movieApiService, moreInfoApiService);
    }

}
