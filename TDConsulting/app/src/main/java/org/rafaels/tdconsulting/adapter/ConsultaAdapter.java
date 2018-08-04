package org.rafaels.tdconsulting.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.rafaels.tdconsulting.R;
import org.rafaels.tdconsulting.model.Model;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsultaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflador;
    private List<Model> model;
    private Context context;
    protected View.OnClickListener onClickListener;

    public ConsultaAdapter(Context context, List<Model> model) {
        this.context = context;
        this.model = model;
        inflador = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vItem = inflador.inflate(R.layout.elemento_lista, parent, false);
        vItem.setOnClickListener(onClickListener);
        return new VHItem(vItem);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        ((VHItem) holder).dia.setText("Dia: " + model.get(i).getDia());
        ((VHItem) holder).altas.setText("  Altas: " + model.get(i).getAltas());
        ((VHItem) holder).simonly.setText("  Simonly: " + model.get(i).getSimonly());
        ((VHItem) holder).hzLpd.setText("  Hz Lpd: " + model.get(i).getHzLpd());
        ((VHItem) holder).hz.setText("  Hz: " + model.get(i).getHz());
        ((VHItem) holder).adsl.setText("  Adls: " + model.get(i).getAdsl());
        ((VHItem) holder).lpd.setText("  Lpd: " + model.get(i).getLpd());
        ((VHItem) holder).portPost.setText("  Port Post: " + model.get(i).getPortPost());
        ((VHItem) holder).migraciones.setText("  Migraciones: " + model.get(i).getMigraciones());
        ((VHItem) holder).star.setText("  Star: " + model.get(i).getStar());
        ((VHItem) holder).une.setText("  Une: " + model.get(i).getUne());
        ((VHItem) holder).adslUne.setText("  Adsl Une: " + model.get(i).getAdslUne());
        ((VHItem) holder).lpdUne.setText("  Lpd Une: " + model.get(i).getLpdUne());
        ((VHItem) holder).hzUne.setText("  Hz Une: " + model.get(i).getHzUne());

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class VHItem extends RecyclerView.ViewHolder {

        @BindView(R.id.dia)
        TextView dia;

        @BindView(R.id.altas)
        TextView altas;

        @BindView(R.id.simonly)
        TextView simonly;

        @BindView(R.id.hz_lpd)
        TextView hzLpd;

        @BindView(R.id.hz)
        TextView hz;

        @BindView(R.id.adsl)
        TextView adsl;

        @BindView(R.id.lpd)
        TextView lpd;

        @BindView(R.id.port_post)
        TextView portPost;

        @BindView(R.id.migraciones)
        TextView migraciones;

        @BindView(R.id.star)
        TextView star;

        @BindView(R.id.une)
        TextView une;

        @BindView(R.id.adsl_une)
        TextView adslUne;

        @BindView(R.id.lpd_une)
        TextView lpdUne;

        @BindView(R.id.hz_une)
        TextView hzUne;


        public VHItem (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
