package com.lgh.happyread.maincontent.infomation;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lgh.happyread.R;
import com.lgh.happyread.model.BaseType;

public class ContentViewHolder0 extends  RecyclerView.ViewHolder implements View.OnClickListener{
    public CardView mCardView;
    public TextView tvTitle;
    public TextView tvContent;
    public TextView tvArtist;
    public BaseType.InfoItem mItem;
    public IContentItemClick mListener;

    public ContentViewHolder0(View itemView, IContentItemClick listener) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvContent = (TextView) itemView.findViewById(R.id.tv_content);
        tvArtist = (TextView) itemView.findViewById(R.id.tv_artist);
        mCardView = (CardView) itemView.findViewById(R.id.cardview);
        mCardView.setOnClickListener(this);
        mListener = listener;
    }

    public void bindInfo( BaseType.InfoItem item){
        mItem = item;
        tvTitle.setText(item.mTitle);
        tvContent.setText(item.mSummary);
        tvArtist.setText(item.mUserName);

    }

    @Override
    public void onClick(View v) {
        if (mListener != null){
            mListener.onItemClick(mItem);
        }
    }
}
