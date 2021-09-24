package com.qrcode.demo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;

@SpringBootApplication
public class DemoApplication
{
	 static final String QR_CODE_LOCATION = "C:\\Users\\bpola\\Downloads\\demo\\";
	public static void main(String[] args) throws Exception {
		String fileName =QR_CODE_LOCATION + "qrCode" +".png";
		RequestBody requestBody = RequestBody.builder().pdfPath("https://spin.polyglots.io/reports/lab2/reports/S210900002.001/R210900002.001_1631101457564.pdf").build();
		String pathOfQrImage = generateQRCode(requestBody.getPdfPath(), 350, 350, fileName);
		System.out.print("pathOfQrImage : "+ pathOfQrImage);
		SpringApplication.run(DemoApplication.class, args);
	}

	private static String generateQRCode(String text, int width, int height, String filePath) throws WriterException, IOException
	{
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
		Path path = FileSystems.getDefault().getPath(filePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		return path.toAbsolutePath().toString();
	}

	public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception
	{
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}


	public static byte[] getQRCodeImage(String text, int width, int height, BitMatrix bitMatrix) throws WriterException, IOException
	{
//		QRCodeWriter qrCodeWriter = new QRCodeWriter();
//		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		byte[] pngData = pngOutputStream.toByteArray();
		return pngData;
	}

}
