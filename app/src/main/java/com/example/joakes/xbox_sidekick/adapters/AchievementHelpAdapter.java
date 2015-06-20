package com.example.joakes.xbox_sidekick.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.requests.utils.WebService;
import com.google.api.services.youtube.model.SearchResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joakes on 5/11/15.
 */
public class AchievementHelpAdapter extends RecyclerView.Adapter<AchievementHelpViewHolder> {
    private ArrayList<SearchResult> mResults;
    private Context mContext;

    public AchievementHelpAdapter(Context context) {
        mResults = new ArrayList<>();
        mContext = context;
    }

    @Override
    public AchievementHelpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.youtube_video_card, parent, false);
        return new AchievementHelpViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AchievementHelpViewHolder holder, int position) {
        SearchResult result = getResult(position);
        holder.youtubeTitle.setText(result.getSnippet().getTitle());
        holder.youtubeAuthor.setText(mContext.getString(R.string.by_author_formatted, result.getSnippet().getChannelTitle()));
        WebService webService = new WebService(mContext);
        webService.loadImageFromUrl(holder.youtubeIcon, result.getSnippet().getThumbnails().getDefault().getUrl());
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public void addSearchResults(List<SearchResult> results) {
        mResults.addAll(results);
        notifyDataSetChanged();
    }

    public SearchResult getResult(int position) {
        return mResults.get(position);
    }
}