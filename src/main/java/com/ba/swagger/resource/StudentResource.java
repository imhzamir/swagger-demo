package com.ba.swagger.resource;

import com.ba.swagger.bean.StudentInfo;
import com.ba.swagger.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public record StudentResource(StudentService service, ObjectMapper objectMapper) {

    @GetMapping
    @Operation(summary = "Retrieve all Students", tags = {"students", "get",})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = StudentInfo.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "There are no Students", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok().body(service.getStudents());
    }


    @GetMapping("{oid}")
    @Operation(
            summary = "Retrieve a Student by Id",
            description = "Get a Student object by specifying its id. The response is Student object with oid, name, phone",
            tags = {"students", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = StudentInfo.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
    })
    public ResponseEntity<?> getStudentById(@PathVariable("oid") Integer oid) {
        return ResponseEntity.ok().body(service.getStudentById(oid));
    }

    @PostMapping
    @Operation(summary = "Create a new Student", tags = {"students", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = StudentInfo.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    public ResponseEntity<?> createStudent(@RequestBody StudentInfo studentInfo) {
        return new ResponseEntity<>(service.saveStudent(studentInfo), HttpStatus.CREATED);
    }

    @DeleteMapping("{oid}")
    @Operation(
            summary = "Remove a Student by Id",
            description = "Remove student from DB",
            tags = {"students", "delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = String.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    public ResponseEntity<?> removeStudentInfo(@PathVariable("oid") Integer oid) {
        service.removeStudent(oid);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("msg", "Successfully Deleted");
        return ResponseEntity.ok(objectNode);
    }

    @PutMapping("{oid}")
    @Operation(summary = "Update existing student by id", tags = {"students", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = StudentInfo.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    public ResponseEntity<?> updateStudentInfo(@PathVariable("oid") Integer oid, @RequestBody StudentInfo studentInfo) {
        return new ResponseEntity<>(service.updateStudent(oid, studentInfo), HttpStatus.CREATED);
    }

}
