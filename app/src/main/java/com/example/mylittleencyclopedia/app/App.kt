package com.example.mylittleencyclopedia.app

import android.app.Application
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import com.yandex.metrica.push.YandexMetricaPush

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val config: YandexMetricaConfig = YandexMetricaConfig
            .newConfigBuilder("fbf11b3d-2cba-471e-bfb4-c932a8d78287")
            .build()

        YandexMetrica.activate(applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(this)

        YandexMetricaPush.init(applicationContext)
    }
}