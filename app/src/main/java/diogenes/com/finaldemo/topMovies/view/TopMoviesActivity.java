package diogenes.com.finaldemo.topMovies.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewGroupCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import diogenes.com.finaldemo.R;
import diogenes.com.finaldemo.root.App;
import diogenes.com.finaldemo.topMovies.TopMoviesActivityMVP;
import diogenes.com.finaldemo.topMovies.model.ViewModel;

import javax.inject.Inject;


public class TopMoviesActivity extends AppCompatActivity implements TopMoviesActivityMVP.View {

    private static final String TAG = TopMoviesActivity.class.getName();

    // ef015f3da16cdbee6ec994a801aa81d6

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @BindView(R.id.listActivity_rootView) ViewGroup viewGroup;

    @Inject
    TopMoviesActivityMVP.Presenter presenter;

    private ListAdapter listAdapter;
    private List<ViewModel> resultsList = new ArrayList<>();


    // Could use IcePick
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);

        //Dagger injection
        ((App) getApplication()).getComponent().inject(this);

        //add inject to to application component
        ButterKnife.bind(this);

        viewSetup();


    }

    private void viewSetup() {

        listAdapter = new ListAdapter(resultsList);
        recyclerView.setAdapter(listAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.setView(this);
        presenter.loadData();

    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.rxUnsubscribe();
        resultsList.clear();
        listAdapter.notifyDataSetChanged();

    }

    @Override
    public void updateData(ViewModel viewModel) {

        resultsList.add(viewModel);
        listAdapter.notifyItemInserted(resultsList.size() - 1);

    }

    @Override
    public void showSnackbar(String s) {

        Snackbar.make(viewGroup, s, Snackbar.LENGTH_SHORT).show();

    }


}
