import com.tensquare.rabbittest.RabbitTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitTestApplication.class)
public class ProductTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接模式
     */
    @Test
    public void sendMsg1() {
        rabbitTemplate.convertAndSend("queue_name", "消息内容");
    }

    /**
     * 分裂模式
     */
    public void sendMsg2() {
        rabbitTemplate.convertAndSend("exchange_name", "","消息内容");
    }

    /**
     * 主题模式
     */
    public void sendMsg3() {
        rabbitTemplate.convertAndSend("exchange_name", "role_content","消息内容");
    }
}
