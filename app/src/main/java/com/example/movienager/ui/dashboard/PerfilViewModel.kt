package com.example.movienager.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PerfilViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Perfil Fragment com a lista de filmes assistidos"
    }
    val text: LiveData<String> = _text
}