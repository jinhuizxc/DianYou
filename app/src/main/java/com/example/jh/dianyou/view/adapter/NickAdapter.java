package com.example.jh.dianyou.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jh.dianyou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jinhui on 2017/10/30.
 * Email：1004260403@qq.com
 *
 * 各种昵称适配器
 */
public class NickAdapter extends RecyclerView.Adapter<NickAdapter.UserViewHolder> {

    private int[] iv_nick = {
            R.drawable.selector_dad, R.drawable.selector_mom, R.drawable.selector_grandpa,
            R.drawable.selector_grandma, R.drawable.selector_uncle, R.drawable.selector_aunt,
            R.drawable.selector_other
    };

    public interface OnItemClickListener {
        void onImeiItemClicked(String nick, int position);
    }

    private String[] nick;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;


    public NickAdapter(Context context, String[] nick) {
        this.nick = nick;
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return nick.length;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.view_nick_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        ;
        holder.tvNick.setText(nick[position]);
        holder.ivNick.setImageResource(iv_nick[position]);

        holder.llNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NickAdapter.this.onItemClickListener != null) {
                    NickAdapter.this.onItemClickListener.onImeiItemClicked(nick[position], position);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    static class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nick)
        TextView tvNick;
        @BindView(R.id.iv_nick)
        ImageView ivNick;
        @BindView(R.id.ll_nick)
        LinearLayout llNick;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

