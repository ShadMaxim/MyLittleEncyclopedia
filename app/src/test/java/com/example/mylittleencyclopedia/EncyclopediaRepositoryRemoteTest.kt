package com.example.mylittleencyclopedia

import com.example.mylittleencyclopedia.data.provide.EncyclopediaApi
import com.example.mylittleencyclopedia.data.provide.EncyclopediaRepositoryRemote
import com.example.mylittleencyclopedia.data.provide.NetProvider
import org.junit.Test

private val BASE_URL = "https://api.backendless.com/2E2FD0CF-791C-51CC-FFED-679EF05FFD00/" +
        "8EBE3D6E-0DD9-DFA2-FFB2-6CD00161ED00/"

class EncyclopediaRepositoryRemoteTest {

    private var api: EncyclopediaApi? = null

    init {
        val retrofit = NetProvider.provideRetrofit(
            BASE_URL,
            NetProvider.provideOkHttp(),
            NetProvider.provideGson()
        )
        api = NetProvider.provideEncyclopediaApi(retrofit)
    }

    @Test
    fun test() {

        val repository = EncyclopediaRepositoryRemote(api!!)
        val list = repository.get(1, 0)

        val testSubscribe = list.test()

        testSubscribe.assertValue {
            it.isNotEmpty()
        }
    }
}