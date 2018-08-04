package com.rafaels.testvocces.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rafaels.testvocces.R;
import com.rafaels.testvocces.model.Model;
import com.rafaels.testvocces.util.Util;

import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MiAdaptador extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflador;
    private Model model;
    private Context context;
    protected View.OnClickListener onClickListener;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public MiAdaptador(Context context, Model model) {
        this.context = context;
        this.model = model;
        inflador = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View vItem = inflador.inflate(R.layout.elemento_lista, parent, false);
            vItem.setOnClickListener(onClickListener);
            return new VHItem(vItem);
        } else if (viewType == TYPE_HEADER){
            View vHeader = inflador.inflate(R.layout.elemento_lista_header, parent, false);
            return new VHHeader(vHeader);
        }
        throw new RuntimeException("there is no type that matches the type " +
                viewType + " + make sure your using types correctly");

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {

        if (holder instanceof VHItem) {

            if(model.getResults().get(i).getGender().equals("female")){
                ((VHItem) holder).genero.setImageResource(R.drawable.ic_female);
            } else if(model.getResults().get(i).getGender().equals("male")){
                ((VHItem) holder).genero.setImageResource(R.drawable.ic_male);
            }
            ((VHItem) holder).nombre.setText(Util.ucFirst(
                    model.getResults().get(i).getName().getFirst()));
            ((VHItem) holder).apellido.setText(Util.ucFirst(
                    model.getResults().get(i).getName().getLast()));

            String url = model.getResults().get(i).getPicture().getMedium();
//            Log.d("adaptador", url);
            Glide.with(context)
                    .load(url)
                    .into(((VHItem) holder).icon);
        } else if (holder instanceof VHHeader){
            ((VHHeader) holder).header.setText(String.format("%d", model.getResults().size())
                    +" usuarios obtenidos ");
        }

    }

    @Override
    public int getItemCount() {
        return model.getResults().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public class VHItem extends RecyclerView.ViewHolder {

        @BindView(R.id.item_layout)
        RelativeLayout itemLayout;
        @BindView(R.id.icono)
        ImageView icon;
        @BindView(R.id.nombre)
        TextView nombre;
        @BindView(R.id.apellido)
        TextView apellido;
        @BindView(R.id.genero)
        ImageView genero;

        public VHItem (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class VHHeader extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_header)
        TextView header;

        public VHHeader(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
