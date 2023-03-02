package com.example.sintronico.Ui.Perfil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sintronico.R;

public class PedirEmailActivity extends AppCompatActivity {
    private Button btnEnviarPedido;
    private EditText edEmailPedido;
    private PedirEmailViewModel pedirEmailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pedir_email);

        pedirEmailViewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(PedirEmailViewModel.class);

        pedirEmailViewModel.getBandera().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                finish();
            }
        });

        InicializarVista();
    }

    public void InicializarVista()
    {
        btnEnviarPedido=findViewById(R.id.btnEnviarPedido);
        edEmailPedido=findViewById(R.id.edEmailPedido);

        btnEnviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedirEmailViewModel.EnviarPedido(edEmailPedido.getText().toString());
            }
        });
    }
}
