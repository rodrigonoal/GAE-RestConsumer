package br.com.siecola.androidproject02.product

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import br.com.siecola.androidproject02.network.Product
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.siecola.androidproject02.databinding.ItemProductBinding

//para adaptar a lista para o layout, utilizamos este adaptador
class ProductAdapter(val onProductClickListener: ProductClickListener) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class ProductViewHolder(private var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) { //product está no xml (item_product)
                binding.product = product
                binding.executePendingBindings()
            }
        }

    class ProductDiff: DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            TODO("Not yet implemented")
        }
    }
}

//explicando a classe
//recebe um listener de clique
//herda de List Adapter