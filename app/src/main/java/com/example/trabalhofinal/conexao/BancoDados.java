package com.example.trabalhofinal.conexao;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import androidx.annotation.Nullable;

        import com.example.trabalhofinal.model.Animes;

        import java.util.ArrayList;
        import java.util.List;

public class BancoDados extends SQLiteOpenHelper {
    private final static String banconome = "db_animes";
    private final static int versaoBanco = 1;


    public BancoDados(@Nullable Context context){
        super(context, banconome, null, versaoBanco);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbanimes= "CREATE TABLE table_animes(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT, " +
                "genero TEXT, " +
                "temporada INTEGER)";

        db.execSQL(dbanimes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1 ) {
        String dropTable= "DROP TABLE table_animes";
        db.execSQL(dropTable);
        onCreate(db);
    }

    public String InserirAnimes(Animes animes){
        long result;

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", animes.getNome());
        values.put("genero", animes.getGenero());
        values.put("Temporada", animes.getTemporadas());

        result = db.insert("table_animes", null, values);
        db.close();
        if(result == -1) {
            return "Erro ao inserir anime no banco de dados";

        }else{
            return "Inserido com sucesso no banco de dados";
        }

    }


    public String AlterarAnimes(Animes animes){
        long result;

        SQLiteDatabase db = getWritableDatabase();
        String alt = "id=" + animes.getId();
        ContentValues values = new ContentValues();
        values.put("nome", animes.getNome());
        values.put("genero", animes.getGenero());
        values.put("Temporada", animes.getTemporadas());

        result = db.update("table_animes",values, alt,null);
        db.close();
        if(result == -1) {
            return "Erro ao alterar anime no banco de dados";

        }else{
            return "Alterado com sucesso no banco de dados";
        }

    }


    public List<Animes> getAnimes() {

        List<Animes> animes = new ArrayList<Animes>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from table_animes";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Animes a = new Animes();
                a.setId(cursor.getInt(0));
                a.setNome(cursor.getString(1));
                a.setGenero(cursor.getString(2));
                a.setTemporadas(cursor.getInt(3));

                animes.add(a);
            } while (cursor.moveToNext());
        }
        db.close();
        return animes;
    }

    public String excluirAnimes(int id ){
        long result;

        SQLiteDatabase db = getReadableDatabase();
        String wheredelete = "id=" + id;
        result = db.delete("table_animes",wheredelete,null);
        db.close();
        if(result == -1) {
            return  "Erro ao excluir anime no banco de dados";

        }else{
            return "Excluido com sucesso no banco de dados";
        }
    }

}