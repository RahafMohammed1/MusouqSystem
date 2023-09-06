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

    public void changeImage(Integer img_id, ImageDTO imageDTO) {
        Image image = imageRepository.findImageById(img_id);
        if (image == null) throw new ApiException("image not exist");
        image.setUrl(imageDTO.getUrl());
        imageRepository.save(image);
    }

    public void deleteImage(Integer img_id) {
        Image image = imageRepository.findImageById(img_id);
        if (image == null) throw new ApiException("image not exist");
        imageRepository.delete(image);
    }


}
