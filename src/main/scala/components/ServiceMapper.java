package components;

import model.User;
import services.UserService;

/**
 * Created by pawel_zaqkxkn on 24.09.2017.
 */
public class ServiceMapper {

    public static void delete(final String token, final User t) {
        UserService.delete(token, t);
    }
}
