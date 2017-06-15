package infomila.info.peritapp.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import infomila.info.peritapp.R;
import infomila.info.peritapp.adapters.TrucadaAdapter;
import infomila.info.peritapp.asynctasks.GetSinistreAsyncTask;
import infomila.info.peritapp.model.SinistreApp;
import infomila.info.peritapp.model.TrucadaApp;

/**
 * Created by Mr. Robot on 14/06/2017.
 */

public class SinistreReadOnlyActivity extends AppCompatActivity {

    private Context context;
    private TextView tvNumSinistre, tvTipusSinistre, tvDataObertura, tvDataAssignacio,
            tvDescripcio, tvClient, tvTipusHabitatge, tvPoblacio, tvAdreca, tvDataInici, tvDataFi;
    private RecyclerView rvTrucades;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinistre);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        context = this;

        tvNumSinistre = (TextView) findViewById(R.id.tvNumSinistre);
        tvTipusSinistre = (TextView) findViewById(R.id.tvTipusSinistre);
        tvDataObertura = (TextView) findViewById(R.id.tvDataObertura);
        tvDataAssignacio = (TextView) findViewById(R.id.tvDataAssignacio);
        tvDescripcio = (TextView) findViewById(R.id.tvDescripcio);
        tvClient = (TextView) findViewById(R.id.tvClient);
        tvTipusHabitatge = (TextView) findViewById(R.id.tvTipusHabitatge);
        tvPoblacio = (TextView) findViewById(R.id.tvPoblacio);
        tvAdreca = (TextView) findViewById(R.id.tvAdreca);
        tvDataInici = (TextView) findViewById(R.id.tvDataInici);
        tvDataFi = (TextView) findViewById(R.id.tvDataFi);

        rvTrucades = (RecyclerView) findViewById(R.id.rvTrucades);


        int idSinistre = getIntent().getIntExtra("idSinistre",-1);

        if(idSinistre > 0){
            GetSinistreAsyncTask task = new GetSinistreAsyncTask(context);
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,idSinistre);
        }

    }

    public void onSinistreDownloaded(SinistreApp sinistreApp) {
        tvNumSinistre.setText(Integer.toString(sinistreApp.getNumero()));
        tvTipusSinistre.setText(sinistreApp.getTipusSinistre().toString());
        tvDataObertura.setText(sdf.format(sinistreApp.getDataObertura()));
        tvDataAssignacio.setText(sdf.format(sinistreApp.getDataTancament()));
        tvDescripcio.setText(sinistreApp.getDescripcio());
        tvClient.setText(sinistreApp.getPolissaApp().getClient());
        tvTipusHabitatge.setText(sinistreApp.getPolissaApp().getTipusHabitatge().toString());
        tvPoblacio.setText(sinistreApp.getPolissaApp().getPoblacio());
        tvAdreca.setText(sinistreApp.getPolissaApp().getLiniaAdreca());
        tvDataInici.setText(sdf.format(sinistreApp.getPolissaApp().getDataInici()));
        tvDataFi.setText(sdf.format(sinistreApp.getPolissaApp().getDataFi()));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        rvTrucades.setLayoutManager(manager);



        List<TrucadaApp> trucades = new ArrayList();
        Iterator<TrucadaApp> itTrucades = sinistreApp.getTrucades();

        while (itTrucades.hasNext()){
            trucades.add(itTrucades.next());
        }

        System.out.println("Trucades Size: " + trucades.size() );

        TrucadaAdapter adapter = new TrucadaAdapter(this, trucades);
        rvTrucades.setAdapter(adapter);



    }
}
