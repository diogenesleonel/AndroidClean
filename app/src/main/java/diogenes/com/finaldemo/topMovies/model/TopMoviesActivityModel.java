package diogenes.com.finaldemo.topMovies.model;


import diogenes.com.finaldemo.http.apiModel.Result;
import diogenes.com.finaldemo.topMovies.TopMoviesActivityMVP;
import rx.Observable;
import rx.functions.Func2;

// TODO

public class TopMoviesActivityModel implements TopMoviesActivityMVP.Model {

    Repository repository;

    public TopMoviesActivityModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result() {

        return Observable.zip(repository.getResultData(), repository.getCountriesData(),
                new Func2<Result, String, ViewModel>() {
                    @Override
                    public ViewModel call(Result result, String country) {
                        return new ViewModel(country,result.getTitle());
                    }
                });

    }
}
