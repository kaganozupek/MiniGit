package com.ozupek.myapplication

import com.ozupek.myapplication.network.NetworkManager
import org.junit.Test

class NetworkManagerTest {

    @Test
    fun isSearchWorking() {

        NetworkManager.getApi().searchRepositories("Retrofit")
            .subscribe {
                assert(!it.items.isEmpty())
            }
    }
}