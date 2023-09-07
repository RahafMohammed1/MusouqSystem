package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.SupplierDTO;
import com.example.musouqsystem.Model.Orders;
import com.example.musouqsystem.Model.Request;
import com.example.musouqsystem.Model.ShippingCompany;
import com.example.musouqsystem.Model.Supplier;
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
    private final ShippingCompanyRepository shippingCompanyRepository;


    public List<Supplier> marketerGetAllSuppliers() {
        return supplierRepository.findAll();
    }

    public void completeProfile(Supplier supplier) {
//        Supplier supplier = new Supplier(null, supplierDTO.getName(), supplierDTO.getPhone(),null,null,null,null);
        supplierRepository.save(supplier);
    }

    public void updateProfile(Integer supplier_id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);

        if (supplier == null) throw new ApiException("supplier not exist");

        supplier.setName(supplierDTO.getName());
        supplier.setPhone(supplierDTO.getPhone());

        supplierRepository.save(supplier);
    }

    public void supplierShippedOrder(Integer supplier_id, Integer order_id, String date) {
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);
        Orders order = ordersRepository.findOrdersById(order_id);
        if (supplier == null || order == null) throw new ApiException("supplier or order not exist");

        if (supplier.getOrders().contains(order))
            if (order.getOrder_status().equalsIgnoreCase("processing")) {
                order.setOrder_status("shipped");
                order.getShippingCompany().setDelivery_time(date);
                ordersRepository.save(order);
            }

    }

    public void deleteAccount(Integer supplier_id) {
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);

        if (supplier == null) throw new ApiException("supplier not exist");

        if (supplier.getMarketers().isEmpty() || supplier.getOrders().isEmpty())
            supplierRepository.delete(supplier);
        else throw new ApiException("you cannot delete your account while there are marketing request & orders");
    }

}
