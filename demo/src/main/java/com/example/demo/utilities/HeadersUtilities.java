package com.example.demo.utilities;

import com.example.demo.Enum.FormatMediaTypeEnum;
import com.example.demo.exception.FormatNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HeadersUtilities {

    public static HttpHeaders getHttpHeaders(String name, String format) throws FormatNotFoundException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
                String.format("attachment; fileName=\"%s.%s\"", name, format));
        httpHeaders.setContentType(MediaType.parseMediaType(FormatMediaTypeEnum.getMediaTypeByFormat(format)));
        return httpHeaders;
    }

}
