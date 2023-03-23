package notebook;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.model.repository.GBRepository;
import notebook.model.repository.impl.UserRepository;
import notebook.util.connector.impl.Connector;
import notebook.util.mapper.impl.UserMapper;
import notebook.view.UserView;

import static notebook.util.connector.impl.Connector.DB_PATH;


public class Main {
    public static void main(String[] args) {
        Connector.connectTxt();
        GBRepository<User, Long> repository = new UserRepository(new UserMapper(),DB_PATH);
        UserController controller = new UserController(repository);
        UserView view = new UserView(controller);
        view.run();
    }
}
