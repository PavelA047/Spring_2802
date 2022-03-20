package product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        this.productRepository.insert(new Product("Socks", 500L));
        this.productRepository.insert(new Product("Jeans", 7000L));
        this.productRepository.insert(new Product("Jacket", 5000L));
        this.productRepository.insert(new Product("T-short", 1000L));
        this.productRepository.insert(new Product("Trousers", 500L));
        this.productRepository.insert(new Product("Shorts", 2000L));
        this.productRepository.insert(new Product("Skirt", 5000L));
        this.productRepository.insert(new Product("Hat", 2000L));
        this.productRepository.insert(new Product("Sunglasses", 4000L));
        this.productRepository.insert(new Product("Bag", 6000L));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Product prod;
        PrintWriter wr = resp.getWriter();
        if (req.getPathInfo() == null) {
            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>Id</th>");
            wr.println("<th>ProductTitle</th>");
            wr.println("<th>Cost</th>");
            wr.println("</tr>");

            for (Product product : productRepository.findAll()) {
                wr.println("<tr>");
                wr.println("<td><a href='" + req.getContextPath() + req.getServletPath() + "/" + product.getId() + "'>" + product.getId() + "</a></td>");
                wr.println("<td>" + product.getTitle() + "</td>");
                wr.println("<td>" + product.getCost() + "</td>");
                wr.println("</tr>");
            }

            wr.println("</table>");
        } else {
            long productId = Long.parseLong(req.getPathInfo().substring(1));
            prod = productRepository.findById(productId);

            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>Id: " + prod.getId() + "</th>");
            wr.println("<th>ProductTitle: " + prod.getTitle() + "</th>");
            wr.println("<th>Cost: " + prod.getCost() + "</th>");
            wr.println("</tr>");
        }
    }
}
