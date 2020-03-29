package lenz.carlos.bobbycard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PetsAdapterTelaCadastrado extends BaseAdapter {

    private Context context;
    private ArrayList<Pet> listpetCad;
    //private LayoutInflater inflater;

    public TextView nometc ;
    public TextView chiptc ;
    public TextView aNasctc ;
    public TextView alturatc ;
    public TextView pesotc ;
    public TextView pelagemtc ;
    public TextView especietc ;
    public TextView portetc;

    public PetsAdapterTelaCadastrado(Context context, ArrayList<Pet> listPet) {
        //super();
        this.context = context;
        this.listpetCad = listPet;

        //inflater = LayoutInflater.from(context);
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
        listpetCad.add(pt);
    }

    public void remove(Pet pt){
        listpetCad.remove(pt);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        //Fazer no braço
        return listpetCad.size();
    }

    @Override
    public Pet getItem(int position) {
        // TODO Auto-generated method stub
        //Fazer no braço
        return listpetCad.get(position);
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
            Pet pt = listpetCad.get(position);

            /*if(convertView==null) {
                convertView = inflater.inflate(R.layout.activity_04_petcadastrado, null);
            }*/

            //instâncias dos objetos da linha
            //Guarda a referencia dos objeto
            nometc = convertView.findViewById(R.id.tvnome);
            chiptc = convertView.findViewById(R.id.tvchip);
            especietc = convertView.findViewById(R.id.tvespecie);
            aNasctc = convertView.findViewById(R.id.tvanonasc);
            portetc = convertView.findViewById(R.id.tvporte);
            pesotc = convertView.findViewById(R.id.tvpeso);
            alturatc = convertView.findViewById(R.id.tvaltura);
            pelagemtc = convertView.findViewById(R.id.tvpelo);

            //Setando o pet na respectiva linha
            nometc.setText(" "+pt.getNome());
            chiptc.setText(" "+pt.getChip());
            especietc.setText(" "+pt.getEspecie());
            aNasctc.setText(" "+pt.getAnoNascimento());
            portetc.setText(" "+pt.getPorte());
            pesotc.setText(" "+pt.getPeso());
            alturatc.setText(" "+pt.getAltura());
            pelagemtc.setText(" "+pt.getPelagem());

            return convertView;
        } catch (Exception e) {
            // TODO: handle exception
            trace("Erro : " + e.getMessage());
        }//fecha catch
        return convertView;
    }//fecha getView
}//fecha classe
