package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.RequestDTO;
import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Model.Request;
import com.example.musouqsystem.Model.Supplier;
import com.example.musouqsystem.Repository.MarketerRepository;
import com.example.musouqsystem.Repository.RequestRepository;
import com.example.musouqsystem.Repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final SupplierRepository supplierRepository;
    private final MarketerRepository marketerRepository;


    public Set<Request> marketerViewAllHisRequest(Integer marketer_id)
    {
        Marketer marketer=marketerRepository.findMarketerById(marketer_id);
        if (marketer==null) throw new ApiException("marketer not found");
        //check if marketer have requests or not
        if(marketer.getRequests()==null)throw new ApiException("you are not send any request yet");
        return marketer.getRequests();
    }

    public Set<Request> supplierViewAllHisRequest(Integer supplier_id)
    {
        Supplier supplier=supplierRepository.findSupplierById(supplier_id);
        if (supplier==null) throw new ApiException("supplier not found");
        if(supplier.getRequests()==null)throw new ApiException("you have not any request yet");
        return supplier.getRequests();
    }

    public void marketerSendRequest(Integer marketer_id, RequestDTO requestDTO)
    {
        Marketer marketer=marketerRepository.findMarketerById(marketer_id);
        if (marketer==null) throw new ApiException("marketer not found");
        //check if the marketer select supplier or not
        if(marketer.getSupplier()==null)
        {
            throw new ApiException("you must select a supplier first to send request to him");
        }
        Request request=new Request();
        request.setReq_description(requestDTO.getReq_description());
        request.setStatus("pending");
        request.setSupplier(marketer.getSupplier());
        request.setMarketer(marketer);
        requestRepository.save(request);
    }

    public void marketerUpdateRequest(Integer marketer_id,Integer request_id,RequestDTO requestDTO)
    {
        Marketer marketer=marketerRepository.findMarketerById(marketer_id);
        if (marketer==null) throw new ApiException("marketer not found");
        if(marketer.getRequests()==null)throw new ApiException("you are not send any request yet");
        Request request=requestRepository.findRequestById(request_id);
        if (request==null) throw new ApiException("request not found");
        if(request_id!=request.getMarketer().getId()) throw new ApiException("the request not belong to you");
        request.setReq_description(requestDTO.getReq_description());
    }

    public void marketerDeleteRequest(Integer marketer_id,Integer request_id) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null) throw new ApiException("marketer not found");
        if (marketer.getRequests() == null) throw new ApiException("you are not send any request yet");
        Request request = requestRepository.findRequestById(request_id);
        if (request == null) throw new ApiException("request not found");
        if(request_id!=request.getMarketer().getId()) throw new ApiException("the request not belong to you");
        requestRepository.delete(request);
    }


    //supplierAcceptRequest
    public void supplierAcceptRequest(Integer supplier_id,Integer request_id){

        Supplier supplier=supplierRepository.findSupplierById(supplier_id);
        if (supplier==null) throw new ApiException("supplier not found");

        Request request=requestRepository.findRequestById(request_id);
        if (request==null) throw new ApiException("request not found");

        if(request.getSupplier().getId()!=supplier_id) throw new ApiException("this request not belong to you");

        request.setStatus("Accepted");
        requestRepository.save(request);
    }
    //supplierRejectRequest
    public void supplierRejectRequest(Integer supplier_id,Integer request_id){

        Supplier supplier=supplierRepository.findSupplierById(supplier_id);
        if (supplier==null) throw new ApiException("supplier not found");

        Request request=requestRepository.findRequestById(request_id);
        if (request==null) throw new ApiException("request not found");

        if(request.getSupplier().getId()!=supplier_id) throw new ApiException("this request not belong to you");

        request.setStatus("Rejected");
        requestRepository.save(request);
    }

}
