package notebook.model.repository.impl;

import notebook.model.User;
import notebook.model.repository.*;
import notebook.util.logger.Log;
import notebook.util.mapper.impl.UserMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepository implements GBRepository<User, Long> {
    private final UserMapper mapper;
    private final String fileName;
    private static final Logger log = Log.log(UserRepository.class.getName());

    public UserRepository(UserMapper mapper, String fileName) {
        this.mapper = new UserMapper();
        this.fileName = fileName;
    }

    @Override
    public List<User> findAll() {
        List<String> lines = readAll();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.toOutput(line));
        }
        log.log(Level.INFO, "Запрос всего списка контактов");
        return users;
    }

    @Override
    public User create(User user) {
        List<User> users = findAll();
        long max = 0L;
        for (User u : users) {
            long id = u.getId();
            if (max < id) {
                max = id;
            }
        }
        long next = max + 1;
        user.setId(next);
        log.log(Level.INFO, "Назначен идентификатор для нового контакта: " + next);
        users.add(user);
        write(users);
        log.log(Level.INFO, "Новый контакт успешно создан");
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(Long userId, User update) {
        List<User> users = findAll();
        User editUser = null;
        for (User u : users) {
            if (u.getId().equals(userId)) {
                editUser = u;
            }
        }
        if (editUser == null) {
            log.log(Level.WARNING, "Контакт не найден. Отсутствует идентификатор: " + userId);
            throw new NullPointerException("User not found");
        }
        editUser.setFirstName(update.getFirstName());
        editUser.setLastName(update.getLastName());
        editUser.setPhone(update.getPhone());
        write(users);
        log.log(Level.INFO, "Изменены данные контакта id: " + editUser.getId());
        log.log(Level.INFO, "Имя контакта изменено на: " + editUser.getFirstName());
        log.log(Level.INFO, "Фамилия контакта изменена на: " + editUser.getLastName());
        log.log(Level.INFO, "Телефон контакта изменен на: " + editUser.getPhone());
        return Optional.of(update);
    }

    @Override
    public boolean delete(Long userId) {
        List<User> users = findAll();
        User deleteUser = null;
        for (User u : users) {
            if (u.getId().equals(userId)) {
                deleteUser = u;
            }
        }
        if (deleteUser == null) {
            log.log(Level.WARNING, "Контакт не найден. Отсутствует id: " + userId);
            throw new NullPointerException("User not found");
        }
        users.remove(deleteUser);
        log.log(Level.INFO, "Успешное удаление контакта id: " + deleteUser.getId());
        write(users);
        return true;
    }

    private void write(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User u : users) {
            lines.add(mapper.toInput(u));
        }
        saveAll(lines);
        log.log(Level.INFO, "Успешное сохранение");
    }

    @Override
    public List<String> readAll() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public void saveAll(List<String> data) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            for (String line : data) {
                writer.write(line);
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
