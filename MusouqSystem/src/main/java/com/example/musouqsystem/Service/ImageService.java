package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.ImageDTO;
import com.example.musouqsystem.Model.Image;
import com.example.musouqsystem.Model.Product;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Repository.AuthRepository;
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
    private final AuthRepository authRepository;

    public List<Image> getAllImage() {
        return imageRepository.findAll();
    }

    public void addImage(Integer supplier_id, Integer product_id, ImageDTO imageDTO) {
        User user = authRepository.findUserById(supplier_id);
        Product product = productRepository.findProductByIdAndSupplierId(product_id, user.getId());

        if (product == null) throw new ApiException("product not exist");

        Image image = new Image(null, imageDTO.getUrl(), product);
        imageRepository.save(image);
    }

    public void changeImage(Integer supplier_id, Integer product_id, Image image) {
        User user = authRepository.findUserById(supplier_id);
        Product product = productRepository.findProductByIdAndSupplierId(product_id, user.getId());
        if (product == null) throw new ApiException("product not exist");

        Image oldImage = imageRepository.findImageByProductId(product.getId());
        if (oldImage == null) throw new ApiException("image not exist");

        oldImage.setUrl(image.getUrl());
        imageRepository.save(oldImage);
    }

    public void deleteImage(Integer supplier_id, Integer product_id) {
        User user = authRepository.findUserById(supplier_id);
        Product product = productRepository.findProductByIdAndSupplierId(product_id, user.getId());
        if (product == null) throw new ApiException("product not exist");

        Image oldImage = imageRepository.findImageByProductId(product.getId());
        if (oldImage == null) throw new ApiException("image not exist");


        productRepository.delete(product);
        imageRepository.delete(oldImage);
    }


}
