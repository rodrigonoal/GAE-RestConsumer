package br.com.siecola.androidproject02.product

import android.widget.TextView
import androidx.databinding.BindingAdapter

//aqui teremos funções para formatar os conteúdos recebidos da API para demonstrá-los na UI

@BindingAdapter("productPrice")
fun bindProductPrice(txtProductPrice: TextView, productPrice: Double?) {
    txtProductPrice.text = productPrice?.let {"$ " + "%.2f".format(it)}
}