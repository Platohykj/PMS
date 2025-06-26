package org.example.title.service.impl;


import org.example.title.model.Title;
import org.example.title.repository.TitleRepository;
import org.example.title.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public List<Title> getAllTitle() {
        return titleRepository.findAll();
    }

    @Override
    public Title addTitle(Title title) {
        if (title.getId() != null) {
            throw new IllegalArgumentException("Title ID should not be set when adding a new title.");
        }
        return titleRepository.save(title);
    }

    @Override
    public Title updateTitle(Title title) {
        if (title.getId() == null) {
            throw new IllegalArgumentException("Title ID must be set when updating a title.");
        }
        return titleRepository.save(title);
    }

    @Override
    public void deleteTitle(Long id) {
        if (!titleRepository.existsById(id)) {
            throw new IllegalArgumentException("Title with ID " + id + " does not exist.");
        }
        titleRepository.deleteById(id);
    }
}
