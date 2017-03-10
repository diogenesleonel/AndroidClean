package diogenes.com.finaldemo.topMovies;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import diogenes.com.finaldemo.topMovies.model.ViewModel;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TopMoviesActivityPresenter implements TopMoviesActivityMVP.Presenter {

    private static final String TAG = TopMoviesActivityPresenter.class.getName();

    @Nullable
    private TopMoviesActivityMVP.View view;
    private Subscription subscription = null;
    private TopMoviesActivityMVP.Model model;


    public TopMoviesActivityPresenter(TopMoviesActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {

        subscription = model.result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ViewModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(ViewModel viewModel) {

                    }
                });

    }

    @Override
    public void rxUnsubscribe() {

    }

    @Override
    public void setView(@NonNull TopMoviesActivityMVP.View view) {
        this.view = view;
    }

}
