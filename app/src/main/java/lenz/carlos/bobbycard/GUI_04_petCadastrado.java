package lenz.carlos.bobbycard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GUI_04_petCadastrado extends AppCompatActivity {

    private TextView nometc ;
    private TextView chiptc ;
    private TextView especietc ;
    private TextView racatc ;
    private TextView aNasctc ;
    private TextView sexotc ;
    private TextView portetc;
    private TextView pesotc ;
    private TextView alturatc ;
    private TextView pelagemtc ;
    private TextView resptc ;
    private TextView fonetc ;
    private TextView enderecotc ;

    private Pet p;
    //Banco
    private PetDAO pDAO; //instância responsável pela persistência dos dados
    private ArrayList<Pet> prodAux; //Lista de contatos cadastrados no BD

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, GUI_02_inicial.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, GUI_02_inicial.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_04_petcadastrado);
        //Mostrar o botão
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Ativar o botão
        getSupportActionBar().setHomeButtonEnabled(true);
        //Titulo para ser exibido na sua Action Bar em frente à seta
        getSupportActionBar().setTitle("Cadastro");



        //Instanciando e abrindo o banco
        pDAO = new PetDAO(this);
        pDAO.abrirBanco();

        nometc = findViewById(R.id.tvnome);
        chiptc = findViewById(R.id.tvchip);
        especietc = findViewById(R.id.tvespecie);
        racatc = findViewById(R.id.tvraca);
        aNasctc = findViewById(R.id.tvanonasc);
        sexotc = findViewById(R.id.tvsexo);
        portetc = findViewById(R.id.tvporte);
        alturatc = findViewById(R.id.tvaltura);
        pesotc = findViewById(R.id.tvpeso);
        pelagemtc = findViewById(R.id.tvpelo);
        resptc = findViewById(R.id.tvresp);
        fonetc = findViewById(R.id.tvfone);
        enderecotc = findViewById(R.id.tvendereco);

        String acao = getIntent().getStringExtra("acao");

        if(acao != null){

            Pet p = (Pet) getIntent().getSerializableExtra("p");
            nometc.setText(String.valueOf(p.getNome()));
            chiptc.setText(String.valueOf(p.getChip()));
            especietc.setText(String.valueOf(p.getEspecie()));
            racatc.setText(String.valueOf(p.getRaca()));
            aNasctc.setText(String.valueOf(p.getAnoNascimento()));
            sexotc.setText(String.valueOf(p.getSexo()));
            portetc.setText(String.valueOf(p.getPorte()));
            pesotc.setText(String.valueOf(p.getPeso()));
            alturatc.setText(String.valueOf(p.getAltura()));
            pelagemtc.setText(String.valueOf(p.getPelagem()));
            resptc.setText(String.valueOf(p.getResponsavel()));
            fonetc.setText(String.valueOf(p.getFone()));
            enderecotc.setText(String.valueOf(p.getEndereco()));

        }

    }//fecha onCreate



    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //Toda vez que a Activity receber o foco, ativamos a conexão com o bd
        pDAO.abrirBanco();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //Toda vez que a Activity perder o foco, encerramos a conexão com o bd
        pDAO.fecharBanco();
    }

}
