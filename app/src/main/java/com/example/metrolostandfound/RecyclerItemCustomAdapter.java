package com.example.metrolostandfound;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerItemCustomAdapter extends RecyclerView.Adapter<RecyclerItemCustomAdapter.ViewHolder>{
    private List<RecyclerItemCustom> mData = null;


    //리스트뷰 사용을 위한 어댑터 ListView 보다 RecyclerView 가 더 좋은 거라길래 이거 씀

    RecyclerItemCustomAdapter(ArrayList<RecyclerItemCustom> list){
        mData = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));

        View view = inflater.inflate(R.layout.recycler_item_custom, parent, false);
        RecyclerItemCustomAdapter.ViewHolder vh = new RecyclerItemCustomAdapter.ViewHolder(view);

        return vh;
    }

    //나중에 아이템 수정할 때 여기 밑에 부분 수정하면 됨 위는 안 건드려도 됨
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerItemCustom item = mData.get(position);

        holder.imageView.setImageBitmap(item.getImage());
        holder.mc.setText(item.getMC());
        holder.sc.setText(item.getSC());
        holder.locate.setText(item.getLocate());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //아이템 수정할 때 여기도 수정
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView mc;
        TextView sc;
        TextView locate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_image);
            mc = itemView.findViewById(R.id.item_text_mc);
            sc = itemView.findViewById(R.id.item_text_sc);
            locate = itemView.findViewById(R.id.item_text_loc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {

                        RecyclerItemCustom item = mData.get(pos) ;

                        Intent intent = new Intent(v.getContext(), ObjectViewActivity.class);
                        intent.putExtra("id", item.getId());
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
