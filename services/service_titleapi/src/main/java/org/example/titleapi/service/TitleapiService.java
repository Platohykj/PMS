package org.example.titleapi.service;


import org.example.response.Response;
import org.example.title.model.Title;
import org.example.titleapi.controller.TitleapiController;
import org.example.titleapi.request.EmployleesRequest;
import org.example.titleapi.request.RemoveRequest;
import org.example.titleapi.request.UpdateEmployeeRequest;
import org.example.titleapi.response.EmployeeResponse;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;

public interface TitleapiService {

    Response<?> getTitleList();

    List<String> getTitleNames();

    Response<?> updateTitle(Title title);

    Response<?> saveTitle(Title title);

    void deleteTitle(Long id);

    List<EmployeeResponse> getEmployeesByDepartment(EmployleesRequest employleesRequest);

    void updateEmployeePosition(UpdateEmployeeRequest updateEmployeeRequest);

    void removeEmployeeTitle(RemoveRequest removeRequest);
}
