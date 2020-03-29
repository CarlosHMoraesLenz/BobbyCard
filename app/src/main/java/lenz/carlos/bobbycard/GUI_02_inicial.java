package lenz.carlos.bobbycard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GUI_02_inicial extends AppCompatActivity {

    //instancia dos atributos
    private ListView list_inicial;
    private Button btnCadNewPet;

    private PetDAO pDAO; //instância responsável pela persistência dos dados

    private ArrayList<Pet> prodAux; //Lista de contatos cadastrados no BD
    private PetsAdapterTelaInicial adapter; //adapter para receber os dados

    //Guarda a posição selecionada
    private int posSelec = -1;

    //Menu de Contexto
    private static final int ALTERAR = 0;
    private static final int DELETAR = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_02__inicial);

        //instancia dos componentes da activity
        list_inicial = findViewById(R.id.list_inicia);
        btnCadNewPet = findViewById(R.id.btnCadNewPet);

        pDAO = new PetDAO(this);
        pDAO.abrirBanco();
        prodAux = pDAO.consultar();
        adapter = new PetsAdapterTelaInicial(this,prodAux);

        list_inicial.setAdapter(adapter);

        //Notificando o adapter para atualização dos dados na tela
        adapter.notifyDataSetChanged();

        //instancia do click do Botão cadastrar
        btnCadNewPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaNewCad = new Intent(GUI_02_inicial.this, GUI_03_cadNewPet.class);
                startActivity(telaNewCad);
            }
        });

        list_inicial.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posSelec = position;

                Pet p = prodAux.get(posSelec);
                Intent ittc = new Intent(GUI_02_inicial.this, GUI_04_petCadastrado.class);
                ittc.putExtra("p",p); //Não esquecer de implementar Serializable na Classe Produto
                ittc.putExtra("acao","alterar");
                startActivity(ittc);
            }
        });
        list_inicial.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                posSelec = position;
                return false;
            }
        });

        //Registrando menu de contexto para a ListView
        registerForContextMenu(list_inicial);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Menu");
        menu.addSubMenu(DELETAR, DELETAR, 100, "Deletar");
        menu.addSubMenu(ALTERAR, ALTERAR, 200, "Alterar");
    }//fecha onCreate

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case DELETAR:

                AlertDialog.Builder msg = new AlertDialog.Builder(GUI_02_inicial.this);
                msg.setTitle("Alerta");
                msg.setMessage("Você tem certeza que deseja excluir?");
                msg.setIcon(R.mipmap.ic_launcher);
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Resgatando o produto selecionado pelo usuario
                        Pet p = prodAux.get(posSelec);
                        //Removendo do Banco
                        pDAO.excluir(p);
                        //Removendo do ArrayList
                        prodAux.remove(p);

                        Toast.makeText(
                                getBaseContext(),
                                "Pet Excluído com sucesso!",
                                Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();
                        //Notificando o adapter
                        adapter.notifyDataSetChanged();
                    }
                });

                msg.setNeutralButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(
                                getBaseContext(),
                                "Pet não foi excluído!",
                                Toast.LENGTH_LONG).show();
                    }
                });
                msg.show();
                break;

            case ALTERAR:
                //Resgatando o produto selecionado pelo usuario
                Pet p = prodAux.get(posSelec);
                //Enviando para tela de cadastro (alterar)
                Intent it = new Intent(GUI_02_inicial.this, GUI_03_cadNewPet.class);
                it.putExtra("p",p); //Não esquecer de implementar Serializable na Classe Produto
                it.putExtra("acao","alterar");
                startActivity(it);
                finish();
                break;
           }
        return super.onContextItemSelected(item);
    }
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
