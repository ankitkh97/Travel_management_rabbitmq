package thmsRabbitMQ.publisher;




import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import thmsRabbitMQ.model.Traveller;
import thmsRabbitMQ.model.UserLogin;
import thmsRabbitMQ.config.MessagingConfig;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AddJourneyPublisher  {

	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/username/{username}/addUserJourney")
	private void addUserJourney(@RequestBody Traveller newTravel, @PathVariable("username") String username) {
		System.out.println(newTravel);
		
		newTravel.setUserlogin(new UserLogin(username, ""));
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, newTravel);
		System.out.println("Inside Publisher");
		
		
		
	}
	
	
}
