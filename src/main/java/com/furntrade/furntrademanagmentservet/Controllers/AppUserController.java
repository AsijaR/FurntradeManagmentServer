package com.furntrade.furntrademanagmentservet.Controllers;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.furntrade.furntrademanagmentservet.Dtos.AccountDto;
import com.furntrade.furntrademanagmentservet.Dtos.EmployeeInfoDto;
import com.furntrade.furntrademanagmentservet.Dtos.UserChangePasswordDto;
import com.furntrade.furntrademanagmentservet.Models.AppRole;
import com.furntrade.furntrademanagmentservet.Models.AppUser;
import com.furntrade.furntrademanagmentservet.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AppUserController {

    private final UserService userService;


    @GetMapping("/all")
    public CollectionModel<EmployeeInfoDto> All(){
       return CollectionModel.of(userService.getUsers(), linkTo(methodOn(AppUserController.class).All()).withSelfRel());
    }
    @GetMapping("/{username}")
    public ResponseEntity<EmployeeInfoDto> One(@PathVariable String username)
    {
        EmployeeInfoDto user=userService.getUser(username);
        if (user==null)
            return (ResponseEntity<EmployeeInfoDto>) ResponseEntity.notFound();
        return ResponseEntity.ok().body(user);//assembler.toModel(appUser);
    }
    @PostMapping("/add-employee")
    public ResponseEntity<?> saveUser(@RequestBody AccountDto newUser) throws ParseException {
        if(userService.saveUser(newUser)!=null)
        {
            URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/add-employee").toUriString());
            return ResponseEntity.created(uri).body(userService.saveUser(newUser));
        }
        else return ResponseEntity.badRequest().body("Username is taken");
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{username}/change-password")
    public ResponseEntity<?> changePassword(@PathVariable String username,@RequestBody UserChangePasswordDto passwordDto) {
        if(userService.changePassword(username,passwordDto))
            return ResponseEntity.ok("Password successfully changed");
        else
            return ResponseEntity.badRequest().body("Password are not matching");
    }
    @PutMapping("/{username}/update")
    ResponseEntity<?> UpdateUser(@PathVariable String username,@RequestBody EmployeeInfoDto userDto)
    {
       if(userService.updateProfile(username,userDto)!=null)
           return ResponseEntity.ok().body(userDto);
       else return ResponseEntity.notFound().build();
    }
    @GetMapping("/role/save")
    public ResponseEntity<AppRole> saveRole(@RequestBody AppRole role){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @GetMapping("token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader=request.getHeader(AUTHORIZATION);
        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer "))
        {
            try {
                String refresh_token=authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm=Algorithm.HMAC256("this is super secret".getBytes());
                JWTVerifier verifier= JWT.require(algorithm).build();
                DecodedJWT decodedJWT=verifier.verify(refresh_token);
                String username=decodedJWT.getSubject();
                AppUser user=userService.getUserApp(username);
                String access_token= JWT.create()
                        .withSubject(user.getUsername())
                        //ovo je 10mimn
                        .withExpiresAt(new Date(System.currentTimeMillis()+120*60*1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles",user.getRoles().stream().map(AppRole::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> tokens=new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            }
            catch (Exception ex){
                response.setHeader("error",ex.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String,String> error=new HashMap<>();
                error.put("error_message",ex.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
        }
        else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
//    @GetMapping("/role/add-role")
//    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
//        userService.addRoleToUser(form.getUsername(),form.getRoleName());
//        return ResponseEntity.ok().build();
//    }
}

//@Data
//class RoleToUserForm{
//    private String username;
//    private String roleName;
//}