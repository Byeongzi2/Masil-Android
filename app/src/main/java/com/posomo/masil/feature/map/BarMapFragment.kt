package com.posomo.masil.feature.map

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapOptions
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import com.posomo.masil.R
import com.posomo.masil.common.util.setStatusBarTextColor
import com.posomo.masil.common.util.setStatusBarTransparent
import com.posomo.masil.databinding.BottomSheetFragmentBarMapBinding
import com.posomo.masil.databinding.FragmentMapBinding
import com.posomo.masil.domain.model.Location
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BarMapFragment : Fragment(), OnMapReadyCallback {

    @Inject
    lateinit var assistedFactory: BarMapViewModel.Factory

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<BarMapViewModel> {
        val location = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(KEY_LAT_LNG, Location::class.java)
        } else {
            arguments?.getParcelable(KEY_LAT_LNG) as? Location
        }
        BarMapViewModel.provideFactory(assistedFactory, location)
    }
    private lateinit var naverMap: NaverMap
    private val locationSource: FusedLocationSource by lazy {
        FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }
    private val mapFragment by lazy {
        childFragmentManager.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance(
                NaverMapOptions()
                    .locationButtonEnabled(false)
                    .logoClickEnabled(false)
                    .compassEnabled(false)).also {
                childFragmentManager.beginTransaction().add(R.id.map, it).commit()
            }
    }
    private val behavior by lazy {
        BottomSheetBehavior.from(binding.clBottomSheet)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        activity?.run {
            setStatusBarTransparent()
            setStatusBarTextColor(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ActivityCompat.requestPermissions(
            requireActivity(),
            PERMISSIONS,
            LOCATION_PERMISSION_REQUEST_CODE
        )
        mapFragment.getMapAsync(this)
        initBinding()
        initBottomSheet()
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.uiSettings.isLocationButtonEnabled = true
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = if (viewModel.userLocation.value != null) {
            LocationTrackingMode.Follow
        } else {
            LocationTrackingMode.None
        }
        viewModel.userLocation.value?.let {
            val cameraUpdate = CameraUpdate
                .scrollTo(LatLng(it.latitude, it.longitude))
                .finishCallback { viewModel.onLoadMapFinish() }
            naverMap.moveCamera(cameraUpdate)
        }
        naverMap.locationOverlay.isVisible = true
        binding.locationButton.map = naverMap
        binding.logo.setMap(naverMap)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (locationSource.isActivated.not()) {
                viewModel.setUserLatLng(null, null)
            } else {
                naverMap.locationTrackingMode = LocationTrackingMode.Follow
                locationSource.lastLocation?.let {
                    viewModel.setUserLatLng(it.latitude, it.longitude)
                }
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun initBinding() = with(binding) {
        lifecycleOwner = this@BarMapFragment
        viewModel = this@BarMapFragment.viewModel
        searchViewClickListener = OnClickListener { TODO("Not yet implemented") }
    }

    private fun initBottomSheet() {
        behavior.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }

    companion object {
        private const val KEY_LAT_LNG = "latLan"
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        private val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        fun newInstance() = MapFragment()
        fun getBundle(location: Location?) = bundleOf(KEY_LAT_LNG to location)
    }
}