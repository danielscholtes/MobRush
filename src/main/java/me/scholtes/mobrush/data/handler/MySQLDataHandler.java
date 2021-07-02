package me.scholtes.mobrush.data.handler;

import com.zaxxer.hikari.HikariDataSource;
import me.scholtes.mobrush.MobRushPlugin;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class MySQLDataHandler {

    private Jdbi jdbi;
    private HikariDataSource dataSource;

    /**
     * Initializes and connects to the database
     * @param plugin Instance of the plugin
     */
    public MySQLDataHandler(MobRushPlugin plugin) {
        dataSource = new HikariDataSource();

        String address = plugin.getConfig().getString("database.address");
        String databaseName = plugin.getConfig().getString("database.db_name");
        String username = plugin.getConfig().getString("database.username");
        String password = plugin.getConfig().getString("database.password");
        String port = plugin.getConfig().getString("database.port");

        dataSource.setMaximumPoolSize(10);
        dataSource.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        dataSource.addDataSourceProperty("serverName", address);
        dataSource.addDataSourceProperty("port", port);
        dataSource.addDataSourceProperty("databaseName", databaseName);
        dataSource.addDataSourceProperty("user", username);
        dataSource.addDataSourceProperty("password", password);
        dataSource.addDataSourceProperty("useSSL", false);

        jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
    }

    public HikariDataSource getDataSource() {
        return this.dataSource;
    }

    public Jdbi getJdbi() {
        return this.jdbi;
    }
}
