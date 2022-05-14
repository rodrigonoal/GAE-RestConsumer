package br.com.siecola.androidproject02.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.siecola.androidproject02.network.Product
import br.com.siecola.androidproject02.network.SalesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val TAG = "ProductListViewModel"

//aqui utilizamos o view model
//ele nos permite manter uma série de processos fora da thread de UI
class ProductListViewModel: ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    //utilizando co-rotina de escopo

    //aqui criamos atributos para guardar os dados variáveis dos produtos
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products


    init {
        getProducts()
    }

    fun refreshProducts() {
        getProducts()
    }

    //aqui passamos a responsabilidade para outro thread
    //tudo que rodar dentrod o launch é assíncrono
    private fun getProducts() {
        Log.i(TAG, "Preparing to request products")

        coroutineScope.launch {
            val getProductsDeferred = SalesApi.retrofitService.getProducts()

            Log.i(TAG, "Fetching products")

            val productsList = getProductsDeferred.await()

            _products.value = productsList

            Log.i(TAG, "Number of products = ${productsList.size}")
        }

        Log.i(TAG, "Products list request")
    }

    //possibilita que o job (requisição) seja cancelado a qualquer momento
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}