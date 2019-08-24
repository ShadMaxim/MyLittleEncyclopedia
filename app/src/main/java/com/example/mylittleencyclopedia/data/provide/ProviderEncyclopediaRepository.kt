package com.example.mylittleencyclopedia.data.provide

private val BASE_URL =
    "https://api.backendless.com/2E2FD0CF-791C-51CC-FFED-679EF05FFD00/8EBE3D6E-0DD9-DFA2-FFB2-6CD00161ED00/"

fun provideEncyclopediaRepository(): EncyclopediaRepository {

    return EncyclopediaRepositoryRemote(
        NetProvider.provideEncyclopediaApi(
            NetProvider.provideRetrofit(
                BASE_URL,
                NetProvider.provideOkHttp(),
                NetProvider.provideGson()
            )
        )
    )
}