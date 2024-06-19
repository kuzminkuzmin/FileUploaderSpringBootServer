package com.dmitriikuzmin.controller;

import com.dmitriikuzmin.dto.ResponseResult;
import com.dmitriikuzmin.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_file")
public class UserFileController {
    private UserFileService userFileService;

    @Autowired
    public void setUserFileService(UserFileService userFileService) {
        this.userFileService = userFileService;
    }

    @GetMapping("/directory")
    public ResponseEntity<ResponseResult<Boolean>> checkDir(@RequestParam String dir) {
        return new ResponseEntity<>(new ResponseResult<>(
                null, this.userFileService.checkDir(dir)), HttpStatus.OK
        );
    }

    @PostMapping("/directory")
    public ResponseEntity<ResponseResult<Boolean>> createDir(@RequestParam String dir) {
        return new ResponseEntity<>(new ResponseResult<>(
                null, this.userFileService.createDir(dir)), HttpStatus.OK
        );
    }

    @DeleteMapping("/directory")
    public ResponseEntity<ResponseResult<Boolean>> deleteDir(@RequestParam String dir) {
        return new ResponseEntity<>(new ResponseResult<>(
                null, this.userFileService.deleteDir(dir)), HttpStatus.OK
        );
    }
}
