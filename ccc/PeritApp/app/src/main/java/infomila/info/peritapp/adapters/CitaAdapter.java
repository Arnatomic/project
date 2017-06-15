package infomila.info.peritapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import infomila.info.peritapp.R;
import infomila.info.peritapp.activities.CitesActivity;
import infomila.info.peritapp.activities.InformeActivity;
import infomila.info.peritapp.activities.SinistreReadOnlyActivity;
import infomila.info.peritapp.asynctasks.TancaInformeAsyncTask;
import infomila.info.peritapp.model.ResumSinistreApp;
import infomila.info.peritapp.model.enums.ESTAT_INFORME;
import infomila.info.peritapp.model.enums.ESTAT_SINISTRE;

/**
 * Created by Mr. Robot on 13/06/2017.
 */

public class CitaAdapter extends RecyclerView.Adapter<CitaAdapter.ViewHolder>{


    private CitesActivity context;
    private List<ResumSinistreApp> cites = new ArrayList<>();
    private Date today;

    public CitaAdapter(CitesActivity context, List<ResumSinistreApp> cites){
        this.context = context;
        this.cites = cites;
        today = new Date();
        System.out.println("AVUI: " + today);
    }

    @Override
    public CitaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       int template = R.layout.tamplate_sinistre;
        View row = LayoutInflater.from(parent.getContext()).inflate(template,parent,false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final CitaAdapter.ViewHolder holder, int position) {

        System.out.println("SIZE: " +getItemCount());

        final ResumSinistreApp resumActual = cites.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        SimpleDateFormat tdf = new SimpleDateFormat("HH:mm");

        try {
            today = sdf.parse(sdf.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }



        holder.tvDiaSetmanaNom.setText(getDayOfWeek(resumActual.getDiaHora()));
        holder.tvDiaSetmanaData.setText(sdf.format(resumActual.getDiaHora()));

        System.out.println("POSITION: " +position);

        holder.tvHora.setText(tdf.format(resumActual.getDiaHora()));
        holder.tvPoblacio.setText(resumActual.getAdreca());
        holder.tvIdSinistre.setText(Integer.toString(resumActual.getSinistreId()));
        holder.tvTipusSinistre.setText(resumActual.getTipusSinistre().toString());

        try {
            if(position >0) {
                System.out.println("POS -1: " + sdf.parse(sdf.format(cites.get(position - 1).getDiaHora())));
                System.out.println("POS: " + sdf.parse(sdf.format(cites.get(position).getDiaHora())));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(position >0){
            try {
                if(sdf.parse(sdf.format(resumActual.getDiaHora())).compareTo(sdf.parse(sdf.format(cites.get(position-1).getDiaHora()))) == 0) {
                    holder.layoutData.setVisibility(View.GONE);
                } else{
                    holder.layoutData.setVisibility(View.VISIBLE);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        int res = -2;

        try {
            res = sdf.parse(sdf.format(resumActual.getDiaHora())).compareTo(sdf.parse(sdf.format(today)));
            System.out.println("RES: " +res);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setColorDia(holder,res);

        if(resumActual.getEstatInforme() == ESTAT_INFORME.PENDENT && res !=0){
            holder.ivEstatInforme.setImageResource(R.drawable.settingss);
        }else if(resumActual.getEstatInforme() == ESTAT_INFORME.TANCAT && res ==0){
            holder.ivEstatInforme.setImageResource(R.drawable.ok);
        }
        else if(resumActual.getEstatInforme() != ESTAT_INFORME.TANCAT && res ==0){
            holder.ivEstatInforme.setImageResource(R.drawable.nothing);
        }

        if(resumActual.getEstatInforme() == ESTAT_INFORME.INEXISTENT && res <1){
            holder.ivEstatInforme.setImageResource(R.drawable.warning);
        }



        System.out.println("ESTAT: " + resumActual.getEstatInforme());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //actionMode =context.startActionMode(new MenuCallBack(resumActual.getSinistreId(),resumActual.getEstatInforme(),context));
                PopupMenu menu = new PopupMenu(context,view);
                menu.inflate(R.menu.menu_contextual);

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.visualitzarInforme:
                                Intent intent = new Intent(context, SinistreReadOnlyActivity.class);
                                intent.putExtra("idSinistre",resumActual.getSinistreId());
                                context.startActivity(intent);
                                break;
                            case R.id.editarInforme:
                                if(resumActual.getEstatInforme() == ESTAT_INFORME.INEXISTENT){
                                    AlertDialog dialog = new AlertDialog.Builder(context).create();
                                    dialog.setTitle("Acció no disponible!");
                                    dialog.setMessage("El sinistre " + resumActual.getSinistreId() + " no te informe");
                                    dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.show();
                                }else{
                                    Intent in = new Intent(context, InformeActivity.class);
                                    in.putExtra("idSinistre",resumActual.getSinistreId());
                                    context.startActivity(in);
                                }
                                break;
                            case R.id.tancarInforme:
                                if(resumActual.getEstatInforme() != ESTAT_INFORME.PENDENT){

                                    String missatge = "";

                                    if(resumActual.getEstatInforme() == ESTAT_INFORME.TANCAT) missatge = "L'informe en questió ja es tancat!";
                                    else missatge = "El sinistre " + resumActual.getSinistreId() + " no conté informe.";

                                    AlertDialog dialog = new AlertDialog.Builder(context).create();
                                    dialog.setTitle("Acció no disponible!");
                                    dialog.setMessage(missatge);
                                    dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.show();
                                }else{
                                    resumActual.setEstatInforme(ESTAT_INFORME.TANCAT);
                                    notifica();
                                    TancaInformeAsyncTask task = new TancaInformeAsyncTask(context);
                                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,resumActual.getSinistreId());

                                }
                                break;
                        }
                        return false;
                    }
                });

                menu.show();
            }
        });


    }

    private void notifica() {
        notifyDataSetChanged();
    }

    private void setColorDia(ViewHolder holder, int res) {
        if(res < 0) holder.layoutData.setBackgroundColor(context.getResources().getColor(R.color.red_sinistre));
        else if(res == 0) holder.layoutData.setBackgroundColor(context.getResources().getColor(R.color.groc_sinistre));
        else if(res > 0) holder.layoutData.setBackgroundColor(context.getResources().getColor(R.color.green_sinistre));
    }

    private String getDayOfWeek(Date diaHora) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(diaHora);
        int dia = cal.get(Calendar.DAY_OF_WEEK);

        switch (dia){
            case 1:
                return "Diumenge";
            case 2:
                return "Dilluns";
            case 3:
                return "Dimarts";
            case 4:
                return "Dimecres";
            case 5:
                return "Dijous";
            case 6:
                return "Divendres";
            default:
                return "Dissabte";
        }
    }

    @Override
    public int getItemCount() {
        return cites.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout layoutData, layoutDades;
        private TextView tvDiaSetmanaNom;
        private TextView tvDiaSetmanaData;

        private TextView tvHora;
        private TextView tvPoblacio;
        private  TextView tvTipusSinistre;

        private TextView tvIdSinistre;

        private ImageView ivEstatInforme;



        public ViewHolder(View row) {
            super(row);
            layoutData = (LinearLayout) row.findViewById(R.id.layoutData);
            layoutDades = (LinearLayout) row.findViewById(R.id.layoutDades);
            tvDiaSetmanaNom = (TextView)  row.findViewById(R.id.tvDiaSetmanaText);
            tvDiaSetmanaData = (TextView)  row.findViewById(R.id.tvDiaSetmanaData);
            tvHora = (TextView)  row.findViewById(R.id.tvHoraSinistre);
            tvPoblacio = (TextView)  row.findViewById(R.id.tvPoblacio);
            tvTipusSinistre = (TextView)  row.findViewById(R.id.tvTipusSinistre);
            tvIdSinistre = (TextView)  row.findViewById(R.id.tvIdSinistre);
            ivEstatInforme = (ImageView)  row.findViewById(R.id.ivEstatInforme);

        }
    }


    //----------------------------------------


}
