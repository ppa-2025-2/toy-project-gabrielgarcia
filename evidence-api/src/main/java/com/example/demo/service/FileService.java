package com.example.demo.service;

import java.io.IOException;

public interface FileService {
    String upload(String name, String type, byte[] conteudo) throws IOException;
}
