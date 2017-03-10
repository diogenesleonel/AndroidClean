package diogenes.com.finaldemo.topMovies.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import diogenes.com.finaldemo.R;
import diogenes.com.finaldemo.topMovies.model.ViewModel;

/**
 * Created by diogenes on 09/03/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {


    private List<ViewModel> list;

    public ListAdapter(List<ViewModel> list) {
        this.list = list;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);


        return new ListItemViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {

        holder.itemName.setText(list.get(position).getName());
        holder.countryName.setText(list.get(position).getCountry());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_fragmentlist_task_name) TextView itemName;

        @BindView(R.id.textView_fragmentlist_country_name) TextView countryName;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
