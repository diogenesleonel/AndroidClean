package diogenes.com.finaldemo.topMovies.model;

import java.util.ArrayList;
import java.util.List;

import diogenes.com.finaldemo.http.api.MoreInfoApiService;
import diogenes.com.finaldemo.http.api.MovieApiService;
import diogenes.com.finaldemo.http.apiModel.OmdbApi;
import diogenes.com.finaldemo.http.apiModel.Result;
import diogenes.com.finaldemo.http.apiModel.TopRated;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by diogenes on 09/03/17.
 */

// TODO - Cached repository
public class TopMoviesRepository implements Repository {

    private MovieApiService movieApiService;
    private MoreInfoApiService moreInfoApiService;

    private List<String> countries;
    private List<Result> results;
    private long timestamp;

    private static final long STALE_MS = 20 * 1000; // Data is stale after 20 seconds

    public TopMoviesRepository(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService) {
        this.moreInfoApiService = moreInfoApiService;
        this.movieApiService = movieApiService;

        this.timestamp = System.currentTimeMillis();

        countries = new ArrayList<>();
        results = new ArrayList<>();
    }

    public boolean isUpToDate(){

        return (System.currentTimeMillis() - timestamp) < STALE_MS;

    }

    @Override
    public Observable<Result> getResultFromMemory() {

        if(isUpToDate()){

            return Observable.from(results);

        }else {

            timestamp = System.currentTimeMillis();
            results.clear();

            return Observable.empty();

        }


    }

    @Override
    public Observable<Result> getResultFromNetwork() {

        // Concatenate 3 result pages of movies
        Observable<TopRated> topRatedObservable = movieApiService.getTopRatedMovies(1)
                .concatWith(movieApiService.getTopRatedMovies(2))
                .concatWith(movieApiService.getTopRatedMovies(3));


        // Map each topRated item to Results and add to the list results
        return topRatedObservable
                .concatMap(new Func1<TopRated, Observable<Result>>() {
                    @Override
                    public Observable<Result> call(TopRated topRated) {
                        return Observable.from(topRated.getResults());
                    }
                }).doOnNext(new Action1<Result>() {
                    @Override
                    public void call(Result result) {
                        results.add(result);
                    }
                });


    }

    @Override
    public Observable<String> getCountriesFromMemory() {

        if(isUpToDate()){

            return Observable.from(countries);

        }else {

            timestamp = System.currentTimeMillis();
            countries.clear();

            return Observable.empty();

        }
    }

    @Override
    public Observable<String> getCountriesFromNetwork() {


        return getResultFromNetwork()
                .concatMap(new Func1<Result, Observable<OmdbApi>>() {
                    @Override
                    public Observable<OmdbApi> call(Result result) {
                        return moreInfoApiService.getCountry(result.getTitle());
                    }
                })
                .concatMap(new Func1<OmdbApi, Observable<String>>() {
                    @Override
                    public Observable<String> call(OmdbApi omdbApi) {
                        return Observable.just(omdbApi.getCountry());
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        countries.add(s);
                    }
                });

    }

    @Override
    public Observable<Result> getResultData() {
        return getResultFromMemory().switchIfEmpty(getResultFromNetwork());
    }

    @Override
    public Observable<String> getCountriesData() {
        return getCountriesFromMemory().switchIfEmpty(getCountriesFromNetwork());
    }
}
