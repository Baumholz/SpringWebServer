package com.example.springwebserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.stream.Collectors;


@RestController
public class MyRestController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String index(HttpServletRequest request) throws Exception {
        String ipAdress = request.getRemoteAddr();
        DBController dbc = new DBController(jdbcTemplate);
        dbc.insert(ipAdress);
        IpAddress ipAdressObject = dbc.getIpAddress(ipAdress);
        return String.format("Your IP-adress is: %s. You visited this Site already %s times.", ipAdress, ipAdressObject.getCountCall());
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/whoami")
    public String whoAmI(@RequestHeader MultiValueMap<String, String> headers, HttpServletRequest request) {
        String ipAdress = request.getRemoteAddr();
        String connection;
        String clientPlatform;
        String browser;

        headers.forEach((key, value) -> {
            String header = String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|")));
            System.out.println(header);
        });

        try {
            connection = headers.get("connection").get(0);
        }catch(NullPointerException ignored){
            connection = "No Information";
        }
        try{
            clientPlatform = headers.get("sec-ch-ua-platform").get(0);
        }catch(NullPointerException ignored){
            clientPlatform = "No Information";
        }
        try{
            browser = headers.get("user-agent").get(0);
        }catch(NullPointerException ignored){
            browser = "No Information";
        }


        return String.format("Connection: %s Client Platform: %s IP-address: %s Client Browser: %s", connection, clientPlatform, ipAdress, browser);
    }

    @GetMapping("/adapt2user”")
    public String adapt2User(@RequestHeader MultiValueMap<String, String> headers, HttpServletRequest request) {
        String ipAdress = request.getRemoteAddr();

        String language;
        String browser;
        String returnString;

        String returnLangugae;
        String returnBrowser;
        String returnIP;

        boolean german = false;

        try {
            language = headers.get("accept-language").get(0);
        }catch(NullPointerException ignored){
            language = "No Information";
        }
        try{
            browser = headers.get("user-agent").get(0);
        }catch(NullPointerException ignored){
            browser = "No Information";
        }

        if(language.equals("de-DE")){
            returnLangugae = "Deine bevorzugte Sprache ist Deutsch. ";
            german = true;
        }if(language.equals("")){
            returnLangugae = "Your preferred language is english. ";
        }else{
            returnLangugae = " Your preferred language was not suported. ";
        }

        if (browser.equals("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36") && german){
            returnBrowser = "Hallo du verwendest GoogleChrom." ;
        }else if (browser.equals("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36")){
            returnBrowser = "Hello you are using GoogleChrom. ";
        }else if (browser.equals("Client Browser: Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko") && german){
            returnBrowser = "Hallo du verwendest den Internet-Explorer";
        }else if (browser.equals("Client Browser: Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")){
            returnBrowser = "Hello you are using the Internet-Explorer. ";
        }else if(german){
            returnBrowser = "Dein Browser ist uns leider unbekannt. Browser: " + browser;
        }else{
            returnBrowser = "Your Browser is sadly not supported by our system. Browser: " + browser;
        }

        if(ipAdress == ""){
           // returnedString = returnedString + "";
        }

        return "";
    }

    @GetMapping("/test")
    @ResponseBody
    public String processData(@RequestParam(defaultValue = "test") String id, @RequestParam(defaultValue = "MaxMustermann") String name) {
        return "ID: " + id + " Name: " + name;
    }

}

