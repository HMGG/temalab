"use strict";
var storedsid = JSON.parse(localStorage.getItem("sid"));
var storedemail = JSON.parse(localStorage.getItem("email"));
var storedpassword = JSON.parse(localStorage.getItem("password"));
var sid = storedsid;
$.ajax({
    type: "PUT",
    url: "http://localhost:8080/login",
    data: "{\"email\":\"" + storedemail + "\",\"password\":\"" + storedpassword + "\",\"sid\":" + storedsid + "}",
    contentType: "application/json",
    dataType: "json",
    success: function (data) {
        if (data == 0)
             sid = (Math.random()-0.5)*9223372036854775807;
        else {
            loginscreen.hidden = true;
            main.hidden = false;
            me.email = storedemail;
            me.password = storedpassword;
            me.id = data;
            buildFeed(null);
        }
    }
})

var currentlatitude;
var currentlongitude;

class User {
    constructor(id, name, email, bio, birthday, password, latitude, longitude, cl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.birthday = birthday;
        this.password = password;
        this.sessionId = sid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.constantloc = cl;
    }
}

var me = new User(null, null, null, null, null, null, null, null);

var loginscreen = document.getElementById("login&register");
var main = document.getElementById("main");

document.getElementById("loginbutton").onclick = function login() {
    let email = document.getElementById("loginemail").value;
    let password = document.getElementById("loginpassword").value;
    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/login",
        data: "{\"email\":\"" + email + "\",\"password\":\"" + password + "\",\"sid\":" + sid + "}",
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            if (data == 0)
                alert("Invalid login");
            else {
                localStorage.setItem("sid", JSON.stringify(sid));
                localStorage.setItem("email", JSON.stringify(email));
                localStorage.setItem("password", JSON.stringify(password));
                loginscreen.hidden = true;
                main.hidden = false;
                me.email = email;
                me.password = password;
                me.id = data;
                buildFeed(null);
                $.ajax({
                    type: "GET",
                    url: "http://localhost:8080/user/"+me.id,
                    success: function(data){
                        me.name = data.name;
                        me.bio = data.bio;
                        me.birthday = data.birthday;
                        me.latitude = data.latitude;
                        me.longitude = data.longitude;
                        me.constantloc = data.constantloc;
                    }
                })
            }
        }
    })
}

var regbirthdayfield = document.getElementById("regbirthdayfield");
var reglocation = document.getElementById("reglocation");
var constantloc;
document.getElementById("business").onclick = function choosebuisness() {
    regbirthdayfield.hidden = true;
    reglocation.hidden = false;
    constantloc = true;
}
document.getElementById("natural").onclick = function choosenatural() {
    regbirthdayfield.hidden = false;
    reglocation.hidden = true;
    constantloc = false;
}

document.getElementById("regbutton").onclick = function () {
    let email = document.getElementById("regemail").value;
    let password = document.getElementById("regpassword").value;
    let name = document.getElementById("regname").value;
    let birthday = new Date(document.getElementById("regbirthday").value).getTime();

    me = new User(null, name, email, null, birthday, password, currentlatitude, currentlongitude, constantloc);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/register",
        data: JSON.stringify(me),
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            if (data == 0)
                alert("Invalid fields/User already exists");
            else {
                localStorage.setItem("sid", JSON.stringify(sid));
                localStorage.setItem("email", JSON.stringify(email));
                localStorage.setItem("password", JSON.stringify(password));
                me.id = data;
                loginscreen.hidden = true;
                main.hidden = false;
                buildFeed(null);
            }
        }
    })
}