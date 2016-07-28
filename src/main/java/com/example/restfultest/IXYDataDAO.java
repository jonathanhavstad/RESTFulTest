package com.example.restfultest;

/**
 * Created by jonathanhavstad on 7/27/16.
 */
public interface IXYDataDAO {
    public XYDataBean getXYData() throws Exception;

    public double getXData() throws Exception;

    public double getYData() throws Exception;

    public void updateXYData(XYDataBean xyDataBean) throws Exception;

    public void updateXData(double x) throws Exception;

    public void updateYData(double y) throws Exception;
}
