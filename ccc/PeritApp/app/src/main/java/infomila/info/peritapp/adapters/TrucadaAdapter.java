package infomila.info.peritapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import infomila.info.peritapp.R;
import infomila.info.peritapp.activities.SinistreReadOnlyActivity;
import infomila.info.peritapp.model.TrucadaApp;

/**
 * Created by Mr. Robot on 14/06/2017.
 */

public class TrucadaAdapter extends RecyclerView.Adapter<TrucadaAdapter.Holder> {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private List<TrucadaApp> trucades = new ArrayList();
    private SinistreReadOnlyActivity context;

    public TrucadaAdapter(SinistreReadOnlyActivity context, List<TrucadaApp> trucades){
        this.context = context;
        this.trucades = trucades;
    }


    @Override
    public TrucadaAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutTrucades = R.layout.template_trucada;
        View row = LayoutInflater.from(parent.getContext()).inflate(layoutTrucades, parent, false);
        return  new Holder(row);
    }

    @Override
    public void onBindViewHolder(TrucadaAdapter.Holder holder, int position) {

        holder.tvDataHora.setText(sdf.format(trucades.get(position).getDataHora()));
        holder.tvContacte.setText(trucades.get(position).getPersonaContacte());
        holder.tvDesc.setText(trucades.get(position).getDescripcio());

    }

    @Override
    public int getItemCount() {
        return trucades.size();
    }


    public static class Holder extends RecyclerView.ViewHolder{
        private TextView tvDataHora, tvDesc, tvContacte;


        public Holder(View row) {
            super(row);

            tvDataHora = (TextView)  row.findViewById(R.id.tvDataHora);
            tvDesc = (TextView)  row.findViewById(R.id.tvDescripcioTrucada);
            tvContacte = (TextView)  row.findViewById(R.id.tvPersonaContacte);
        }
    }
}
