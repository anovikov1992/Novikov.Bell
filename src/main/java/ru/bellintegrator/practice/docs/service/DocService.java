package ru.bellintegrator.practice.docs.service;

import ru.bellintegrator.practice.docs.model.Doc;
import ru.bellintegrator.practice.docs.view.DocView;

import java.util.List;

public interface DocService {

    List<DocView> getAllDocs();
}
