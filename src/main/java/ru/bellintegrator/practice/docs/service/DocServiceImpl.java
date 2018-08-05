package ru.bellintegrator.practice.docs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.docs.dao.DocDao;
import ru.bellintegrator.practice.docs.model.Doc;
import ru.bellintegrator.practice.docs.view.DocView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@Transactional
public class DocServiceImpl implements DocService {

    private final DocDao docDao;

    @Autowired
    public DocServiceImpl(DocDao docDao) {
        this.docDao = docDao;
    }


    @Override
    @Transactional(readOnly = true)
    public List<DocView> getAllDocs() {
        List<Doc> all = docDao.getAllDocs();
        return all.stream()
                .map(mapDoc())
                .collect(Collectors.toList());
    }
    private Function<Doc, DocView> mapDoc() {
        return p -> {

            DocView view = new DocView();
            view.id = Long.valueOf(p.getId());
            view.docName = p.getDocName();
            view.docCode = p.getDocCode();
            return view;
        };
    }
}

