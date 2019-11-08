package ecosistemas.com.clase15domicianorincon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText correo;
    private EditText password;
    private Button registrar;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        password = findViewById(R.id.password);
        registrar = findViewById(R.id.registrar);
        lista = findViewById(R.id.lista);

        //Leer UN objeto
        FirebaseDatabase.getInstance().getReference()
                .child("usuarios")
                .child("-LtBcWCAvzunzDjxcByS")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Usuario moncada = dataSnapshot.getValue(Usuario.class);
                        //Toast.makeText(MainActivity.this,
                        //        moncada.getNombre(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        FirebaseDatabase.getInstance().getReference()
                .child("usuarios")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int counter = 0;
                        for(DataSnapshot child : dataSnapshot.getChildren()){
                            Usuario user = child.getValue(Usuario.class);
                            counter++;
                        }
                        Toast.makeText(MainActivity.this,
                                "Usuarios: "+counter,
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        Query query =  FirebaseDatabase.getInstance().getReference()
                .child("usuarios");

        FirebaseListOptions<Usuario> options =
                new FirebaseListOptions.Builder<Usuario>()
                .setLayout(R.layout.fila)
                .setQuery(query, Usuario.class)
                .build();

        FirebaseListAdapter<Usuario> adapter = new FirebaseListAdapter<Usuario>(options) {
            @Override
            protected void populateView(View v, Usuario model, int position) {
                TextView nombre = v.findViewById(R.id.fila_nombre);
                TextView correo = v.findViewById(R.id.fila_correo);
                nombre.setText(model.getNombre());
                correo.setText(model.getCorreo());
            }
        };
        lista.setAdapter(adapter);
        adapter.startListening();


    }

    public void registrarBtn(View view){

        String nombreString = nombre.getText().toString();
        String correoString = correo.getText().toString();
        String passwordString = password.getText().toString();
        Usuario usuario = new Usuario(
                nombreString,
                correoString,
                passwordString
        );
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuarios").push();
        myRef.setValue(usuario);





    }
}
