package tnt.franc.mimap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class Mapa extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText edtnombre,txtLatitud, txtLongitud;
    GoogleMap mMap;
    private List<Ubicacion> ListUbicacion = new ArrayList<Ubicacion>();
    private List<String> ListUbicacionName = new ArrayList();
    ArrayAdapter<Ubicacion> ArrayAdapterUbicacion;
    ArrayAdapter<String> arrayAdapterString;
    Button buttonAgregar;
    ListView ListadoUbi;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        edtnombre=findViewById(R.id.edtnombre);
        txtLatitud=findViewById(R.id.txtLatitud);
        txtLongitud=findViewById(R.id.txtLongitud);
        buttonAgregar=findViewById(R.id.buttonAgregar);

        ListadoUbi=findViewById(R.id.ListadoUbi);
        inicializarFireBase();
        listarUbi();

        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ubicacion ubicacion = new Ubicacion();

                ubicacion.setIdUbi(UUID.randomUUID().toString());
                ubicacion.setNombre(edtnombre.getText().toString());
                ubicacion.setLatitud(txtLatitud.getText().toString());
                ubicacion.setLongitud(txtLongitud.getText().toString());
                databaseReference.child("Ubicacion").child(ubicacion.getIdUbi()).setValue(ubicacion);


            }
        });
    }

    private void listarUbi() {
        databaseReference.child("Ubicacion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ListUbicacion.clear();
                for (DataSnapshot objs : snapshot.getChildren()){
                    Ubicacion li =objs.getValue(Ubicacion.class);
                    ListUbicacion.add(li);
                    ListUbicacionName.add(""+li.getNombre()+" "+li.getLatitud()+" "+li.getLongitud()+" ");
                    arrayAdapterString =new ArrayAdapter<String>(Mapa.this, android.R.layout.simple_expandable_list_item_1,ListUbicacionName);
                    ListadoUbi.setAdapter(arrayAdapterString);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFireBase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng Arauco = new LatLng(-36.606097, -72.102550);
        mMap.addMarker(new MarkerOptions().position(Arauco).title("Bicicletero Arauco"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Arauco));


        LatLng SgtAldea = new LatLng(-36.611106, -72.099266);
        mMap.addMarker(new MarkerOptions().position(SgtAldea).title("sgto.Aldea y Maipon"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SgtAldea));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText(String.valueOf(latLng.latitude));
        txtLongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng Arauco = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(Arauco).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Arauco));
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText(String.valueOf(latLng.latitude));
        txtLongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng Arauco = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(Arauco).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Arauco));
    }
}