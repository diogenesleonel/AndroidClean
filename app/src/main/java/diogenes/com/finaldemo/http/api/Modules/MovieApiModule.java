package diogenes.com.finaldemo.http.api.Modules;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import diogenes.com.finaldemo.http.api.MovieApiService;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by diogenes on 09/03/17.
 */

@Module
public class MovieApiModule {

    public final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    public final String API_KEY = "ef015f3da16cdbee6ec994a801aa81d6";

    @Provides
    public OkHttpClient provideClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        // New type of request to show log using apikey
                        Request request = chain.request();
                        HttpUrl url = request.url().newBuilder()
                                .addQueryParameter("api_key", API_KEY)
                                .build();

                        request = request.newBuilder().url(url).build();

                        return chain.proceed(request);
                    }
                })
                .build();

    }

    @Provides
    public Retrofit providesRetrofit(String baseURL, OkHttpClient client ){

        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Provides
    public MovieApiService provideApiService(){
        return providesRetrofit(BASE_URL, provideClient()).create(MovieApiService.class);
    }

}
