package com.qrcode.demo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestBody {

    private String pdfPath;
}
