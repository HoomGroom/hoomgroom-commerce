package id.ac.ui.cs.advprog.hoomgroomcommerce.repository;


import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


import org.springframework.stereotype.Repository;


@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();


    public Product create(Product product) {
        if (product.getProductId() == null) {
            UUID uuid = UUID.randomUUID();
            product.setProductId(uuid);
        }


        productData.add(product);
        return product;
    }


    public Iterator<Product> findAll() {
        return productData.iterator();
    }


    public Product findById(UUID productId) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid product Id:" + productId)
                );
    }


    public Product edit(Product editedProduct) {
        UUID productId = editedProduct.getProductId();
        Product existingProduct = findById(productId);
        int indexOfProduct = productData.indexOf(existingProduct);
        productData.set(indexOfProduct, editedProduct);
        return editedProduct;
    }


    public Product delete(UUID productId) {
        Product product = findById(productId);
        productData.remove(product);
        return product;
    }
}