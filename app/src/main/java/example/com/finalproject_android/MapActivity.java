package example.com.finalproject_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback  {

        private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(43.6539, -79.3842);
        mMap.addMarker(new MarkerOptions().position(sydney).title("5555 Main St. Toronto,ON"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

//    public void Add_Location(View view) {
//
//
//        EditText lan_text = findViewById(R.id.lan);
//        EditText lat_text = findViewById(R.id.lat);
//        EditText location = findViewById(R.id.center_name);
//
//        String s = lan_text.getText().toString();
//        Double lan = Double.parseDouble(s);
//        Double lat = Double.parseDouble(lat_text.getText().toString());
//
//
//
//        LatLng newCity = new LatLng(lat, lan);
//        mMap.addMarker(new MarkerOptions().position(newCity).title(location.getText().toString()));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(newCity));
//
//
//
//    }
}
