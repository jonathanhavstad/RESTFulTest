package com.example;

import javax.json.*;

import java.io.File;
import java.util.ArrayList;

import static java.lang.Math.PI;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestListener;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * Created by jonathanhavstad on 7/12/16.
 */
@Path("data/1.0")
public class RESTFulTestResource {
    private int x, y; // This should be a database resource

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("x/{x}/y/{y}")
    public String getXY(@PathParam("x") String x, @PathParam("y") String y) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("x", this.x);
        jsonObjectBuilder.add("y", this.y);
        JsonObject jObject = jsonObjectBuilder.build();

        return jObject.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("x")
    public String getX() {
        // Execute a query to a database (SELECT) for x
        // Retrieve the results
        // Store the result into x
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("x", this.x);
        JsonObject jObject = jsonObjectBuilder.build();

        ServletContextListener contextListener;
        ServletRequestListener requestListener;
        return jObject.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("y")
    public String getY() {
        // Execute a query to a database (SELECT) for y
        // Retrieve the results
        // Store the result into y
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("y", this.y);
        JsonObject jObject = jsonObjectBuilder.build();
        return jObject.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("resource")
    public String getData() {
        // Execute a query to a database (SELECT) for x & y
        // Retrieve the results
        // Store the result into x & y
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("x", this.x);
        jsonObjectBuilder.add("y", this.y);
        JsonObject jObject = jsonObjectBuilder.build();

        return jObject.toString();
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("x")
    public Response setX(String x, @Context UriInfo uriInfo, @Context HttpServletResponse httpServletResponse) {
        this.x = Integer.parseInt(x);
        // Execute a query on the database (INSERT) for x
        // Set the value of x equal to the value of x
        // Return
        File file = new File("/Users/jonathanhavstad/Documents/workspace/html_test/test2.html");
        ResponseBuilder response = Response.ok((Object)file);
        response.header("Content-Disposition", "attachment: filename=file.html");
        return response.build();
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("y")
    public Response setY(@DefaultValue("") @QueryParam("y") String y, @Context HttpHeaders headers) {
        // Execute a query on the database (INSERT) for x
        // Set the value of x equal to the value of x
        // Return
        return Response.accepted(y).build();
    }
}
