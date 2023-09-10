package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Model.Product;
import com.example.musouqsystem.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketerRepository extends JpaRepository<Marketer, Integer> {
    Marketer findMarketerById(Integer marketer_id);

    List<Marketer> findMarketersBySupplier (Supplier supplier);

}
