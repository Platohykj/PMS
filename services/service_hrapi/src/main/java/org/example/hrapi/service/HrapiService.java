package org.example.hrapi.service;


import org.example.hrapi.request.BatchSalary;
import org.example.hrapi.request.ListRequest;
import org.example.hrapi.request.UpadteRequest;
import org.example.hrapi.response.ListResponse;

import java.util.List;

public interface HrapiService {

    public List<ListResponse> list(ListRequest employleesRequest);

    void update(UpadteRequest request);

    void batchSalary(BatchSalary request);
}
