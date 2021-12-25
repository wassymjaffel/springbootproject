package tn.esprit.spring.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Produit;

public interface ImageService {

    void store(MultipartFile file, Produit product);
    Resource loadProductFiles(String filename, Long projectId);
    void init();
}
