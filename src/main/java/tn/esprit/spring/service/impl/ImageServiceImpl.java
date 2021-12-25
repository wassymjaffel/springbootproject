package tn.esprit.spring.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Produit;
import tn.esprit.spring.service.ImageService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    private final Path rootLocationProduct = Paths.get("product-dir");


    public void store(MultipartFile file, Produit produit) {
        try {
            final Path rootLocationOneProject = Paths.get("product-dir/");
            Files.copy(file.getInputStream(), rootLocationOneProject.resolve(produit.getIdProduit()+file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("FAIL!" + e);
        }
    }

    public Resource loadProductFiles(String filename, Long productId) {
        final Path rootLocationOneProject = Paths.get("product-dir/"+productId);
        try {
            Path file = rootLocationOneProject.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void init() {
        try {
            Files.createDirectory(rootLocationProduct);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

}
