package com.example.githubapi.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.githubapi.Model.Repo;
import com.example.githubapi.R;

import java.util.List;

import static com.example.githubapi.R.id.repoDescription;

/**
 * Created by siddhi on 8/8/17.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder>{

    private List<Repo> repos;
    private int rowLayout;
    private Context context;

    public RepoAdapter(List<Repo> repos, int rowLayout, Context context) {
        this.setRepos(repos);
        this.setRowLayout(rowLayout);
        this.setContext(context);
    }

    public List<Repo> getRepos() {
        return repos;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }

    public int getRowLayout() {
        return rowLayout;
    }

    public void setRowLayout(int rowLayout) {
        this.rowLayout = rowLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static class RepoViewHolder extends RecyclerView.ViewHolder {

        LinearLayout reposLayout;
        TextView repoName;
        TextView repoDesc;
        TextView repoLang;

        public RepoViewHolder(View v){
            super(v);
            reposLayout = (LinearLayout) v.findViewById(R.id.repo_item_layout);
            repoName = (TextView) v.findViewById(R.id.repoName);
            repoDesc = (TextView) v.findViewById(repoDescription);
            repoLang = (TextView) v.findViewById(R.id.repoLanguage);
        }
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(rowLayout,parent,false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {

        holder.repoName.setText(repos.get(position).getName());
        holder.repoDesc.setText(repos.get(position).getDescription());
        holder.repoLang.setText("Language: "+ repos.get(position).getLanguage());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }
}
