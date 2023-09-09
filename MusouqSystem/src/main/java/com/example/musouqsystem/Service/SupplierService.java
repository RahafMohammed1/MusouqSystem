package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.SupplierDTO;
import com.example.musouqsystem.Model.*;
import com.example.musouqsystem.Repository.AuthRepository;
import com.example.musouqsystem.Repository.OrdersRepository;
import com.example.musouqsystem.Repository.ShippingCompanyRepository;
import com.example.musouqsystem.Repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final OrdersRepository ordersRepository;
    private final AuthRepository authRepository;


    public List<Supplier> marketerGetAllSuppliers() {
        return supplierRepository.findAll();
    }

    public void completeProfile(Integer supplier_id, SupplierDTO supplierDTO) {
        User user = authRepository.findUserById(supplier_id);
        Supplier supplier = new Supplier(null, supplierDTO.getName(), supplierDTO.getPhone(),user,null,null,null,null,null);
        supplierRepository.save(supplier);
    }

    public void updateProfile(Integer supplier_id, SupplierDTO supplierDTO) {
        User user = authRepository.findUserById(supplier_id);

        user.getSupplier().setName(supplierDTO.getName());
        user.getSupplier().setPhone(supplierDTO.getPhone());

        supplierRepository.save(user.getSupplier());
    }

    public void supplierShippedOrder(Integer supplier_id, Integer order_id) {
        User user = authRepository.findUserById(supplier_id);
        Orders order = ordersRepository.findOrdersById(order_id);

        if (order == null) throw new ApiException("order not exist");

        if (user.getSupplier().getOrders().contains(order))
            if (order.getOrder_status().equalsIgnoreCase("processing")) {
                order.setOrder_status("shipped");
                ordersRepository.save(order);
            }else throw new ApiException("order shipped before");
        else throw new ApiException("you cannot ship this order");
    }

    public void deleteAccount(Integer supplier_id) {
        User user = authRepository.findUserById(supplier_id);

        if (user.getSupplier().getMarketers().isEmpty()
                && user.getSupplier().getOrders().isEmpty()
                && user.getSupplier().getProducts().isEmpty()){
            supplierRepository.delete(user.getSupplier());
            authRepository.delete(user);
        }
        else throw new ApiException("you cannot delete your account while there are marketing request & orders");
    }

}
