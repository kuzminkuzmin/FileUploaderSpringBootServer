package com.dmitriikuzmin.service;

import com.dmitriikuzmin.model.User;
import com.dmitriikuzmin.model.UserFile;
import com.dmitriikuzmin.repository.UserFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import net.lingala.zip4j.ZipFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UserFileServiceImpl implements UserFileService {
    private UserFileRepository userFileRepository;
    private UserService userService;

    @Autowired
    public void setUserFileRepository(UserFileRepository userFileRepository) {
        this.userFileRepository = userFileRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean checkDir(String dir) {
        return new File(dir).isDirectory();
    }

    @Override
    public boolean createDir(String dir) {
        return new File(dir).mkdirs();
    }

    @Override
    public boolean deleteDir(String dir) {
        return new File(dir).delete();
    }

    @Override
    public UserFile add(long userId, MultipartFile file, String dir) {
        String fileName = file.getOriginalFilename();
        try {
            User user = this.userService.get(userId);
            UserFile userFile = this.userFileRepository.save(new UserFile(fileName, user));
            byte[] bytes = file.getBytes();
            try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream(new File(dir, fileName))
            )) {
                bufferedOutputStream.write(bytes);
            }
            return userFile;
        } catch (IOException | DataIntegrityViolationException e) {
            //TODO exception text
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /*@Override
    public void downloadFile(HttpServletResponse response, UserFile userFile) {
        File reqFile = new File(userFile.getFilename());
        try (ZipFile zipFile = new ZipFile(new File(file.getName() + ".zip"))) {
            zipFile.addFile();
        } catch (IOException e) {
        }

    }*/

    @Override
    public UserFile get(long id) {
        return this.userFileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User File Not Found"));
    }
}
