package lenz.carlos.bobbycard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.RadioButton;

public class BaseDAO extends SQLiteOpenHelper{

    //tabela Pet
    public static final String TBL_PET = "pet";
    public static final String PET_NOME = "nome";
    public static final String PET_CHIPID = "chipid";
    public static final String PET_ESPECIE = "especie";
    public static final String PET_RACA = "raca";
    public static final String PET_ANONASC = "anonasc";
    public static final String PET_SEXO = "sexo";
    public static final String PET_PORTE = "porte";
    public static final String PET_PESO = "peso";
    public static final String PET_ALTURA = "altura";
    public static final String PET_PELAGEM = "pelagem";
    public static final String PET_RESP = "responsavel";
    public static final String PET_FONE = "fone";
    public static final String PET_ENDERECO = "endereco";


    /* Colunas da Tabela Pet. São públicos para qualquer classe. */
    public static final String[] TBL_PET_COLUNAS = {
            BaseDAO.PET_NOME,
            BaseDAO.PET_CHIPID,
            BaseDAO.PET_ESPECIE,
            BaseDAO.PET_RACA,
            BaseDAO.PET_ANONASC,
            BaseDAO.PET_SEXO,
            BaseDAO.PET_PORTE,
            BaseDAO.PET_PESO,
            BaseDAO.PET_ALTURA,
            BaseDAO.PET_PELAGEM,
            BaseDAO.PET_RESP,
            BaseDAO.PET_FONE,
            BaseDAO.PET_ENDERECO};

    //Banco + name + version
    public static final String DATABASE_NAME = "bobby.sqlite";
    public static final int DATABASE_VERSION = 1;

    //DDL - criação da(s) tabela(s)
    public static final String CREATE_PET =
            "create table "+TBL_PET+"(" +
                    PET_NOME+" text primary key, "+
                    PET_CHIPID+" text , "+
                    PET_ESPECIE+" text not null, "+
                    PET_RACA +" text , "+
                    PET_ANONASC+" integer not null," +
                    PET_SEXO +" text not null, "+
                    PET_PORTE+" text not null, "+
                    PET_PESO+" double  , "+
                    PET_ALTURA+" double  , "+
                    PET_PELAGEM+" text  , "+
                    PET_RESP+" text  , "+
                    PET_FONE+" text  , "+
                    PET_ENDERECO+" text );";

      //DDL - exclusão da(s) tabela(s)
    public static final String DROP_PET =
            "drop table if exists " + TBL_PET;



    public BaseDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//fecha BaseDAO

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* Criando a tabela pet */
        db.execSQL(CREATE_PET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Excluindo a tabela caso ela exista
        db.execSQL(DROP_PET);
		/* chamando novamente o método
		   onCreate para recriar a tabela */
        onCreate(db);
    }
}//fecha classe