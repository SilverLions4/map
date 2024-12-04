package com.example.mapas

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val markers = mutableListOf<Marker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configura el fragmento del mapa
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Vincular botones
        val addMarkerButton: Button = findViewById(R.id.addMarkerButton)
        val removeMarkerButton: Button = findViewById(R.id.removeMarkerButton)

        // Listener para agregar marcador
        addMarkerButton.setOnClickListener {
            addMarker()
        }

        // Listener para eliminar marcadores
        removeMarkerButton.setOnClickListener {
            removeMarkers()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Centrar el mapa en una ubicación inicial
        val initialLocation = LatLng(19.432608, -99.133209) // Coordenadas de Ciudad de México
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 12f))

        // Configurar controles de zoom
        mMap.uiSettings.isZoomControlsEnabled = true
    }

    private fun addMarker() {
        // Agregar un marcador en la posición actual de la cámara
        val position = mMap.cameraPosition.target
        val marker = mMap.addMarker(
            MarkerOptions()
                .position(position)
                .title("Nuevo Marcador")
        )
        marker?.let { markers.add(it) }
    }

    private fun removeMarkers() {
        // Eliminar todos los marcadores
        for (marker in markers) {
            marker.remove()
        }
        markers.clear()
    }
}
