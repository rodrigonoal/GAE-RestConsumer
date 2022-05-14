package br.com.siecola.androidproject02.productdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.siecola.androidproject02.databinding.FragmentProductDetailBinding

private const val TAG = "ProductDetailFragment"

class ProductDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "Creating ProductDetailFragment")
        val binding = FragmentProductDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }
}
