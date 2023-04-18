package com.example.observeflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

/*Dans ce code, la variable privée _myEventFlow est déclarée comme MutableSharedFlow et est utilisée pour émettre des événements à partir de la fonction loadData().
La variable publique myEventFlow est déclarée comme un SharedFlow et est exposée en lecture seule à l'extérieur de la classe pour que les clients puissent s'abonner aux événements émis par _myEventFlow.
En utilisant une variable privée pour stocker le MutableSharedFlow et une variable publique pour stocker le SharedFlow en lecture seule, la classe MyViewModel s'assure que les clients ne peuvent pas émettre d'événements directement sur _myEventFlow.
Cela permet à la classe de contrôler l'émission d'événements et de garantir que les clients ne peuvent que s'abonner aux événements et non les modifier.
En résumé, en utilisant une variable privée et une variable publique dans ce cas, la classe MyViewModel peut fournir une API propre et bien définie pour que les clients
s'abonnent aux événements émis par la classe, tout en limitant la façon dont les clients peuvent modifier les événements.*/

class MyViewModel : ViewModel() {
    private val _myEventFlow = MutableSharedFlow<MyEvent>()
    val myEventFlow: SharedFlow<MyEvent> = _myEventFlow

    fun loadData() {
        viewModelScope.launch {
            _myEventFlow.emit(MyEvent.Loading)
            delay(2000)
            _myEventFlow.emit(MyEvent.Success("Data loaded successfully"))
        }
    }
}
