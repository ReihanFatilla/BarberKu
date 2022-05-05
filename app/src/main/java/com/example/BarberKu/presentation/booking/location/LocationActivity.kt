package com.example.BarberKu.presentation.booking.location

import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.BarberKu.databinding.ActivityLocationBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class LocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityLocationBinding

    private var latitude: Double = 0.toDouble()
    private var longitude: Double = 0.toDouble()

    private lateinit var lastLocation: Location
    private var marker: Marker? = null


    //Location
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        initView()

        // Request runtime permission
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkLocationPermission()){
                buildLocationRequest()
                buildLocationCallback()

                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback,
                    Looper.myLooper()!!
                )
            } else {
                buildLocationRequest()
                buildLocationCallback()

                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback,
                    Looper.myLooper()!!)
            }


        }

    }

    private fun initView() {
        binding.apply{
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun buildLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval= 5000
        locationRequest.fastestInterval = 3000
        locationRequest.smallestDisplacement = 10f
    }

    private fun buildLocationCallback() {
        locationCallback = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
                lastLocation = locationResult.locations.get(locationResult.locations.size-1)

                if(marker != null){
                    marker!!.remove()
                }

                latitude = lastLocation.latitude
                longitude = lastLocation.longitude

                val latLng = LatLng(latitude, longitude)
                val markerOptions = MarkerOptions()
                    .position(latLng)
                    .title("Lokasi Anda")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                marker = mMap.addMarker(markerOptions)

                // Move Camera
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                mMap.animateCamera(CameraUpdateFactory.zoomTo(11f))
            }
        }
    }

    private fun checkLocationPermission(): Boolean {
        if(ContextCompat.checkSelfPermission(this,
                ACCES_FINE_LOCATION) != PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, ACCES_FINE_LOCATION))
                ActivityCompat.requestPermissions(this, arrayOf(
                    ACCES_FINE_LOCATION
                ), MY_PERMISSION_CODE)
            else {
                ActivityCompat.requestPermissions(this, arrayOf(
                    ACCES_FINE_LOCATION
                ), MY_PERMISSION_CODE)
                return false
                }
        } else {
            return true
        }
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setPadding(15, 15, 15, 15);
        // move the zoom control to center

        // Google Play Map Services
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this,
                    ACCES_FINE_LOCATION) == PERMISSION_GRANTED){
                mMap.isMyLocationEnabled = true
            }
        } else {
            mMap.isMyLocationEnabled = true
        }

        //Enable zoom control
        mMap.uiSettings.isZoomControlsEnabled = true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            MY_PERMISSION_CODE -> {
                if(grantResults.size > 0 && grantResults[0] == PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(this, ACCES_FINE_LOCATION) == PERMISSION_GRANTED){
                        if(checkLocationPermission()){
                            buildLocationRequest()
                            buildLocationCallback()

                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback,
                                Looper.myLooper()!!)
                            mMap.isMyLocationEnabled = true
                        }
                    }
                    else {
                        Toast.makeText(applicationContext, "Permission Denied!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun nearbyBarbershop(){

    }



    override fun onDestroy() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        super.onDestroy()

    }

    companion object{
        private const val MY_PERMISSION_CODE = 1000
        private const val ACCES_FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION
        private  const val PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED
    }
}