package com.br.appmarvelstudios.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.appmarvelstudios.Constants.Companion.FAILED_TO_RECEIVE_CHARACTERS
import com.br.appmarvelstudios.Constants.Companion.UNABLE_TO_RECEIVE_INTERNAL_CHARACTERS
import com.br.appmarvelstudios.Constants.Companion.UNABLE_TO_SAVE_CHARACTER
import com.br.appmarvelstudios.model.Character
import com.br.appmarvelstudios.model.HeadQuarters
import com.br.appmarvelstudios.repository.AvengersRepository
import com.br.appmarvelstudios.repository.Failure
import com.br.appmarvelstudios.repository.Resource
import com.br.appmarvelstudios.repository.Success
import kotlinx.coroutines.launch

class AvengersViewModel(
    private val repository: AvengersRepository
) : ViewModel() {

    fun getAllCharacters() = MutableLiveData<Resource<HeadQuarters?>>().also { allCharacters ->
        viewModelScope.launch {
            val resource : Resource<HeadQuarters?> = try {
                Success(dados = repository.getAllCharacters())
            } catch (e: Exception) {
                Failure(erro = FAILED_TO_RECEIVE_CHARACTERS)
            }
            allCharacters.postValue(resource)
        }
        return allCharacters
    }

    fun internalSave(character: Character) = MutableLiveData<Resource<Character>>().also {insertCharacter ->
        viewModelScope.launch {
            val resource: Resource<Character> = try {
                repository.saveCharacter(character)
                Success()
            } catch (e: Exception){
                Failure(erro = UNABLE_TO_SAVE_CHARACTER)
            }
            insertCharacter.postValue(resource)
        }
        return insertCharacter
    }

    fun getAllCharacterDao() = MutableLiveData<Resource<List<Character>>>().also { allCharacterDao ->
        viewModelScope.launch {
            val resource: Resource<List<Character>> = try {
                Success(dados = repository.getAllCharacterDao())
            } catch (e: Exception){
                Failure(erro = UNABLE_TO_RECEIVE_INTERNAL_CHARACTERS)
            }
            allCharacterDao.postValue(resource)
        }
        return allCharacterDao
    }

}