package com.example.restfultest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Created by jonathanhavstad on 7/27/16.
 */
public class MySQLXYDataDAO implements IXYDataDAO {
    private Connection conn;
    private boolean initialized;
    private Exception initializationException;

    private void init() {
        try {
            LoadDriver.init();
            initialized = true;
        } catch (IllegalAccessException e) {
            initializationException = e;
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            initializationException = e;
            e.printStackTrace();
        } catch (InstantiationException e) {
            initializationException = e;
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/restfultestdb?" +
                    "user=restfultestuser&password=P@$$word1&useLegacyDatetimeCode=false&serverTimezone=EST");
            initialized = true;
        } catch (SQLException e) {
            initialized = false;
            initializationException = e;
            e.printStackTrace();
        }
    }

    public MySQLXYDataDAO() {
        this.init();
    }

    public XYDataBean getXYData() throws Exception {
        XYDataBean xyDataBean = null;
        if (this.initialized) {
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT x, y FROM restfultestdb.xydata");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sb.toString());
            rs.first();
            xyDataBean = new XYDataBean();
            xyDataBean.setX(rs.getDouble(1));
            xyDataBean.setY(rs.getDouble(2));

        } else {
            throw initializationException;
        }
        return xyDataBean;
    }

    public double getXData() throws Exception {
        double x = 0.0;
        if (this.initialized) {
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT x FROM restfultestdb.xydata");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sb.toString());
            rs.first();
            x = rs.getDouble(1);
        } else {
            throw initializationException;
        }
        return x;
    }

    public double getYData() throws Exception {
        double y = 0.0;
        if (this.initialized) {
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT y FROM restfultestdb.xydata");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sb.toString());
            rs.first();
            y = rs.getDouble(1);
        } else {
            throw initializationException;
        }
        return y;
    }

    public void updateXYData(XYDataBean xyDataBean) throws Exception {
        if (this.initialized) {
            StringBuffer sb = new StringBuffer();
            sb.append("UPDATE restfultestdb.xydata SET x=" + xyDataBean.getX() + ", y=" + xyDataBean.getY());
            Statement stmt = conn.createStatement();
            stmt.execute(sb.toString());
        } else {
            throw initializationException;
        }
    }

    public void updateXData(double x) throws Exception {
        if (this.initialized) {
            StringBuffer sb = new StringBuffer();
            sb.append("UPDATE restfultestdb.xydata SET x=" + x);
            Statement stmt = conn.createStatement();
            stmt.execute(sb.toString());
        } else {
            throw initializationException;
        }
    }

    public void updateYData(double y) throws Exception {
        if (this.initialized) {
            StringBuffer sb = new StringBuffer();
            sb.append("UPDATE restfultestdb.xydata SET y=" + y);
            Statement stmt = conn.createStatement();
            stmt.execute(sb.toString());
        } else {
            throw initializationException;
        }
    }
}
