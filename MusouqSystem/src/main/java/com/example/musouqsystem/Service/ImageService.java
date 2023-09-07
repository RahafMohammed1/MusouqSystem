package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.ImageDTO;
import com.example.musouqsystem.Model.Image;
import com.example.musouqsystem.Model.Product;
import com.example.musouqsystem.Repository.ImageRepository;
import com.example.musouqsystem.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    public List<Image> getAllImage() {
        return imageRepository.findAll();
    }

    public void addImage(Integer product_id, ImageDTO imageDTO) {
        Product product = productRepository.findProductById(product_id);
        if (product == null) throw new ApiException("product not exist");
        Image image = new Image(null, imageDTO.getUrl(), product);
        imageRepository.save(image);
    }

    public void changeImage(Integer supplier_id, Integer product_id, Image image) {
        Product product = productRepository.findProductByIdAndSupplierId(product_id, supplier_id);
        if (product == null) throw new ApiException("product not exist");

        Image oldImage = imageRepository.findImageByProductId(product.getId());
        if (oldImage == null) throw new ApiException("image not exist");

        oldImage.setUrl(image.getUrl());
        imageRepository.save(oldImage);
    }

    public void deleteImage(Integer supplier_id, Integer product_id) {
        Product product = productRepository.findProductByIdAndSupplierId(product_id, supplier_id);
        if (product == null) throw new ApiException("product not exist");

        Image oldImage = imageRepository.findImageByProductId(product.getId());
        if (oldImage == null) throw new ApiException("image not exist");


        productRepository.delete(product);
        imageRepository.delete(oldImage);
    }


}
