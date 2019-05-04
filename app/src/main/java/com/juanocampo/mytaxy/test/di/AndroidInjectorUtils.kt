package com.juanocampo.mytaxy.test.di

import com.juanocampo.mytaxy.test.TaxiApp
import com.juanocampo.mytaxy.test.view.di.DaggerViewComponent
import com.juanocampo.mytaxy.test.view.di.ViewComponent
import com.juanocampo.mytaxy.test.view.fragment.HamburgMapFragment
import com.juanocampo.mytaxy.test.view.fragment.TaxisListFragment

class AndroidInjectorUtils {

    companion object {

        var viewComponent: ViewComponent? = null

        @JvmStatic
        fun buildViewComponent() {
            viewComponent = DaggerViewComponent
                .builder()
                .appComponent(TaxiApp.instance?.component)
                .build()
        }

        @JvmStatic
        fun inject(fragment: TaxisListFragment) {
            viewComponent?.inject(fragment)
        }

        @JvmStatic
        fun inject(fragment: HamburgMapFragment) {
            viewComponent?.inject(fragment)
        }

    }

}