package pl.edu.uj.reviewexchange

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration

class InitRealm : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(config)

        Log.d("REALM STARTED", "realm")
    }


}

