package com.example.demo.Enum;

import java.util.Arrays;

public enum FormatMediaTypeEnum {

    TEXT("txt", "text/plain"),
    XLS("xls", "application/vnd.ms-excel"),
    DEFAULT("def", "application/octet-stream");

    private final String format;
    private final String mediaType;

    FormatMediaTypeEnum(String format, String mediaType) {
        this.format = format;
        this.mediaType = mediaType;
    }

    public static String getMediaTypeByFormat(String format) {
        return Arrays.stream(FormatMediaTypeEnum.values()).filter(formatMediaTypeEnum -> formatMediaTypeEnum.format.equals(format)).findFirst().orElse(DEFAULT).getMediaType();
    }

    public String getFormat() {
        return format;
    }

    public String getMediaType() {
        return mediaType;
    }
}
