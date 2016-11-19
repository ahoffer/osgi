import funservice.FunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public Client(FunService service) {
        logger.warn("HEY. I'm LOGGING");
        service.speak();
    }
}
