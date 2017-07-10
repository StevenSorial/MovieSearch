package com.steven.moviesearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.steven.moviesearch.models.ResultItem;

import java.util.ArrayList;

public class ResultItemAdapter extends ArrayAdapter<ResultItem> {

	private static final String TAG = ResultItemAdapter.class.getName();


	public ResultItemAdapter(Context context, ArrayList<ResultItem> resultList) {

		super(context, 0, resultList);
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ResultItem currentResultItem = getItem(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
		}
		TextView titleTextView = (TextView) convertView.findViewById(R.id.textView_title_year);

		String titleYear;

		if (!TextUtils.isEmpty(currentResultItem.getReleaseDate())) {
			titleYear = currentResultItem.getTitle() + " (" + currentResultItem.getYear() + ")";
		} else {
			titleYear = currentResultItem.getTitle();
		}
		titleTextView.setText(titleYear);

		// TextView typeTextView = (TextView) convertView.findViewById(R.id.textView_type);
		// typeTextView.setText(currentResultItem.getType());

		ImageView posterImageView = (ImageView) convertView.findViewById(R.id.imageView_poster);
		Glide.with(getContext())
				.load("https://image.tmdb.org/t/p/w500" + currentResultItem.getPosterPath())
				.apply(RequestOptions.centerCropTransform())
				.into(posterImageView);

		return convertView;
	}
}
