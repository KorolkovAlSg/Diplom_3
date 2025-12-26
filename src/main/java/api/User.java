package api;

import constans.Constants;
import constans.Endpoints;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import serialization.ProfileUser;

import static io.restassured.RestAssured.given;

public class User {
    private final ProfileUser profile;

    public User(ProfileUser profile){
        this.profile=profile;
    }

    @Step("Send POST request to /api/auth/register")
    public void createUser() {
        RestAssured.baseURI = Constants.MAIN_PAGE;
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(profile)
                        .when()
                        .post(Endpoints.REGISTER);
    }

    @Step("Send POST request to /api/auth/login")
    public Response logInUser(){
        RestAssured.baseURI = Constants.MAIN_PAGE;
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(profile)
                .when()
                .post(Endpoints.LOGIN);
    }

    public String getAToken(){
        return logInUser().body().path("accessToken").toString().split(" ")[1];
    }

    @Step("Send DELETE request to /api/auth/user")
    public void deleteUser() {
        RestAssured.baseURI = Constants.MAIN_PAGE;
        given()
                .header("Content-type", "application/json")
                .auth().oauth2(getAToken())
                .when()
                .delete(Endpoints.USER);
    }
}
