import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.add(product);
    }

    public List<Product> getProductList() {
        return productRepository.getProductList();
    }

    public Product getProductByID(int id) {
        return productRepository.getProductByID(id);
    }

    @Override
    public String toString() {
        return productRepository.toString();
    }
}
