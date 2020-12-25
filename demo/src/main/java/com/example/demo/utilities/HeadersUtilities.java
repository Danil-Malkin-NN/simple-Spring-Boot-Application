package com.example.demo.utilities;

import com.example.demo.Enum.FormatMediaTypeEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HeadersUtilities {

    public static HttpHeaders getHttpHeaders(String name, String format) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; fileName=\"%s.%s\"", name, format));
        httpHeaders.setContentType(MediaType.parseMediaType(FormatMediaTypeEnum.getMediaTypeByFormat(format)));
        return httpHeaders;
    }

}
