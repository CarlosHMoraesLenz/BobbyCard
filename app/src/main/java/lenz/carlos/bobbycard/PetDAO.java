package lenz.carlos.bobbycard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PetDAO {

    private SQLiteDatabase database;
    private BaseDAO dbHelper;

    public PetDAO(Context context) {
        dbHelper = new BaseDAO(context);
    }//fecha PetsDAO

    public void abrirBanco(){
        database = dbHelper.getWritableDatabase();
    }

    public void fecharBanco(){
        dbHelper.close();
    }

    public long inserir(Pet p){
        ContentValues cv = new ContentValues();

        cv.put(BaseDAO.PET_NOME, p.getNome());
        cv.put(BaseDAO.PET_CHIPID, p.getChip());
        cv.put(BaseDAO.PET_ESPECIE, p.getEspecie());
        cv.put(BaseDAO.PET_RACA, p.getRaca());
        cv.put(BaseDAO.PET_ANONASC, p.getAnoNascimento());
        cv.put(BaseDAO.PET_SEXO, p.getSexo());
        cv.put(BaseDAO.PET_PORTE, p.getPorte());
        cv.put(BaseDAO.PET_PESO, p.getPeso());
        cv.put(BaseDAO.PET_ALTURA, p.getAltura());
        cv.put(BaseDAO.PET_PELAGEM, p.getPelagem());
        cv.put(BaseDAO.PET_RESP, p.getResponsavel());
        cv.put(BaseDAO.PET_FONE, p.getFone());
        cv.put(BaseDAO.PET_ENDERECO, p.getEndereco());

        return database.insert(BaseDAO.TBL_PET, null, cv);
    }//fecha inserir

    public String alterar(Pet p){

        String id = p.getNome();

        ContentValues cv = new ContentValues();
        cv.put(BaseDAO.PET_NOME, p.getNome());
        cv.put(BaseDAO.PET_CHIPID, p.getChip());
        cv.put(BaseDAO.PET_ESPECIE, p.getEspecie());
        cv.put(BaseDAO.PET_RACA, p.getRaca());
        cv.put(BaseDAO.PET_ANONASC, p.getAnoNascimento());
        cv.put(BaseDAO.PET_SEXO, p.getSexo());
        cv.put(BaseDAO.PET_PORTE, p.getPorte());
        cv.put(BaseDAO.PET_PESO, p.getPeso());
        cv.put(BaseDAO.PET_ALTURA, p.getAltura());
        cv.put(BaseDAO.PET_PELAGEM, p.getPelagem());
        cv.put(BaseDAO.PET_RESP, p.getResponsavel());
        cv.put(BaseDAO.PET_FONE, p.getFone());
        cv.put(BaseDAO.PET_ENDERECO, p.getEndereco());

        return String.valueOf(database.update(
                BaseDAO.TBL_PET,
                cv,
                BaseDAO.PET_NOME+"=?",
                new String[]{String.valueOf(id)}));
    }//fecha alterar

    public String excluir(Pet p){

        String id = p.getNome();

        return String.valueOf(database.delete(
                BaseDAO.TBL_PET,
                BaseDAO.PET_NOME+"=?",
                new String[]{String.valueOf(id)}));
    }//fecha excluir

    public ArrayList<Pet> consultar(){

        ArrayList<Pet> prodAux = new ArrayList<>();

        /* Consulta para trazer todos os dados de todas as
         *  colunas da tabela produto ordenados pelo nome */
        Cursor cursor = database.query(
                BaseDAO.TBL_PET,
                BaseDAO.TBL_PET_COLUNAS,
                null,
                null,
                null,
                null,
                BaseDAO.PET_NOME); //order by

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Pet p = new Pet();
            p.setNome(cursor.getString(0));
            p.setChip(cursor.getString(1));
            p.setEspecie(cursor.getString(2));
            p.setRaca(cursor.getString(3));
            p.setAnoNascimento(cursor.getInt(4));
            p.setSexo(cursor.getString(5));
            p.setPorte(cursor.getString(6));
            p.setPeso(cursor.getDouble(7));
            p.setAltura(cursor.getDouble(8));
            p.setPelagem(cursor.getString(9));
            p.setResponsavel(cursor.getString(10));
            p.setFone(cursor.getString(11));
            p.setEndereco(cursor.getString(12));
            cursor.moveToNext();
            prodAux.add(p);
        }//fecha while

        cursor.close();
        return prodAux;
    }//fecha consultar
}
