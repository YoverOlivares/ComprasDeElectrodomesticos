package unc.edu.pe.comprasdeelectrodomesticos.ui.productlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import unc.edu.pe.comprasdeelectrodomesticos.databinding.ItemProductBinding;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList = new ArrayList<>();
    private final OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    public ProductAdapter(OnProductClickListener listener) {
        this.listener = listener;
    }

    public void setProducts(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductBinding binding;

        public ProductViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // Método para enlazar datos del producto_________
        public void bind(Product product) {
            binding.tvProductName.setText(product.getName());
            binding.tvProductPrice.setText("S/. " + product.getPrice());

            // Cargar imagen desde drawable
            int imageResId = getDrawableResourceId(product.getImageUrl());
            if (imageResId != 0) {
                binding.ivProductImage.setImageResource(imageResId);
            } else {
                // Imagen por defecto si no se encuentra
                binding.ivProductImage.setImageResource(android.R.drawable.ic_menu_gallery);
            }

            // Click listener en toda la tarjeta
            binding.getRoot().setOnClickListener(v -> listener.onProductClick(product));
        }
        private int getDrawableResourceId(String drawableName) {
            try {
                return binding.getRoot().getContext().getResources()
                        .getIdentifier(drawableName, "drawable",
                                binding.getRoot().getContext().getPackageName());
            } catch (Exception e) {
                return 0;
            }
        }

    }
}