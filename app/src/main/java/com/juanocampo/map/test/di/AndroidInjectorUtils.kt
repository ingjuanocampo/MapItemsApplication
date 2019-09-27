package com.juanocampo.mytaxy.test.di

import com.juanocampo.map.test.TaxiApp
import com.juanocampo.map.test.view.fragment.HamburgMapFragment
import com.juanocampo.map.test.view.di.MainScreenComponent
import com.juanocampo.map.test.view.di.PresentationMainModule
import com.juanocampo.map.test.view.fragment.TaxisListFragment

class AndroidInjectorUtils {

    companion object {

        var viewComponent: MainScreenComponent? = null

        @JvmStatic
        fun buildViewComponent() {
           viewComponent = TaxiApp.instance?.component?.buildMainScreenComponent(
               PresentationMainModule()
           )
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