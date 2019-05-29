package com.example.dell.domea;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by dell on 2019/5/29.
 */

public class Rck_Adapter extends XRecyclerView.Adapter<Rck_Adapter.Vholder>{
    private Context mContext;
    private List<User>mList;

    public Rck_Adapter(Context context, List<User> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public Rck_Adapter.Vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new Vholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Rck_Adapter.Vholder holder, int position) {
        final User user = mList.get(position);
        final RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(mContext).load(user.getUrl()).apply(requestOptions).into(holder.iv);
        holder.tv.setText(user.getDesc());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class Vholder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public Vholder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.aiv);
            tv=itemView.findViewById(R.id.tv);
        }
    }
}
