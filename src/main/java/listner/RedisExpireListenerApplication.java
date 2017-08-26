package listner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author Deepak Muthekar
 *
 */
@Configuration
@SpringBootApplication
public class RedisExpireListenerApplication implements CommandLineRunner {
	@Autowired
	private StringRedisTemplate template;

	@Override
	public void run(String... args) throws Exception {
		ValueOperations<String, String> ops = this.template.opsForValue();
		String key = "SEASON";
		if (!this.template.hasKey(key)) {
			ops.set(key, "WINTER");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisExpireListenerApplication.class, args);
	}

	@Bean
	RedisMessageListenerContainer keyExpirationListenerContainer(RedisConnectionFactory connectionFactory) {
		System.out.println("Inside HAndler.........");
		RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
		listenerContainer.setConnectionFactory(connectionFactory);

		listenerContainer.addMessageListener((message, pattern) -> {
			System.out.println("INSIDE EVENT EXPIRY HANDLER -----------------------------");
			System.out.println(message.toString());
		}, new PatternTopic("__keyevent@*__:expired"));

		return listenerContainer;
	}

}
