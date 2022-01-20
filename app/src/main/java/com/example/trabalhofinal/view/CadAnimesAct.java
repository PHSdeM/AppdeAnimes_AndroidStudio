package com.example.trabalhofinal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.conexao.BancoDados;
import com.example.trabalhofinal.model.Animes;

import java.util.ArrayList;
import java.util.List;

public class CadAnimesAct extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private TextView id;
    private EditText nome, temp;
    private Spinner combo;
    private Button cadastrarA;
    private Button fechar;

    String[] genero = {"Ação", "Comedia","Romance", "Shounen ", "Seinen", "Horror"};
    List<Animes> lista = new ArrayList<Animes>();
    Animes ani = new Animes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_animes);



        id = (TextView)findViewById(R.id.lbld);
        nome = (EditText) findViewById(R.id.txtNome);
        temp = (EditText)findViewById(R.id.txtNTemporadasCad);
        combo = (Spinner) findViewById(R.id.cbxGenero);
        cadastrarA = (Button) findViewById(R.id.btnCadAnimes);
        fechar = (Button) findViewById(R.id.btnFechar);


        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, android.R.id.text1,genero);
        combo.setAdapter(adp);

        combo.setOnItemSelectedListener(this);
        cadastrarA.setOnClickListener(this);
        fechar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCadAnimes:
                String resp= "";
                BancoDados bd = new BancoDados(this);
                ani.setNome(nome.getText().toString());
                ani.setGenero(combo.getSelectedItem().toString());
                ani.setTemporadas(Integer.parseInt(temp.getText().toString()));
                resp = bd.InserirAnimes(ani);
                Toast.makeText(this,resp, Toast.LENGTH_LONG).show();
                break;

            case R.id.btnFechar:
                finish();
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
        String item = (String)combo.getItemAtPosition(i);
        ani.setGenero(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
