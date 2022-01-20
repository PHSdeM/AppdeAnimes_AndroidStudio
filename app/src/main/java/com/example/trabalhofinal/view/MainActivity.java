package com.example.trabalhofinal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.conexao.BancoDados;
import com.example.trabalhofinal.model.Animes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button cadastrar;
    private ListView lista;

    BancoDados bd = new BancoDados(this);
    List<Animes> listaAnimes = new ArrayList<Animes>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cadastrar = (Button)findViewById(R.id.btnCadastrar);
        lista = (ListView)findViewById(R.id.listAnimes);

        cadastrar.setOnClickListener(this);
        lista.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAnimes = bd.getAnimes();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaAnimes);
        lista.setAdapter(adp);
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, CadAnimesAct.class);
        startActivity(it);
    }

   @Override
    public void onItemClick(AdapterView<?> adapterView, View v, int i, long l){
        int pegaId = (int)lista.getItemIdAtPosition(i);

        Intent it = new Intent(this, AltAnimesAct.class);
        it.putExtra("pegarAnimes", listaAnimes.get(pegaId));
        startActivity(it);
   }
}