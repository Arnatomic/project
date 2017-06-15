package infomila.info.peritapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import infomila.info.peritapp.R;
import infomila.info.peritapp.adapters.EntradaAdapter;
import infomila.info.peritapp.asynctasks.DesaEntradaAsyncTask;
import infomila.info.peritapp.asynctasks.GetInformeAsyncTask;
import infomila.info.peritapp.asynctasks.UpdateInformeAsyncTask;
import infomila.info.peritapp.model.EntradaInformeApp;
import infomila.info.peritapp.model.InformePericialApp;
import infomila.info.peritapp.model.enums.RESULTAT_PERITATGE;

/**
 * Created by Mr. Robot on 14/06/2017.
 */

public class EntradaActivity extends AppCompatActivity {

    private Context context;
    private int idSinistre, numUltimaEntrada;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView ivFoto;
    private Bitmap imatgeEntrada;
    private EditText etDescripcio;
    private CheckBox chkPostReparacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        context = this;


        idSinistre = getIntent().getIntExtra("idSinistre",0);
        numUltimaEntrada = getIntent().getIntExtra("numEntrada",0);

        ivFoto = (ImageView) findViewById(R.id.ivFoto);
        etDescripcio = (EditText) findViewById(R.id.etDescripcio);
        chkPostReparacio = (CheckBox) findViewById(R.id.chPostReparacio);

        System.out.println("SIN: " + idSinistre + " E: " + numUltimaEntrada);

    }

    public void onClickCamera(View view){
        dispatchTakePictureIntent();
    }

    public void onClickDesarEntrada(View view){
        DesarEntrada();
    }


    public void onClickCancelar(View view){
        onBackPressed();
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imatgeEntrada = (Bitmap) extras.get("data");
            ivFoto.setImageBitmap(imatgeEntrada);
        }
    }



    private void DesarEntrada() {

        Date rightNow = new Date();
        String desc = etDescripcio.getText().toString();
        boolean postRep = chkPostReparacio.isChecked();
        byte[] imatge = new byte[]{};

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        if(imatgeEntrada != null){
            imatgeEntrada.compress(Bitmap.CompressFormat.PNG,100,stream);
            imatge=stream.toByteArray();
        }

        EntradaInformeApp entrada = new EntradaInformeApp(rightNow,desc,imatge,postRep);
        entrada.setNumero(idSinistre);

        System.out.println("ENTRADA:  " + entrada);

        DesaEntradaAsyncTask task = new DesaEntradaAsyncTask(context);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,entrada);

    }

    public void onSaveEntrada(Integer res) {
        if(res == 1) Toast.makeText(this,"Entrada desada Correctament.",Toast.LENGTH_SHORT).show();
        else Toast.makeText(this,"Error desant entrada.",Toast.LENGTH_SHORT).show();
    }
}
