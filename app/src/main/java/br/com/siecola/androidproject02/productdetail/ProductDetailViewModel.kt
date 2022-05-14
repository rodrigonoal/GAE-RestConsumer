package br.com.siecola.androidproject02.productdetail

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

private const val TAG = "ProductDetailViewModel"

class ProductDetailViewModel(private val code: String): ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
    get() = _product

    init {
        getProduct()
    }

    private fun getProduct() {
        Log.i(TAG, "Preparing request q product by its code")
        coroutineScope.launch {
            _product.value = SalesApi.retrofitService.getProductByCode(code).await()
        }
        Log.i(TAG, "Product requested by code")
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared")
        super.onCleared()
        viewModelJob.cancel()
    }

}