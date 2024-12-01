package vn.hoidanit.laptopshop.controller.admin;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

import java.util.List;

@Controller
public class ProductController {

    private final UploadService uploadService;
    private final ProductService productService;

    public ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    // get all product
    @GetMapping("/admin/product")
    public String getProduct(Model model, @RequestParam(value = "page", defaultValue = "1") int page){
        Pageable pageable = PageRequest.of(page - 1,3);
        Page<Product> allProducts = this.productService.getAllProducts(pageable);
        List<Product> productList = allProducts.getContent();
        model.addAttribute("products", productList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", allProducts.getTotalPages());
        return "admin/product/show";
    }

    // go to create product page
    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model){
        model.addAttribute("newProduct", new Product());  // lay thong tin tu form
        return "admin/product/create";
    }

    // handle create product
    @PostMapping("/admin/product/create")
    public String handleCreateProduct(
            @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("imageFile") MultipartFile file
    ){

        // validate
        if(newProductBindingResult.hasErrors()){
            return "admin/product/create";
        }

        // upload image
        String image = this.uploadService.handleUploadFile(file, "product");
        product.setImage(image);

        System.out.println(product);

        this.productService.createProduct(product);

        return "redirect:/admin/product";
    }

    // go to detail product page
    @GetMapping("/admin/product/{id}")
    public String getProductDetail(Model model, @PathVariable("id") Long id){
        model.addAttribute("product", this.productService.getProductById(id));
        return "admin/product/detail";
    }

    // delete product
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        this.productService.deleteProductById(id);
        return "redirect:/admin/product";
    }

    // go to update product page
    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable Long id){
        model.addAttribute("updateProduct", this.productService.getProductById(id));
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String handleUpdateProduct(
            @ModelAttribute("updateProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("imageFile") MultipartFile file
    ){
        // validate
        if(newProductBindingResult.hasErrors()){
            return "admin/product/update";
        }

        Product currentProduct = this.productService.getProductById(product.getId());
        if (currentProduct != null) {
            // update image
            if (!file.isEmpty()) {
                String image = this.uploadService.handleUploadFile(file, "product");
                currentProduct.setImage(image);
            }

            currentProduct.setName(product.getName());
            currentProduct.setLongDescription(product.getLongDescription());
            currentProduct.setShortDescription(product.getShortDescription());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setQuantity(product.getQuantity());
            currentProduct.setCategory(product.getCategory());

            this.productService.createProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }
}
