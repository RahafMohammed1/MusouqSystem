package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    Supplier findSupplierById(Integer id);
}
