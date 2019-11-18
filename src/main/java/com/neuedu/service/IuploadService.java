package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import org.springframework.stereotype.Service;

import java.io.File;
@Service
public interface IuploadService {
    public ServerResponse uploadFile(File uploadFile);
}
