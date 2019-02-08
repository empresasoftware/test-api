/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.sc.restapi.service;

/**
 *
 * @author pedro
 */
import empresa.software.sc.restapi.exception.FileStorageException;
import empresa.software.sc.restapi.exception.MyFileNotFoundException;
import empresa.software.sc.restapi.model.Escort;
import empresa.software.sc.restapi.model.Foto;
import empresa.software.sc.restapi.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class DBFileStorageService {

    @Autowired
    private FotoRepository fotoRepository;
    

    public Foto storeFile(MultipartFile file, Escort escort) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Foto foto = new Foto(fileName, file.getContentType(), file.getBytes(), escort);

            return fotoRepository.save(foto);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Foto getFile(Long fileId) {
        return fotoRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}