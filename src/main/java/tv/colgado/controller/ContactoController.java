package tv.colgado.controller;

import com.sendgrid.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import tv.colgado.model.CaptchaResult;
import tv.colgado.model.Contacto;

import java.io.IOException;

import static tv.colgado.utils.Constants.*;

@Controller
public class ContactoController implements ColgadoController{
	
	final static Logger LOGGER = Logger.getLogger(ContactoController.class);

	@Value("${version}")
	private String version;
	
	@Value("${email.receiver}")
	private String emailReceiver;
	
	@Value("${email.api}")
	private String emailApiKey;
	
	@Value("${captcha.api}")
	private String captchaApiKey;

	@RequestMapping("/contacto")
	public String root(Model model) {
		addCommonAttributes(model);
		model.addAttribute("title", "Contacto");
		return getDefaultView();
	}

	@PostMapping("/contacto")
    public String greetingSubmit(@ModelAttribute Contacto contacto) {
		LOGGER.info("Nuevo mensaje");
		if (isHuman(contacto)) {
			Mail mail = generateMail(contacto);
			SendGrid sg = new SendGrid(emailApiKey);
			Request request = new Request();
			request.method = Method.POST;
			request.endpoint = "mail/send";
		      try {
		    	LOGGER.info("Enviando Email");
				request.body = mail.build();
				sg.api(request);
			} catch (IOException e) {
				return ROBOT_VIEW;
			}
			return MESSAGE_VIEW;
		}
		return ROBOT_VIEW;
    }

	private Mail generateMail(Contacto contacto) {
		LOGGER.info("Generando Email");
		Email from = new Email(contacto.getEmail());
		String subject = EMAIL_SUBJECT+contacto.getNombre();
		Email to = new Email(emailReceiver);
		Content content = new Content("text/plain", contacto.getMensaje());
		return new Mail(from, subject, to, content);
	}

	private Boolean isHuman(Contacto contacto) {
		LOGGER.info("Validando que el emisor sea humano");
		String uri = GOOGLE_CAPTCHA_VERIFY_URI+"?secret="+captchaApiKey+"&response="+contacto.getToken();
		RestTemplate restTemplate = new RestTemplate();
		CaptchaResult result = restTemplate.postForObject( uri, null, CaptchaResult.class);
		return result.getSuccess();
	}

	@Override
	public void addCommonAttributes(Model model) {
		model.addAttribute("active", ACTIVE_CONTACTO);
		model.addAttribute("version", version);
		model.addAttribute("ad", false);
	}

	@Override
	public void addMediaAttributes(Model model, String title,String template, String schedule) {
		
	}

}
