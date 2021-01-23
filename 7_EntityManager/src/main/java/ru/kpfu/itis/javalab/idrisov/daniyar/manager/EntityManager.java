package ru.kpfu.itis.javalab.idrisov.daniyar.manager;

import ru.kpfu.itis.javalab.idrisov.daniyar.repositories.JdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Field;

public class EntityManager {

    private JdbcTemplate jdbcTemplate;

    public EntityManager(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public <T> void createTable(String tableName, Class<T> entityClass) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE " + tableName + " (");

        Field[] fields = entityClass.getDeclaredFields();

        for (Field field : fields) {
            String s = "";
            if (field.getType().getSimpleName().equals("Long") || field.getType().getSimpleName().equals("long")) {
                s = "bigint";
            } else {
                if (field.getType().getSimpleName().equals("String")) {
                    s = "varchar";
                } else {
                    if (field.getType().getSimpleName().equals("Integer") || field.getType().getSimpleName().equals("int")) {
                        s = "int";
                    } else {
                        if (field.getType().getSimpleName().equals("Boolean") || field.getType().getSimpleName().equals("boolean")) {
                            s = "boolean";
                        }
                    }
                }
            }
            stringBuilder.append(field.getName() + " " + s + ", ");
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(")");

        jdbcTemplate.query(stringBuilder.toString());
    }

    public void save(String tableName, Object entity) {

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();

        stringBuilder.append("INSERT INTO " + tableName + " (");
        stringBuilder1.append("(");

        Class<?> aClass = entity.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            stringBuilder.append(field.getName() + ", ");
            try {
                field.setAccessible(true);
                stringBuilder1.append("'" + field.get(entity) + "', ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(") VALUES ");

        stringBuilder1.setLength(stringBuilder1.length() - 2);
        stringBuilder1.append(")");

        stringBuilder.append(stringBuilder1);

        jdbcTemplate.query(stringBuilder.toString());
    }

    // User user = entityManager.findById("account", User.class, Long.class, 10L);
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {
        Field[] fields = resultType.getDeclaredFields();
        String id = null;
        for (Field field : fields) {
            if (field.getName().equals("id")) {
                id = field.getName();
            }
        }
        if (id != null) {
            String sql = "SELECT * FROM " + tableName + " WHERE " + id + " = " + idValue.toString();
            return jdbcTemplate.findById(sql, resultType);
        }
        return null;
    }

}
