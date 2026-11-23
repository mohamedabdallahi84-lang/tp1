package com.example.tp1.service;
import com.example.tp1.entity.Product;
import com.example.tp1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
   @Autowired
   private ProductRepository productRepository;
    public List<Product> getAll() { return productRepository.findAll(); }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé : " + id));
    }
    public Product create(Product product) { return productRepository.save(product); }

    public Product update(Long id, Product details) {
        Product p = getById(id);
        p.setName(details.getName());
        p.setPrice(details.getPrice());
        p.setDescription(details.getDescription());
        return productRepository.save(p);
    }

    public void delete(Long id) { productRepository.deleteById(id); }
}