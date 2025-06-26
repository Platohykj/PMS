package org.example.titleapi.request;

import lombok.Data;

@Data
public class UpdateEmployeeRequest {
    String jobId;
    Long titleId;

    //将titleId的初始值设为null
    public UpdateEmployeeRequest() {
        this.titleId = null;
    }
}
