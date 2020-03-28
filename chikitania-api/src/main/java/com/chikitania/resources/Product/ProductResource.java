package com.chikitania.resources.Product;

import com.google.inject.Inject;
import com.chikitania.api.Product;
import com.chikitania.api.custom.ProductResponse;
import com.chikitania.resources.Product.service.ProductService;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;
import org.apache.http.HttpStatus;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ProductResource.NAME)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = ProductResource.NAME, description = " Product Operation")
public class ProductResource {
    public static final String NAME = "product";

    public final ProductService productService;

    @Inject
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @POST
    @RolesAllowed({"PRODUCT.CREATE"})
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @ApiOperation(value = "Register a new Product")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Product created",
                    response = Product.class)})
    public Response create(@ApiParam(value = "Product entity", required = true)
                                  @NotNull @Valid ProductResponse product) {
        return Response.ok(productService.save(product)).build();
    }

    @GET
    @RolesAllowed({"PRODUCT.LIST"})
    @UnitOfWork
    @ApiOperation(value = "Returns all Product")

    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "List all Product",
                    response = Product.class, responseContainer = "List")})
    public Response getAll() {
        return Response.ok(productService.getAll()).build();
    }

    @GET
    @RolesAllowed({"PRODUCT.LIST", "PRODUCT.VIEW"})
    @Path("{id}")
    @UnitOfWork
    @ApiOperation(value = "Returns Product by Id")

    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "The Product requested by Id",
                    response = Product.class),
            @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = "Not Found Product for provided Id")})
    public Response getById(@ApiParam(value = "Product ID", required = true)
                            @PathParam("id") Integer productId) throws NotFoundException {
        return Response.ok(productService.getById(productId)).build();
    }

    @PUT
    @RolesAllowed({"PRODUCT.EDIT"})
    @UnitOfWork
    @ApiOperation(value = "Update Product")

    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Updated Product")})
    public Response update(@ApiParam(value = "Product entity", required = true)
                                  @NotNull @Valid ProductResponse request) {
        try {
            productService.update(request);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(HttpStatus.SC_NOT_FOUND).build();
        }
    }

    @DELETE
    @RolesAllowed({"PRODUCT.DELETE"})
    @Path("{id}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "DELETE a Product")

    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = "Product updated",
                    response = Product.class),
            @ApiResponse(code = HttpStatus.SC_OK, message = "Product not found")
    })
    public Response delete(@PathParam("id") @NotNull @Valid Integer id) {
        try {
            productService.delete(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(HttpStatus.SC_NOT_FOUND).build();
        }
    }
}