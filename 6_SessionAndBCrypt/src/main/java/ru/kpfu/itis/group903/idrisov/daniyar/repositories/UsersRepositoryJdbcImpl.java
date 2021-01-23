package ru.kpfu.itis.group903.idrisov.daniyar.repositories;

import ru.kpfu.itis.group903.idrisov.daniyar.model.User;
import ru.kpfu.itis.group903.idrisov.daniyar.repositories.interfaces.RowMapper;
import ru.kpfu.itis.group903.idrisov.daniyar.repositories.interfaces.UsersRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from users";

    //language=SQL
    private static final String SQL_FIND_BY_UUID = "select * from users where UUID = ?";

    //language=SQL
    private static final String SQL_SAVE_USER = "insert into users(login, password, UUID) values (?,?,?)";

    //language=SQL
    private static final String SQL_FIND_BY_LOGIN = "select * from users where login = ?";


    private SimpleJdbcTemplate simpleJdbcTemplate;
    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .login(row.getString("login"))
            .password(row.getString("password"))
            .UUID(row.getString("UUID"))
            .build();


    @Override
    public void save(User entity) {
        User user = (User) entity;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUUID());
            int updRows = ps.executeUpdate();
            if (updRows == 0) {
                //Если ничего не было изменено, значит возникла ошибка
                //Возбуждаем соответсвующее исключений
                throw new SQLException();
            }
            //Достаём созданное Id пользователя
            try (ResultSet set = ps.getGeneratedKeys();) {
                //Если id  существет, обновляем его у модели.
                if (set.next()) {
                    user.setId(set.getLong(1));
                } else {
                    //Модель сохранилась, но не удаётся получить сгенерированный id
                    //Возбуждаем соответвующее исключение
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);

        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List findById(Long id) {
        return null;
    }

    @Override
    public List<User> findByUUID(String UUID) {

        return simpleJdbcTemplate.query(SQL_FIND_BY_UUID, userRowMapper, UUID);

    }

    @Override
    public List<User> findByLogin(String login) {

        return simpleJdbcTemplate.query(SQL_FIND_BY_LOGIN, userRowMapper, login);

    }

    @Override
    public List<User> findAll() {

        return simpleJdbcTemplate.query(SQL_FIND_ALL, userRowMapper);

    }

}

