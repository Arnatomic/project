package infomila.info.peritapp.adapters;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import infomila.info.peritapp.R;
import infomila.info.peritapp.activities.InformeActivity;
import infomila.info.peritapp.activities.SinistreReadOnlyActivity;
import infomila.info.peritapp.asynctasks.DeleteEntradaAsyncTask;
import infomila.info.peritapp.model.EntradaInformeApp;


/**
 * Created by Mr. Robot on 14/06/2017.
 */

public class EntradaAdapter extends RecyclerView.Adapter<EntradaAdapter.Holder> {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<EntradaInformeApp> entrades = new ArrayList();
    private InformeActivity context;

    public EntradaAdapter(InformeActivity context, List<EntradaInformeApp> entrades){
        this.context = context;
        this.entrades = entrades;
    }


    @Override
    public EntradaAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutTrucades = R.layout.template_entrada;
        View row = LayoutInflater.from(parent.getContext()).inflate(layoutTrucades, parent, false);
        return  new Holder(row);
    }

    @Override
    public void onBindViewHolder(EntradaAdapter.Holder holder, final int position) {

        holder.tvDataEntrada.setText(sdf.format(entrades.get(position).getData()));
        holder.tvDesc.setText(entrades.get(position).getDescripcio());
        if(entrades.get(position).isPostReparacio()) holder.chPostReparacio.setChecked(true);
        else holder.chPostReparacio.setChecked(false);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entrades.remove(entrades.get(position));
                notifyDataSetChanged();

                DeleteEntradaAsyncTask task = new DeleteEntradaAsyncTask(context);
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,entrades.get(position).getNumero(),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return entrades.size();
    }


    public static class Holder extends RecyclerView.ViewHolder{
        private TextView tvDataEntrada, tvDesc;
        private Button btnDelete;
        private CheckBox chPostReparacio;


        public Holder(View row) {
            super(row);

            tvDataEntrada = (TextView)  row.findViewById(R.id.tvDataEntrada);
            tvDesc = (TextView)  row.findViewById(R.id.tvDescEntrada);
            btnDelete = (Button) row.findViewById(R.id.btnDeleteEntrada);
            chPostReparacio = (CheckBox) row.findViewById(R.id.chPostRepa);
        }
    }
}
