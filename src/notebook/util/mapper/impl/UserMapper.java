package notebook.util.mapper.impl;

import notebook.model.User;
import notebook.util.mapper.Mapper;

public class UserMapper implements Mapper<User, String> {
    @Override
    public String toInput(User user) {
        return String.format("%s,%s,%s,%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    @Override
    public User toOutput(String s) {
        String[] lines = s.split(",");
        long id;
        if (isDigit(lines[0])) {
            id = Long.parseLong(lines[0]);
            User user = new User.UserBuilder()
                    .id(id)
                    .firstName(lines[1])
                    .lastName(lines[2])
                    .phone(lines[3])
                    .build();
            return user;
        }
        throw new NumberFormatException("Id must be a large number");
    }

    public boolean isDigit(String s) throws NumberFormatException {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
