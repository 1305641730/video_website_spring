import com.kurumi.domain.User;
import com.kurumi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        List<User> userList = userService.selectAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
