package org.example.title.service;


import org.example.salarySet.model.SalarySet;
import org.example.title.model.Title;

import java.util.List;

public interface TitleService {

    List<Title> getAllTitle();

    Title addTitle(Title title);

    Title updateTitle(Title title);

    void deleteTitle(Long id);

    Title getTitleById(Long id);
}
