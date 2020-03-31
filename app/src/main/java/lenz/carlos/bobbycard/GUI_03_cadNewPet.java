package lenz.carlos.bobbycard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GUI_03_cadNewPet extends AppCompatActivity {

    //instancia atributos
    private EditText nome;
    private EditText chip;
    private EditText especie;
    private EditText raca;
    private EditText aNasc;
    private EditText sexo;
    private EditText porte;
    private EditText altura;
    private EditText peso;
    private EditText pelagem;
    private EditText resp;
    private EditText fone;
    private EditText endereco;
    private Button cad;
    private Button alt;

    //instancia classes
    private Pet p;
    private PetDAO pDAO;

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
        setContentView(R.layout.activity_03_cadnewpet);

        //Mostrar o botão
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Ativar o botão
        getSupportActionBar().setHomeButtonEnabled(true);

        //Titulo para ser exibido na sua Action Bar em frente à seta
        getSupportActionBar().setTitle("Cadastrar Novo Pet");


        View decorView = getWindow().getDecorView();
        int uiOptions =  View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);

        // instancia dos componentes da activity
        nome = findViewById(R.id.editNome);
        chip = findViewById(R.id.editChip);
        especie = findViewById(R.id.editEspecie);
        raca = findViewById(R.id.editRaca);
        aNasc = findViewById(R.id.editAnoNasc);
        sexo = findViewById(R.id.editSexo);
        porte = findViewById(R.id.editPorte);
        altura = findViewById(R.id.editAltura);
        peso = findViewById(R.id.editPeso);
        pelagem = findViewById(R.id.editPelo);
        resp = findViewById(R.id.editResp);
        fone = findViewById(R.id.editFone);
        endereco = findViewById(R.id.editEndereco);
        cad = findViewById(R.id.btnCadPet);
        alt = findViewById(R.id.btnAltPet);

        //Instanciando e abrindo o banco
        pDAO = new PetDAO(this);
        pDAO.abrirBanco();

        cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = new Pet();
                p.setNome(nome.getText().toString());
                p.setChip(chip.getText().toString());
                p.setEspecie(especie.getText().toString());
                p.setRaca(raca.getText().toString());
                p.setAnoNascimento(Integer.parseInt(aNasc.getText().toString()));
                p.setSexo(sexo.getText().toString());
                p.setPorte(porte.getText().toString());
                p.setAltura(Double.parseDouble(altura.getText().toString()));
                p.setPeso(Double.parseDouble(peso.getText().toString()));
                p.setPeso(Double.parseDouble(peso.getText().toString()));
                p.setPelagem(pelagem.getText().toString());
                p.setResponsavel(resp.getText().toString());
                p.setFone(fone.getText().toString());
                p.setEndereco(endereco.getText().toString());

                Toast.makeText(
                        getBaseContext(), "Novo Pet Cadastrado :)",
                        Toast.LENGTH_LONG).show();

                //Enviando para o método cadastrar
                pDAO.inserir(p);

                limpar();
                Intent telaPetCad = new Intent(GUI_03_cadNewPet.this, GUI_02_inicial.class);
                startActivity(telaPetCad);
            }
        });

        String acao = getIntent().getStringExtra("acao");

        if(acao != null){

            //Alterando Titulo para ser exibido na sua Action Bar em frente à seta
            getSupportActionBar().setTitle("Alterar Informações do Pet");
            //Habilitando a visualização do botão alterar e a desvisualização do botão cadastrar
            alt.setVisibility(View.VISIBLE);
            cad.setVisibility(View.INVISIBLE);
            //Desabilitando para a edição o campo do código
            nome.setEnabled(false);

            Pet p = (Pet) getIntent().getSerializableExtra("p");
            nome.setText(String.valueOf(p.getNome()));
            chip.setText(String.valueOf(p.getChip()));
            especie.setText(String.valueOf(p.getEspecie()));
            raca.setText(String.valueOf(p.getRaca()));
            aNasc.setText(String.valueOf(p.getAnoNascimento()));
            sexo.setText(String.valueOf(p.getSexo()));
            porte.setText(String.valueOf(p.getPorte()));
            peso.setText(String.valueOf(p.getPeso()));
            altura.setText(String.valueOf(p.getAltura()));
            pelagem.setText(String.valueOf(p.getPelagem()));
            resp.setText(String.valueOf(p.getResponsavel()));
            fone.setText(String.valueOf(p.getFone()));
            endereco.setText(String.valueOf(p.getEndereco()));

        }

        //Botão Alterar
        alt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = new Pet();
                p.setNome(nome.getText().toString());
                p.setChip(chip.getText().toString());
                p.setEspecie(especie.getText().toString());
                p.setRaca(raca.getText().toString());
                p.setAnoNascimento(Integer.parseInt(aNasc.getText().toString()));
                p.setSexo(sexo.getText().toString());
                p.setPorte(porte.getText().toString());
                p.setAltura(Double.parseDouble(altura.getText().toString()));
                p.setPeso(Double.parseDouble(peso.getText().toString()));
                p.setPelagem(pelagem.getText().toString());
                p.setResponsavel(resp.getText().toString());
                p.setFone(fone.getText().toString());
                p.setEndereco(endereco.getText().toString());
                Toast.makeText(
                        getBaseContext(),
                        "Informaçoes do Pet Alteradas com Sucesso! ",
                        Toast.LENGTH_LONG).show();

                //Enviando para o método alterar
                pDAO.alterar(p);

                Intent itvi = new Intent(GUI_03_cadNewPet.this,GUI_02_inicial.class);
                startActivity(itvi);

            }
        });//fecha Alterar
    }



    private void limpar() {
        nome.setText(null);
        chip.setText(null);
        especie.setText(null);
        raca.setText(null);
        aNasc.setText(null);
        sexo.setText(null);
        porte.setText(null);
        peso.setText(null);
        altura.setText(null);
        pelagem.setText(null);
        resp.setText(null);
        fone.setText(null);
        endereco.setText(null);
    }//fecha limpar

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
