package com.nbnfsoft.update;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 16:38 2019-11-13
 */
public class DBUpgrade {
    static Logger log = Logger.getLogger(DBUpgrade.class);

    private static String driver = "oracle.jdbc.OracleDriver";
    private static String url = "";
    private static String user = "";
    private static String password = "!";

    private static String workUrl = "";
    private static String workUser = "";
    private static String workPassword = "";

    private static Properties config = new Properties();

    static {
        try {
            config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            url = config.getProperty("url");
            user = config.getProperty("username");
            password = config.getProperty("password");

            workUrl = config.getProperty("work_url");
            workUser = config.getProperty("work_username");
            workPassword = config.getProperty("work_password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        run();
    }

    private static void run() {
        log.info("本应用数据库连接:" + url);
        log.info("本应用数据库用户名:" + user);
        log.info("本应用数据库密码:" + password);

        log.info("主应用数据库连接:" + workUrl);
        log.info("主应用数据库用户名:" + workUser);
        log.info("主应用数据库密码:" + workPassword);
        log.info("-------------------------------");
        Scanner sc = new Scanner(System.in);
        try {
            log.info("按Y开始更新");
            String val = sc.next();
            if ("y".equals(val.toLowerCase())) {
                Collection<File> workFile = FileUtils.listFiles(new File(Thread.currentThread().getContextClassLoader().getResource("work").getPath()), new String[]{"sql"}, false);
                Collection<File> selfFile = FileUtils.listFiles(new File(Thread.currentThread().getContextClassLoader().getResource("self").getPath()), new String[]{"sql"}, false);
                List<File> workList = new ArrayList(workFile);
                List<File> selfList = new ArrayList(selfFile);
                for (int i = 0; i < workList.size(); i++) {
                    File work = workList.get(i);
                    File self = selfList.get(i);
                    String fileName = work.getName();
                    if (!fileName.startsWith("V") || !fileName.contains("__")) {
                        System.out.println("文件名:" + fileName + " 不符合规则,更新失败");
                        break;
                    }
                    if (!self.getName().startsWith("V") || !self.getName().contains("__")) {
                        System.out.println("文件名:" + self.getName() + " 不符合规则,更新失败");
                        break;
                    }
                    String currVersion = getCurrVersionNumber();
                    String updateVersion = fileName.split("__")[0].substring(1);
                    if (currVersion.compareTo(updateVersion) < 0) {
                        System.out.println("当前版本号：" + currVersion + "  更新版本号：" + updateVersion);
                        System.out.println("---------------升级中----------------");
                        log.info("开始更新主应用sql");
                        String[] worksSqls = FileUtils.readFileToString(work, "utf-8").split("#split#");
                        executeWorkSqls(worksSqls);
                        log.info("更新主应用sql结束");
                        log.info("开始更新本应用sql");
                        String[] selfSqls = FileUtils.readFileToString(self, "utf-8").split("#split#");
                        executeSelfSqls(selfSqls);
                        log.info("开始更新本应用sql");
                        updateVersionNumber(updateVersion);
                        log.info("更新本应用sql结束");
                        System.out.println("版本:" + updateVersion + " 升级完成");
                    }
                }
                log.info("-------------------------------");
                log.info("升级成功,退出操作");
            } else {
                log.info("-------------------------------");
                log.info("退出操作");
            }
        } catch (Exception ex) {
            log.error("数据库升级发生错误:" + ex.getMessage());
        }
    }


    public static void executeSelfSqls(String[] arrSqls) throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String driver = getDriver();
            Class.forName(driver);
            String url = getUrl();
            String user = getUser();
            String password = getPassword();
            connection = DriverManager.getConnection(url, user, password);
            String sql = "";
            try {
                statement = connection.createStatement();
                connection.setAutoCommit(false);
                for (int i = 0; i < arrSqls.length; ++i) {
                    sql = arrSqls[i];
                    sql = sql.replaceAll("\r|\n", "");
                    if (sql != null && !sql.equals("")) {
                        statement.addBatch(arrSqls[i]);
                    }
                }
                statement.executeBatch();
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw new Exception(e.getMessage());
            }
            connection.setAutoCommit(true);
        } catch (Exception ex) {
            closeAll(connection, statement, resultSet);
            throw new Exception(ex.getMessage());
        } finally {
            closeAll(connection, statement, resultSet);
        }
    }

    public static void executeWorkSqls(String[] arrSqls) throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String driver = getDriver();
            Class.forName(driver);
            String url = getWorkUrl();
            String user = getWorkUser();
            String password = getWorkPassword();
            connection = DriverManager.getConnection(url, user, password);
            String sql = "";
            try {
                statement = connection.createStatement();
                connection.setAutoCommit(false);
                for (int i = 0; i < arrSqls.length; ++i) {
                    sql = arrSqls[i];
                    sql = sql.replaceAll("\r|\n", "");
                    if (sql != null && !sql.equals("")) {
                        statement.addBatch(arrSqls[i]);
                    }
                }
                statement.executeBatch();
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw new Exception(e.getMessage());
            }
            connection.setAutoCommit(true);
        } catch (Exception ex) {
            closeAll(connection, statement, resultSet);
            throw new Exception(ex.getMessage());
        } finally {
            closeAll(connection, statement, resultSet);
        }
    }

    private static void updateVersionNumber(String versionNumber) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String driver = getDriver();
            Class.forName(driver);
            String url = getWorkUrl();
            String user = getWorkUser();
            String password = getWorkPassword();
            connection = DriverManager.getConnection(url, user, password);
            String sql = "update nfsys_parameters set value = ? where code='version_number' and app_code='appointment'";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, versionNumber);
            int line = preparedStatement.executeUpdate();
            if (line <= 0) {
                throw new Exception("更新版本号失败" + versionNumber);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            closeAll(connection, preparedStatement, resultSet);
        }
    }

    private static String getCurrVersionNumber() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String driver = getDriver();
            Class.forName(driver);
            String url = getWorkUrl();
            String user = getWorkUser();
            String password = getWorkPassword();
            connection = DriverManager.getConnection(url, user, password);
            int count = 0;
            String existsSql = "select count(*) as count from user_tables where table_name='NFSYS_PARAMETERS'";
            preparedStatement = connection.prepareStatement(existsSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
            if (count == 0) {
                return "0";
            }
            String sql = "select value from nfsys_parameters where code='version_number' and app_code='appointment'";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("value");
            }
        } catch (Exception e) {
            throw new Exception("获取版本号发生错误" + e.getMessage());
        } finally {
            closeAll(connection, preparedStatement, resultSet);
        }
        return "0";
    }

    private static void closeAll(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUser() {
        return user;
    }

    public static String getDriver() {
        return driver;
    }

    public static String getUrl() {
        return url;
    }

    public static String getPassword() {
        return password;
    }


    public static String getWorkUrl() {
        return workUrl;
    }


    public static String getWorkUser() {
        return workUser;
    }

    public static String getWorkPassword() {
        return workPassword;
    }

}
