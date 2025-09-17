package br.com.muniz.controllers.docs;

import br.com.muniz.data.dto.persondto.PersonDTOV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PersonControllerDocs {

    @Operation(summary = "Find by id", description = "Finds one person", tags = "people",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTOV1.class))),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
    PersonDTOV1 findBiId(@PathVariable("id") Long id);

    @Operation(summary = "Find all people", description = "Finds all people", tags = "people",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTOV1.class))
                                    )
                            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<PagedModel<EntityModel<PersonDTOV1>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
            @RequestParam(value = "Direction", defaultValue = "asc") String direction
    );

    @Operation(summary = "Find people by name", description = "Finds people by their name", tags = "people",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTOV1.class))
                                    )
                            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<PagedModel<EntityModel<PersonDTOV1>>> findByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
            @RequestParam(value = "Direction", defaultValue = "asc") String direction
    );

    @Operation(summary = "Create a person", description = "Create a person", tags = "people",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTOV1.class))),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
    PersonDTOV1 create(@RequestBody PersonDTOV1 person);

    @Operation(summary = "Update a person", description = "Update a person", tags = "people",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTOV1.class))),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
    PersonDTOV1 update(@RequestBody PersonDTOV1 person);

    @Operation(summary = "disable by id", description = "disable one person", tags = "people",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTOV1.class))),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
    PersonDTOV1 disablePerson(@PathVariable("id") Long id);

    @Operation(summary = "disable by id", description = "disable one person", tags = "people",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTOV1.class))),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<?> delete(@PathVariable("id") Long id);
}
