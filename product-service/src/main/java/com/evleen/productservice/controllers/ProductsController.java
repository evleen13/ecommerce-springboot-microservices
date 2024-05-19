package com.evleen.productservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evleen.productservice.dto.ProductDecrementDto;
import com.evleen.productservice.entity.Product;
import com.evleen.productservice.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	@Autowired
	private ProductService productService;

	@GetMapping()
	public List<Product> getallProducts() {
		return productService.retrieveAllProducts();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable String id) {
		return productService.retrieveProductById(id);
	}

	@GetMapping("/by-key")
	public Product getProductByKey(@RequestParam String key) {
		return productService.retrieveProductByKey(key);
	}

	@PostMapping()
	public Product addProduct(@Valid @RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@PostMapping("/decrement-stock")
	public ResponseEntity<Boolean> decrementProductStock(@Valid @RequestBody ProductDecrementDto productDecrementDto) {
		return productService.decrementQuantityOnStock(productDecrementDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable String id) {
		return productService.removeProductById(id);
	}
}
