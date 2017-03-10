package diogenes.com.finaldemo.topMovies.model;


import diogenes.com.finaldemo.topMovies.TopMoviesActivityMVP;
import rx.Observable;

// TODO

public class TopMoviesActivityModel implements TopMoviesActivityMVP.Model {

    Repository repository;

    public TopMoviesActivityModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result() {
        return null;
    }
}
