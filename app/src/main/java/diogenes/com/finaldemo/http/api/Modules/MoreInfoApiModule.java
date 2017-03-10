package diogenes.com.finaldemo.http.api.Modules;

import dagger.Module;
import dagger.Provides;
import diogenes.com.finaldemo.http.api.MoreInfoApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by diogenes on 09/03/17.
 */

@Module
public class MoreInfoApiModule {

    public final String BASE_URL = "http://www.omdbapi.com";

    @Provides
    public OkHttpClient provideClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();

    }


    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client){

        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


    @Provides
    public MoreInfoApiService provideApiService(){

        return provideRetrofit(BASE_URL, provideClient()).create(MoreInfoApiService.class);

    }

}
