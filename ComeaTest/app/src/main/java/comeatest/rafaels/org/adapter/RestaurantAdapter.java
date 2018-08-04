package comeatest.rafaels.org.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import comeatest.rafaels.org.R;
import comeatest.rafaels.org.model.Model;
import comeatest.rafaels.org.util.Utils;

public class RestaurantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflador;
    private List<Model> model;
    private Context context;
    protected View.OnClickListener onClickListener;

    public RestaurantAdapter(Context context, List<Model> model){
        this.context = context;
        this.model = model;
        inflador = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vItem = inflador.inflate(R.layout.list_item, parent, false);
        vItem.setOnClickListener(onClickListener);
        return new VHItem(vItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Calendar mcurrentTime = Calendar.getInstance();
        int currentHour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int currentMinute = mcurrentTime.get(Calendar.MINUTE);

        int hourOpen = model.get(position).getOpeningHour();
        int hourClose = model.get(position).getClosingHour();

        ((VHItem) holder).icon.setImageURI(model.get(position).getLogoUrl());
        ((VHItem) holder).name.setText(model.get(position).getName());
        ((VHItem) holder).category.setText(model.get(position).getCategory());


        if((hourOpen <= currentHour) && (currentHour < hourClose) ){
            ((VHItem) holder).openClose.setTextColor(
                    context.getResources().getColor(R.color.openGreen));
            ((VHItem) holder).openClose.setText(R.string.open);
//            ((VHItem) holder).openClose.setTextColor(Color.GREEN);
        }else{
//            ((VHItem) holder).cardView.setCardBackgroundColor(Color.GRAY);
            ((VHItem) holder).openClose.setText(R.string.close);
            ((VHItem) holder).openClose.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class VHItem extends RecyclerView.ViewHolder{

        @BindView(R.id.item_layout)
        RelativeLayout layoutItem;
        @BindView(R.id.card_view_item)
        CardView cardView;
        @BindView(R.id.icono)
        SimpleDraweeView icon;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.category)
        TextView category;
        @BindView(R.id.open_close)
        TextView openClose;

        public VHItem (View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);

            Utils.setTypefaceAvenirMedium(context, name);
            Utils.setTypefaceAvenirMedium(context, category);
            Utils.setTypefaceAvenirMedium(context, openClose);

        }
    }


}
