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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import infomila.info.peritapp.R;
import infomila.info.peritapp.adapters.EntradaAdapter;
import infomila.info.peritapp.asynctasks.GetInformeAsyncTask;
import infomila.info.peritapp.asynctasks.UpdateInformeAsyncTask;
import infomila.info.peritapp.model.EntradaInformeApp;
import infomila.info.peritapp.model.InformePericialApp;
import infomila.info.peritapp.model.enums.RESULTAT_PERITATGE;

/**
 * Created by Mr. Robot on 14/06/2017.
 */

public class ReportActivity extends AppCompatActivity {

    private Context context;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        webView = (WebView) findViewById(R.id.wvReport);

        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient());



        String url = "http://92.222.27.83:8080/jasperserver/flow.html?"
                + "_flowId=viewReportFlow&standAlone=true&_flowId=viewReportFlow&"
                + "reportUnit=/m2-agonzalez/projecte/resumPerits&"
                + "j_username=m2-agonzalez&j_password=47111651S";


        webView.loadUrl(url);


        context = this;



    }

}
