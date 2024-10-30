package com.example.laboratorio7_retrofit.ui.market.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.laboratorio7_retrofit.database.data.SupermarketItemEntity
import com.example.laboratorio7_retrofit.ui.market.repository.SuperMarketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID
class SupermarketViewModel(private val repository: SuperMarketRepository) : ViewModel() {

    // Estado de los artículos de la lista de supermercado
    private val _items = MutableLiveData<List<SupermarketItemEntity>>()
    val items: LiveData<List<SupermarketItemEntity>> get() = _items

    // Estados de carga y error
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadItems() // Carga inicial de los artículos cuando se inicia el ViewModel
    }

    // Método para cargar todos los artículos de la lista
    private fun loadItems() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _items.value = repository.getAllItems()
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar los artículos"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Método para agregar un nuevo artículo
    fun addItem(itemName: String, quantity: Int, imagePath: String? = null) {
        val newItem = SupermarketItemEntity(
            itemName = itemName,
            quantity = quantity,
            imagePath = imagePath
        )

        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.insertItem(newItem)
                _items.value =
                    _items.value?.plus(newItem) // Optimiza al añadir el nuevo ítem localmente
            } catch (e: Exception) {
                _errorMessage.value = "Error adding item"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Método para actualizar un artículo existente
    fun updateItem(
        itemId: Int,
        newName: String,
        newQuantity: Int,
        newImagePath: String? = null
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val item = repository.getItemById(itemId)
                if (item != null) {
                    val updatedItem = item.copy(
                        itemName = newName,
                        quantity = newQuantity,
                        imagePath = newImagePath ?: item.imagePath
                    )
                    repository.updateItem(updatedItem)
                    _items.value = _items.value?.map { if (it.id == itemId) updatedItem else it }
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error updating item"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Método para eliminar un artículo
    fun deleteItem(item: SupermarketItemEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                item.imagePath?.let { path ->
                    val file = File(path)
                    if (file.exists()) file.delete()
                }
                repository.deleteItem(item)
                loadItems() // Recarga los elementos después de eliminar
            } catch (e: Exception) {
                _errorMessage.value = "Error al eliminar el artículo"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Método para obtener un artículo específico por su ID (usando StateFlow en vez de devolución directa)
    suspend fun getItemById(itemId: Int): SupermarketItemEntity? {
        return try {
            repository.getItemById(itemId)
        } catch (e: Exception) {
            _errorMessage.value = "Error al obtener el artículo"
            null
        }
    }
}

class SupermarketViewModelFactory(
    private val repository: SuperMarketRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SupermarketViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SupermarketViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
