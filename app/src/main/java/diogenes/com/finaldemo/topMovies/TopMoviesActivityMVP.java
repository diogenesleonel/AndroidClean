package diogenes.com.finaldemo.topMovies;

import rx.Observable;

import diogenes.com.finaldemo.topMovies.model.ViewModel;


public interface TopMoviesActivityMVP {

    interface Model {

        Observable<ViewModel> result();

    }

    interface View {

        void updateDate(ViewModel viewModel);

        void showSnackbar(String s);

    }

    interface Presenter {

        void loadData();

        void rxUnsubscribe();

        void setView(TopMoviesActivityMVP.View view);

    }
}
