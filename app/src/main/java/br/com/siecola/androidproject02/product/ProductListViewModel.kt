package br.com.siecola.androidproject02.product

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.siecola.androidproject02.network.SalesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val TAG = "ProductListViewModel"

class ProductListViewModel: ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    //utilizando co-rotina de escopo

    //aqui passamos a responsabilidade para outro thread
    //tudo que rodar dentrod o launch é assíncrono
    private fun getProducts() {
        Log.i(TAG, "Preparing to request products")

        coroutineScope.launch {
            val getProductsDeferred = SalesApi.retrofitService.getProducts()

            Log.i(TAG, "Fetching products")

            val productsList = getProductsDeferred.await()

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