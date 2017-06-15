package infomila.info.peritapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import infomila.info.peritapp.R;
import infomila.info.peritapp.adapters.EntradaAdapter;
import infomila.info.peritapp.adapters.TrucadaAdapter;
import infomila.info.peritapp.asynctasks.GetInformeAsyncTask;
import infomila.info.peritapp.asynctasks.GetSinistreAsyncTask;
import infomila.info.peritapp.asynctasks.UpdateInformeAsyncTask;
import infomila.info.peritapp.model.EntradaInformeApp;
import infomila.info.peritapp.model.InformePericialApp;
import infomila.info.peritapp.model.SinistreApp;
import infomila.info.peritapp.model.TrucadaApp;
import infomila.info.peritapp.model.enums.RESULTAT_PERITATGE;

import static infomila.info.peritapp.R.id.etImportCobert;
import static infomila.info.peritapp.R.id.spResultatPeritatge;
import static infomila.info.peritapp.R.id.spTipusSinistre;

/**
 * Created by Mr. Robot on 14/06/2017.
 */

public class InformeActivity extends AppCompatActivity {

    private Context context;
    private EditText etImportCobert, etDescripcio;
    private Spinner spResultat;
    private Button btnDesar, btnCancelar, btnNovaEntrada;
    private RecyclerView rvEntrades;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Integer idSinistre;
    private EntradaAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        context = this;

        etImportCobert = (EditText) findViewById(R.id.etImportCobert);
        etDescripcio = (EditText) findViewById(R.id.etDescripcio);

        spResultat = (Spinner) findViewById(R.id.spResultatPeritatge);

        btnDesar = (Button) findViewById(R.id.btnDesar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnNovaEntrada = (Button) findViewById(R.id.btnNovaEntrada);

        rvEntrades = (RecyclerView) findViewById(R.id.rvEntrades);

        fillSpinner();


        idSinistre = getIntent().getIntExtra("idSinistre",-1);

        if(idSinistre > 0){
            GetInformeAsyncTask task = new GetInformeAsyncTask(context);
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,idSinistre);
        }


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void fillSpinner() {

        List<String> resultatsPeritatges = new ArrayList<>();

        for(RESULTAT_PERITATGE ts : RESULTAT_PERITATGE.values()){
            resultatsPeritatges.add(ts.name());
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,R.layout.template_spinner, resultatsPeritatges);

        spResultat.setAdapter(adapterSpinner);

        spResultat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(spResultat.getSelectedItem().toString().equals("COBERT_PARCIAL")) etImportCobert.setEnabled(true);
                else etImportCobert.setEnabled(false);
                if(spResultat.getSelectedItem().toString().equals("SENSE_COBERTURA")) etImportCobert.setText("0.00");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void onInformeDownloaded(InformePericialApp informePericialApp) {
        if(informePericialApp != null) {
            if(informePericialApp.getImportCobert() != null){
                etImportCobert.setText((String.valueOf(informePericialApp.getImportCobert())));
            }else{
                etImportCobert.setText("0.00");
            }

            etDescripcio.setText(informePericialApp.getInforme());

            spResultat.setSelection(informePericialApp.getResultatPeritatge().ordinal());

            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);

            rvEntrades.setLayoutManager(manager);


            List<EntradaInformeApp> entrades = new ArrayList();
            Iterator<EntradaInformeApp> itEntrades = informePericialApp.getEntrades();

            while (itEntrades.hasNext()) {
                entrades.add(itEntrades.next());
            }

            System.out.println("Entrades Size: " + entrades.size());

            adapter = new EntradaAdapter(this, entrades);
            rvEntrades.setAdapter(adapter);

        }

    }

    public void onClickDesarInforme(View view){
        String resultatPeritatge = spResultat.getSelectedItem().toString();

        String desc = etDescripcio.getText().toString();
        String importCobert = etImportCobert.getText().toString();

        UpdateInformeAsyncTask task = new UpdateInformeAsyncTask(context);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,idSinistre.toString(),resultatPeritatge,desc,importCobert);
    }

    public void onClickNovaEntrada(View view){

        Intent intent = new Intent(context,EntradaActivity.class);
        intent.putExtra("idSinistre",idSinistre);
        intent.putExtra("numEntrada",adapter.getItemCount());
        startActivity(intent);
    }

    public void onDeletedEntrada(int res) {
        if(res == 1) Toast.makeText(this,"Entrada esborrada Correctament.",Toast.LENGTH_SHORT).show();
        else Toast.makeText(this,"Error esborrant entrada.",Toast.LENGTH_SHORT).show();
    }

    public void onUpdateInforme(Integer res) {
        System.out.println("RES UPD: " + res);
        if(res == 1) Toast.makeText(this,"Informe desat Correctament.",Toast.LENGTH_SHORT).show();
        else Toast.makeText(this,"Error desant informe.",Toast.LENGTH_SHORT).show();
    }
}
