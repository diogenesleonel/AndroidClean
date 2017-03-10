package diogenes.com.finaldemo.topMovies.model;

import diogenes.com.finaldemo.http.api.MoreInfoApiService;
import diogenes.com.finaldemo.http.api.MovieApiService;

/**
 * Created by diogenes on 09/03/17.
 */

// TODO
public class TopMoviesRepository implements Repository {

    private MovieApiService movieApiService;
    private MoreInfoApiService moreInfoApiService;

    public TopMoviesRepository(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService) {
        this.moreInfoApiService = moreInfoApiService;
        this.movieApiService = movieApiService;
    }
}
