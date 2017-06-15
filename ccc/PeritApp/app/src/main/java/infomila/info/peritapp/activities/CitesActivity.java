package infomila.info.peritapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import infomila.info.peritapp.R;
import infomila.info.peritapp.adapters.CitaAdapter;
import infomila.info.peritapp.asynctasks.GetCitesAsyncTask;
import infomila.info.peritapp.asynctasks.LoginAsyncTask;
import infomila.info.peritapp.asynctasks.RefrescaSinistresAsyncTask;
import infomila.info.peritapp.model.ResumSinistreApp;
import infomila.info.peritapp.model.enums.ESTAT_INFORME;
import infomila.info.peritapp.model.enums.TIPUS_HABITATGE;
import infomila.info.peritapp.model.enums.TIPUS_SINISTRE;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class CitesActivity extends AppCompatActivity {

    private RecyclerView rvCites;
    private CitaAdapter adapter;
    private List<ResumSinistreApp> citesMaster = new ArrayList<>();
    private ImageView ivBtnFiltrar;
    private boolean mostrarTancats = false;
    private CheckBox chkBoxTancats;
    private Spinner spTipusSinistre;
    private EditText etPoblacio;
    private String tipusSinistreSelecionat = "Sense Selecció";
    private String poblacioSeleccionada = "";
    private boolean filtreOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cites);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rvCites = (RecyclerView) findViewById(R.id.rvSinistres);

        ivBtnFiltrar = (ImageView) findViewById(R.id.ivFiltrar);

        chkBoxTancats = (CheckBox) findViewById(R.id.chkBoxTancats);
        spTipusSinistre =(Spinner) findViewById(R.id.spTipusSinistre);
        etPoblacio = (EditText) findViewById(R.id.etPoblacio);

        fillSpinner();

        GetCitesAsyncTask task = new GetCitesAsyncTask(this);
        task.execute();

        RefrescaSinistresAsyncTask sinistresTask = new RefrescaSinistresAsyncTask(this);
        sinistresTask.execute();



    }

    private void fillSpinner() {

        List<String> tipusSinistres = new ArrayList<>();
        tipusSinistres.add("Sense Selecció");
        for(TIPUS_SINISTRE ts : TIPUS_SINISTRE.values()){
            tipusSinistres.add(ts.name());
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,R.layout.template_spinner, tipusSinistres);
        spTipusSinistre.setAdapter(adapterSpinner);



    }

    public void onClickFiltrar(View view){

        tipusSinistreSelecionat = spTipusSinistre.getSelectedItem().toString();       
        poblacioSeleccionada = etPoblacio.getText().toString();
        mostrarTancats = chkBoxTancats.isChecked();

        if(!filtreOn){
            ivBtnFiltrar.setImageResource(R.drawable.filtre_of);           
            filtreOn = true;
        }else{
            ivBtnFiltrar.setImageResource(R.drawable.filtre_on);         
            filtreOn = false;
        }

        filtrarDesferFiltre();
    }

    private void filtrarDesferFiltre() {
        List<ResumSinistreApp> resums = new ArrayList<>();
        if(filtreOn) {

            for(ResumSinistreApp rs : citesMaster) {
                if (mostrarTancats) {
                    if ((poblacioSeleccionada.equals("") || rs.getAdreca().equals(poblacioSeleccionada))
                            && (tipusSinistreSelecionat.equals("Sense Selecció") || rs.getTipusSinistre().toString().equals(tipusSinistreSelecionat))) {
                        resums.add(rs);
                    }
                } else {
                    if ((poblacioSeleccionada.equals("") || rs.getAdreca().equals(poblacioSeleccionada))
                            && (tipusSinistreSelecionat.equals("Sense Selecció") || rs.getTipusSinistre().toString().equals(tipusSinistreSelecionat))
                            && rs.getEstatInforme() != ESTAT_INFORME.TANCAT) {
                        resums.add(rs);
                    }
                }
            }

        }else {
            for(ResumSinistreApp r : citesMaster){
                if(r.getEstatInforme() != ESTAT_INFORME.TANCAT){
                    resums.add(r);
                }
            }

        }

        adapter = new CitaAdapter(this,resums);
        rvCites.setAdapter(adapter);

        rvCites.setHasFixedSize(true);
    }

    public void onGetCitesResult(ArrayList<ResumSinistreApp> cites) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        List<ResumSinistreApp> resums = new ArrayList<>();
        citesMaster = cites;

        for(ResumSinistreApp r : cites){
            if(r.getEstatInforme() != ESTAT_INFORME.TANCAT){
                resums.add(r);
            }
        }

        rvCites.setLayoutManager(manager);

        adapter = new CitaAdapter(this,resums);
        rvCites.setAdapter(adapter);

        rvCites.setHasFixedSize(true);
    }


    public void onInformeClosed(Integer codiInforme) {
        Toast.makeText(this,"Informe " + codiInforme + " tancat amb èxit",Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.desconnect) {
            Intent intent = new Intent(this, LoginAsyncTask.class);
            startActivity(intent);
            finish();
        }
        if (id == R.id.obrirReport) {
            Intent intent = new Intent(this, ReportActivity.class);
            startActivity(intent);
            finish();
        }
        if(id == R.id.refrescaSinistres){
            onRefreshSinistres();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onRefreshSinistres() {
        GetCitesAsyncTask task = new GetCitesAsyncTask(this);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        System.out.println("SINISTRES REFRESCATS!!!");

    }
}
