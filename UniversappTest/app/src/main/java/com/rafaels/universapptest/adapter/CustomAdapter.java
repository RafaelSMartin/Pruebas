package com.rafaels.universapptest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rafaels.universapptest.R;
import com.rafaels.universapptest.model.Model;
import com.rafaels.universapptest.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private Model model;
    private Context context;
    private View.OnClickListener onClickListener;

    private static final int TYPE_ITEM = 1;

    public CustomAdapter(Context context, Model model) {
        this.context = context;
        this.model = model;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vItem = inflater.inflate(R.layout.list_item, parent, false);
        vItem.setOnClickListener(onClickListener);
        return new VHItem(vItem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {

        if(model.getResults().get(i).getGender().equals("female")){
            ((VHItem) holder).gender.setImageResource(R.drawable.ic_female);
        } else if(model.getResults().get(i).getGender().equals("male")){
            ((VHItem) holder).gender.setImageResource(R.drawable.ic_male);
        }
        ((VHItem) holder).name.setText(Util.ucFirst(
                model.getResults().get(i).getName().getFirst()));
        ((VHItem) holder).surname.setText(Util.ucFirst(
                model.getResults().get(i).getName().getLast()));

        String url = model.getResults().get(i).getPicture().getMedium();
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(((VHItem) holder).icon);

    }

    @Override
    public int getItemCount() {
        return model.getResults().size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }


    public class VHItem extends RecyclerView.ViewHolder {

        @BindView(R.id.item_layout)
        RelativeLayout itemLayout;
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.surname)
        TextView surname;
        @BindView(R.id.gender)
        ImageView gender;
        @BindView(R.id.delete)
        ImageView delete;

        public VHItem (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            delete.setOnClickListener(view ->
                    Toast.makeText(context, "delete push", Toast.LENGTH_SHORT).show()
            );
        }
    }



}
