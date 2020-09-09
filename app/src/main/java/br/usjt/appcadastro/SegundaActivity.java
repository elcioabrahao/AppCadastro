package br.usjt.appcadastro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int REQUEST_CODE_GPS = 1001;
    private TextView locationTextView;
    private double latitudeAtual;
    private double longitudeAtual;
    private ImageView imagemRestaurante;
    private Button buttonRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);


        Log.d("CICLO_DE_VIDA","SegundaActivity --> onCreate");

        Intent intent = getIntent();
        String nome = intent.getStringExtra("NOMEUSUARIO");
        String cpf = intent.getStringExtra("CPFUSUARIO");
        String email = intent.getStringExtra("EMAILUSUARIO");
        TextView textViewNome = (TextView)findViewById(R.id.nomeSegundaTelaTextView);
        textViewNome.setText("Olá "+nome+ " seu CPF é: "+cpf +" e seu email é "+email);

        locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                latitudeAtual = lat;
                longitudeAtual = lon;
                imagemRestaurante.setVisibility(View.VISIBLE);
                buttonRestaurante.setVisibility(View.VISIBLE);
                locationTextView.setText(String.format("Lat: %f, Long: %f", lat,lon));
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle
                    extras) {
            }
            @Override
            public void onProviderEnabled(String provider) {
            }
            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        locationTextView = findViewById(R.id.locationTextView);
        imagemRestaurante = findViewById(R.id.imagemRestaurante);
        buttonRestaurante = findViewById(R.id.achaRestauranteButton);

    }

    public void voltar(View view) {
        finish();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("CICLO_DE_VIDA","SegundaActivity --> onStart");

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, locationListener);
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION}, 1001);
        }

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("CICLO_DE_VIDA","SegundaActivity --> onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("CICLO_DE_VIDA","SegundaActivity --> onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("CICLO_DE_VIDA","SegundaActivity --> onStop");
        locationManager.removeUpdates(locationListener);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("CICLO_DE_VIDA","SegundaActivity --> onDestroy");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull
            String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GPS){
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){

                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            0, 0, locationListener);
                }
            }
            else{

                Toast.makeText(this, getString(R.string.semgps),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void achaRestaurante(View view) {
        Uri gmmIntentUri =
                Uri.parse(String.format("geo:%f,%f?q=restaurantes",
                        latitudeAtual, longitudeAtual));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}