package com.example.restfultest;

import javax.json.*;

import java.io.File;
import java.util.ArrayList;

import static java.lang.Math.PI;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestListener;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jonathanhavstad on 7/12/16.
 */
@Path("data/1.0")
public class XYDataResource {

    public XYDataResource() {
        xyDataDAO = new MySQLXYDataDAO();
    }

    private IXYDataDAO xyDataDAO;

    public IXYDataDAO getXyDataDAO() {
        return xyDataDAO;
    }

    public void setXyDataDAO(IXYDataDAO xyDataDAO) {
        this.xyDataDAO = xyDataDAO;
    }

    @GET
    @Path("xy")
    public Response getXY() {
        XYDataBean xyDataBean = null;
        try {
            xyDataBean = xyDataDAO.getXYData();
        } catch (Exception e) {
            return Response.serverError().build();
        }
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("x", xyDataBean.getX());
        jsonObjectBuilder.add("y", xyDataBean.getY());
        JsonObject jObject = jsonObjectBuilder.build();
        return Response.ok(jObject.toString(), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("x")
    public Response getX() {
        // Execute a query to a database (SELECT) for x
        // Retrieve the results
        // Store the result into x
        XYDataBean xyDataBean = null;
        try {
            xyDataBean = xyDataDAO.getXYData();
        } catch (Exception e) {
            return Response.ok(e.getMessage(), MediaType.TEXT_PLAIN_TYPE).build();
        }
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("x", xyDataBean.getX());
        JsonObject jObject = jsonObjectBuilder.build();

        ServletContextListener contextListener;
        ServletRequestListener requestListener;
        return Response.ok(jObject.toString(), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("y")
    public Response getY() {
        // Execute a query to a database (SELECT) for y
        // Retrieve the results
        // Store the result into y
        XYDataBean xyDataBean = null;
        try {
            xyDataBean = xyDataDAO.getXYData();
        } catch (Exception e) {
            return Response.serverError().build();
        }
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("y", xyDataBean.getY());
        JsonObject jObject = jsonObjectBuilder.build();
        return Response.ok(jObject.toString(), MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("xy")
    public Response setXY(@QueryParam("x") String x, @QueryParam("y") String y) {
        // Execute a query on the database (UPDATE) for x
        // Set the value of x equal to the value of x
        // Return
        XYDataBean xyDataBean = new XYDataBean();
        xyDataBean.setX(Integer.parseInt(x));
        xyDataBean.setY(Integer.parseInt(y));
        try {
            xyDataDAO.updateXYData(xyDataBean);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.accepted(x + y).build();
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("x")
    public Response setX(@QueryParam("x") String x) {
        try {
            xyDataDAO.updateXData(Integer.parseInt(x));
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.accepted(x).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("y")
    public Response setY(@FormParam("y") String y) {
        // Execute a query on the database (UPDATE) for x
        // Set the value of x equal to the value of x
        // Return
        try {
            xyDataDAO.updateYData(Integer.parseInt(y));
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.accepted(y).build();
    }

}
