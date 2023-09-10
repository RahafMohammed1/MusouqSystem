package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Model.Request;
import com.example.musouqsystem.Model.Supplier;
import com.example.musouqsystem.Service.MarketerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {

    Request findRequestById(Integer id);

    List<Request> findRequestsByMarketer(Marketer marketer);
    List<Request> findRequestsBySupplier(Supplier supplier);


}
