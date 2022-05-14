package br.com.siecola.androidproject02.product

import android.view.LayoutInflater
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
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onProductClickListener.onClick(product)
        }
    }

    class ProductViewHolder(private var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
                //product está no xml (item_product)
                binding.product = product
                binding.executePendingBindings()
            }
        }

    object ProductDiff: DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return ((oldItem.id == newItem.id) &&
                    (oldItem.name == newItem.name) &&
                    (oldItem.code == newItem.code) &&
                    (oldItem.price == newItem.price))
        }
    }

    class ProductClickListener(val clickListener: (product: Product) -> Unit) {
        fun onClick(product: Product) = clickListener(product)
    }
}

//explicando a classe
//recebe um listener de clique
//herda de List Adapter