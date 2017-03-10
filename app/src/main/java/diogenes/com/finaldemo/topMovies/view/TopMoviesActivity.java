package diogenes.com.finaldemo.topMovies.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import diogenes.com.finaldemo.R;
import diogenes.com.finaldemo.root.App;
import diogenes.com.finaldemo.topMovies.TopMoviesActivityMVP;

import javax.inject.Inject;


public class TopMoviesActivity extends AppCompatActivity implements TopMoviesActivityMVP.View {


    // ef015f3da16cdbee6ec994a801aa81d6

    private static final String TAG = TopMoviesActivity.class.getName();

    @Inject
    TopMoviesActivityMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);

        //Dagger injection
        ((App) getApplication()).getComponent().inject(this);

        //add inject to to application component


    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);

    }


}
