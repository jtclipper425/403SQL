package com.androidbash.androidbashphpmysql;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private Context context;
    private List<Item> movies;

    public MoviesAdapter(Context context, List<Item> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.movieName.setText(movies.get(position).getItemTitle());
        holder.movieGenre.setText(movies.get(position).getItemQuality());
        Glide.with(context).load(movies.get(position).getImageLink()).into(holder.imageView);
    }



    @Override
    public int getItemCount() {
        return movies.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView movieName;
        public TextView movieGenre;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            movieName = (TextView) itemView.findViewById(R.id.moviename);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            movieGenre = (TextView) itemView.findViewById(R.id.genre);
            imageView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            showPopupMenu(v,position);
        }
    }

    private void showPopupMenu(View view, int poaition) {
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.setOnMenuItemClickListener(new MenuClickListener(poaition));
        popup.show();
    }


    class MenuClickListener implements PopupMenu.OnMenuItemClickListener {
        Integer pos;
        public MenuClickListener(int pos) {
            this.pos=pos;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_create_order:
                    addCheckoutToDB(3);
                    return true;
                default:
            }
            return false;
        }

        private void addCheckoutToDB(int id) {
            AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
                @Override
                protected Void doInBackground(Integer... movieIds) {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.0.2.2/checkouts.php?id=" + movieIds[0])
                            .build();
                    try {
                        Response response = client.newCall(request).execute();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };

            asyncTask.execute(id);
        }
    }
}
