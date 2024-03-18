package com.team_ingterior.ingterior.common.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    
    public static String encodeContentDisposition(String fileName){
        return "attachment; filename*=UTF-8''"+URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");
    }
}
