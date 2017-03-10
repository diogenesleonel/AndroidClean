package diogenes.com.finaldemo.http.api;

import diogenes.com.finaldemo.http.apiModel.OmdbApi;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by diogenes on 09/03/17.
 */

// Endpoint omdb
public interface MoreInfoApiService {


    @GET("/")
    Observable<OmdbApi> getCountry(@Query("t") String title);
}
