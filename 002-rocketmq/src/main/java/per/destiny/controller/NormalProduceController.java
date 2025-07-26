package per.destiny.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class NormalProduceController {
  @Resource
  private RocketMQTemplate rocketmqTemplate;
  
  @GetMapping("/send")
  public void send(@RequestParam("content") String content) {
    Message<String> msg = MessageBuilder.withPayload(content).build();
    rocketmqTemplate.send("my_topic", msg);
  }
}