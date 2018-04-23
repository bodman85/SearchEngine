package org.search.engine.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private Map<Long, String> documents = new HashMap();

    public static final String DELIMITER = (",|\\s|\\t");

//    {
//        populateDummyDocuments();
//    }

//    private void populateDummyDocuments() {
//        documents.put(1L, "abc, def, ghi");
//        documents.put(2L, "def zzz klm");
//        documents.put(3L, "uia klm s7");
//        documents.put(4L, "glk gle gls");
//    }

    @Override
    public Map<Long, String> findAllDocuments() {
        return documents;
    }

    @Override
    public String findById(Long id) {
        return documents.get(id);
    }

    @Override
    public boolean isDocumentExist(Long id) {
        return documents.containsKey(id);
    }

    @Override
    public void saveDocument(Long id, String document) {
        documents.put(id, document);
    }

    @Override
    public Set<Long> findByTokens(String tokens) {
        return documents.entrySet().stream()
                .filter(map -> stream(map.getValue().split(DELIMITER))
                        .anyMatch(element -> asList(tokens.split(DELIMITER)).contains(element)))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
//                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()))
//                .keySet();
    }
}
