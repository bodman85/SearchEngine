package org.search.engine.services;


import java.util.Map;
import java.util.Set;

public interface DocumentService {
    public Map<Long, String> findAllDocuments();

    public String findById(Long id);

    public boolean isDocumentExist(Long id);

    public void saveDocument(Long id, String document);

    public Set<Long> findByTokens(String tokens);
}
