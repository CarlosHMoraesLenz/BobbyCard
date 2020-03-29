package lenz.carlos.bobbycard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PetsAdapterTelaInicial extends BaseAdapter {

    private Context context;
    private ArrayList<Pet> listpet;
    private LayoutInflater inflater;

    public TextView tvPetNome;
    public TextView tvPetChip;

    public PetsAdapterTelaInicial(Context context, ArrayList<Pet> listPet) {
        //super();
        this.context = context;
        this.listpet = listPet;

        inflater = LayoutInflater.from(context);
    }//fecha construtor

    //Chamar no braço também
    @Override
    public void notifyDataSetChanged() {
        // TODO Auto-generated method stub
        try {
            super.notifyDataSetChanged();
        } catch (Exception e) {
            // TODO: handle exception
            trace("Erro : " + e.getMessage());
        }
    }//fecha notifyDataSetChanged

    //Métodos responsáveis pelo envio de mensagem
    public void toast (String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    private void trace (String msg){
        toast(msg);
    }

    public void add(Pet pt){
        listpet.add(pt);
    }

    public void remove(Pet pt){
        listpet.remove(pt);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        //Fazer no braço
        return listpet.size();
    }

    @Override
    public Pet getItem(int position) {
        // TODO Auto-generated method stub
        //Fazer no braço
        return listpet.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        //Fazer no braço
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        try {
            Pet pt = listpet.get(position);

            if(convertView==null) {
                convertView = inflater.inflate(R.layout.list_inicial, null);
            }

            //instâncias dos objetos da linha
            //Guarda a referencia dos objeto
            tvPetNome = convertView.findViewById(R.id.tvPetNome);
            tvPetChip = convertView.findViewById(R.id.tvPetChip);

            //Setando o pet na respectiva linha
            tvPetNome.setText(" "+pt.getNome());
            tvPetChip.setText(" "+pt.getChip());

            return convertView;
        } catch (Exception e) {
            // TODO: handle exception
            trace("Erro : " + e.getMessage());
        }//fecha catch
        return convertView;
    }//fecha getView
}//fecha classe
