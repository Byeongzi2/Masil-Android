package com.posomo.masil

import android.app.Application
import android.content.Context
import com.naver.maps.map.NaverMapSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MasilApplication : Application() {

	init{
		instance = this
	}

	override fun onCreate() {
		super.onCreate()
		NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("9teby2rpq2")
	}

	companion object {
		lateinit var instance: MasilApplication

		fun getApplicationContext() : Context {
			return instance.applicationContext
		}
	}
}
