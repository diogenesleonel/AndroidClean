package diogenes.com.finaldemo.http.api;

import diogenes.com.finaldemo.http.apiModel.TopRated;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by diogenes on 09/03/17.
 */

// Endpoint themoviedb
public interface MovieApiService {

    @GET("top_rated")
    Observable<TopRated> getTopRatedMovies(@Query("page") Integer page);

}
