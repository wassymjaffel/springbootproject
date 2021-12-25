package tp.springboot;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

import io.swagger.annotations.Api;


@SpringBootApplication
@Api
public class Application {

	@Autowired
	private EmailSenderService service;
           
	private static String QRCODE_PATH = "C:\\Utilisateurs\\wassy\\Desktop\\";
	public String writeQRCode( List<PaytmRequestBody> paytmRequestBody) throws Exception {
		String qrcode = QRCODE_PATH + "-QRCODE.png";
		String info="";
		for (PaytmRequestBody temp :paytmRequestBody)
		{info+=" \n"+temp;
		}
		System.out.println(info);
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix bitMatrix = writer.encode(info, BarcodeFormat.QR_CODE, 502, 502);
		Path path = FileSystems.getDefault().getPath(qrcode);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		return "QRCODE is generated successfully....";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		
		Application codeGenerator = new Application();
		 List<PaytmRequestBody> liste = new ArrayList<PaytmRequestBody>();
		 
		 liste.add(new PaytmRequestBody("wassym", "26406443", "UIB", "404NOTFOUND"));
		System.out.println(codeGenerator.writeQRCode(liste));
	
	}
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {

		service.sendSimpleEmail("wassym.jaffel@esprit.tn",
				"This is Email Body with Attachment... ...",
				"This email has attachment");

	}
	
	}
