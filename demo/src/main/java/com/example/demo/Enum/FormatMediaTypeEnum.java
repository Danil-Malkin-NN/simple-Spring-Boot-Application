package com.example.demo.Enum;

import com.example.demo.exception.FormatNotFoundException;

import java.util.Arrays;

public enum FormatMediaTypeEnum {

    TEXT("txt", "text/plain"),
    XLS("xls", "application/vnd.ms-excel");

    private String format;
    private String mediaType;

    FormatMediaTypeEnum(String format, String mediaType) {
        this.format = format;
        this.mediaType = mediaType;
    }

    public static String getMediaTypeByFormat(String format) throws FormatNotFoundException {
        return Arrays.stream(FormatMediaTypeEnum.values())
                .filter(formatMediaTypeEnum -> formatMediaTypeEnum.format.equals(format))
                .findFirst().orElseThrow(() -> new FormatNotFoundException("Format is not found")).mediaType;
    }

    public String getFormat() {
        return format;
    }

    public String getMediaType() {
        return mediaType;
    }
}
