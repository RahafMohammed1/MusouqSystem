package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findImageById(Integer img_id);

    Image findImageByProductId(Integer product_id);
}
