package diogenes.com.finaldemo.topMovies.model;

import diogenes.com.finaldemo.http.apiModel.Result;
import rx.Observable;

/**
 * Created by diogenes on 09/03/17.
 */

// TODO
public interface Repository {

    Observable<Result> getResultFromMemory();

    Observable<Result> getResultFromNetwork();

    Observable<String> getCountriesFromMemory();

    Observable<String> getCountriesFromNetwork();

    // Facades

    Observable<Result> getResultData();

    Observable<String> getCountriesData();

}
