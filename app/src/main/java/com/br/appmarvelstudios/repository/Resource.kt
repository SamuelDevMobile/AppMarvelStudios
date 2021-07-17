package com.br.appmarvelstudios.repository

abstract class Resource<T>(
    val dados: T? = null,
    val error: String? = null
)

class Success<T>(
    dados: T? = null
) : Resource<T>(dados = dados)

class Failure<T>(
    erro: String
) : Resource<T>(error = erro)