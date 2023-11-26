package com.posomo.masil

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MasilApplication : Application() {

	init{
		instance = this
	}

	companion object {
		lateinit var instance: MasilApplication

		fun getApplicationContext() : Context {
			return instance.applicationContext
		}
	}
}