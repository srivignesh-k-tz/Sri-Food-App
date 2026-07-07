    package com.example.kupa;

    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.ImageView;
    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import com.example.kupa.bottomsheet.BottomSheetDialog;
    import android.Manifest;
    import android.content.pm.PackageManager;
    import android.location.Location;
    import android.widget.TextView;
    import androidx.annotation.NonNull;
    import androidx.core.app.ActivityCompat;
    import androidx.core.content.ContextCompat;
    import com.google.android.gms.location.FusedLocationProviderClient;
    import com.google.android.gms.location.LocationCallback;
    import com.google.android.gms.location.LocationRequest;
    import com.google.android.gms.location.LocationResult;
    import com.google.android.gms.location.LocationServices;
    import com.google.android.gms.maps.CameraUpdateFactory;
    import com.google.android.gms.maps.GoogleMap;
    import com.google.android.gms.maps.MapView;
    import com.google.android.gms.maps.model.LatLng;
    import com.google.android.gms.maps.model.MarkerOptions;

    public class location extends AppCompatActivity {

        private MapView mapView;
        private GoogleMap mMap;
        private FusedLocationProviderClient fusedLocationClient;
        private LocationCallback locationCallback;
        private LocationRequest locationRequest;
        TextView put_street, put_city, put_pincode;

        SharedPreferences sharedPreferences;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_location);

            put_street = findViewById(R.id.put_street);
            put_city = findViewById(R.id.put_city);
            put_pincode = findViewById(R.id.put_pincode);

            ImageView show = findViewById(R.id.show);
            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BottomSheetDialog bottomSheet = new BottomSheetDialog();
                    bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
                }
            });
            ImageView back = findViewById(R.id.backs);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(location.this, New.class);
                    intent.putExtra("openFragment", "profile");
                    startActivity(intent);
                    finish();
                }
            });


            mapView = findViewById(R.id.map_view);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this::onMapReady);

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

            locationRequest = LocationRequest.create();
            locationRequest.setInterval(2000);
            locationRequest.setFastestInterval(2000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }
                    for (Location location : locationResult.getLocations()) {
                        if (mMap != null) {
                            LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.clear();
                            mMap.addMarker(new MarkerOptions().position(currentLatLng).title("You are here"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
                        }
                    }
                }
            };

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                startLocationUpdates();
            }

            sharedPreferences = getSharedPreferences("Edit Address", Context.MODE_PRIVATE);

        }

        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng defaultLocation = new LatLng(11.666500423402336, 78.14310010531909);
            mMap.addMarker(new MarkerOptions().position(defaultLocation).title("Marker in Salem"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation));
        }

        private void startLocationUpdates() {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }

        @Override
        protected void onResume() {
            super.onResume();
            mapView.onResume();
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            }

            String street = sharedPreferences.getString("street", "Post, Street");
            String city = sharedPreferences.getString("city", "Taluk, District");
            String pincode = sharedPreferences.getString("pincode", "Pincode");

            put_street.setText(street);
            put_city.setText(city);
            put_pincode.setText(pincode);
        }

        @Override
        protected void onPause() {
            super.onPause();
            mapView.onPause();
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            mapView.onDestroy();
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            mapView.onSaveInstanceState(outState);
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            mapView.onLowMemory();
        }
        @Override
        public void onBackPressed() {
            Intent intent = new Intent(location.this, New.class);
            intent.putExtra("openFragment", "profile");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            }
        }
    }