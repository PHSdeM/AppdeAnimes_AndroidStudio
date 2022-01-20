package com.example.trabalhofinal.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.conexao.BancoDados;
import com.example.trabalhofinal.model.Animes;

public class AltAnimesAct extends AppCompatActivity  implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    private TextView id;
    private EditText nome, temp;
    private Spinner combo;
    private Button alterar;
    private Button fechar;

    String[] genero = {"Ação", "Comedia","Romance", "Shounen ", "Seinen", "Horror"};
    Animes ani = new Animes();
    String resp= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_animes);


        id = (TextView)findViewById(R.id.lbldAlt);
        nome = (EditText) findViewById(R.id.txtNomeAlt);
        temp = (EditText)findViewById(R.id.txtTemporadasAlt);
        combo = (Spinner) findViewById(R.id.cbxGeneroAlt);
        alterar = (Button) findViewById(R.id.btnAnimesAlt);
        fechar = (Button) findViewById(R.id.btnFecharAlt);

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, android.R.id.text1,genero);
        combo.setAdapter(adp);

        ani = (Animes) getIntent().getSerializableExtra("pegarAnimes");
        id.setText("" + ani.getId());
        nome.setText(ani.getNome());
        temp.setText(ani.getTemporadas());


        combo.setOnItemSelectedListener(this);
        alterar.setOnClickListener(this);
        fechar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAnimesAlt:

                BancoDados bd = new BancoDados(this);
                ani.setId(Integer.parseInt(id.getText().toString()));
                ani.setNome(nome.getText().toString());
                ani.setGenero(combo.getSelectedItem().toString());
                ani.setTemporadas(Integer.parseInt(temp.getText().toString()));
                resp = bd.AlterarAnimes(ani);
                Toast.makeText(this,resp, Toast.LENGTH_LONG).show();
                break;

            case R.id.btnExcluirAlt:
                BancoDados sa = new BancoDados(this);
                int pegarId = Integer.parseInt(id.getText().toString());
                AlertDialog.Builder msg = new AlertDialog.Builder(this);
                msg.setTitle("Excluir");
                msg.setMessage("Você tem certeza que quer excluir esse anime?" + nome.getText().toString());
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        resp = sa.excluirAnimes(pegarId);
                        Toast.makeText(getApplicationContext(),resp, Toast.LENGTH_LONG).show();
                    }
                });
                msg.setNegativeButton("Não", null);
                msg.show();
                break;

            case R.id.btnFecharAlt:
                finish();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        String item = (String)combo.getItemAtPosition(i);
        ani.setGenero(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}