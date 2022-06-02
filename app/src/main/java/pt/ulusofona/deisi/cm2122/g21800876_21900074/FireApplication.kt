package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.app.Application

class FireApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FireRepository.init(this,
            FireModelRoom(FireDatabase.getInstance(this).fireDao()),
            FireRetrofit(RetrofitBuilder.getInstance("https://api-dev.fogos.pt"))
        )
        FusedLocation.start(this)
    }

}