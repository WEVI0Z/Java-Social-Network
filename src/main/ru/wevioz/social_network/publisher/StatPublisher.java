package wevioz.social_network.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import wevioz.social_network.dto.StatDto;
import wevioz.social_network.dto.UserDto;

@Component
@RequiredArgsConstructor
public class StatPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void send(StatDto order) {
        rabbitTemplate.convertAndSend(queue.getName(), order);
    }
}
