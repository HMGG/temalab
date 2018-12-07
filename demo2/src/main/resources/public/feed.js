"use strict";
var feed = document.getElementById("feedactions");
var newpost = document.getElementById("newpost");
var posttext = document.getElementById("posttext");
var submitpost = document.getElementById("submitpost");
var postdistance = document.getElementById("postdistance");
var setpostdist = document.getElementById("setpostdist");
var postrecency = document.getElementById("postrecency");
var setpostrecency = document.getElementById("setpostrecency");
var focuseduser = document.getElementById("focuseduser");
var focusedtext = document.getElementById("focusedtext");
var newcomment = document.getElementById("newcomment");
var removecomment = document.getElementById("removecomment");
var focusedcomments = document.getElementById("focusedcomments");

class Post {
    constructor(id, text, postTime, latitude, longitude, userid) {
        this.id = id;
        this.text = text;
        this.postTime = postTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userid = userid;
    }
}

var posts = [];
var focusedpost;

submitpost.onclick = function () {
    var mypost = new Post(0, posttext.value, new Date().getTime(), currentlatitude, currentlongitude, me.id);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/feed/" + sid,
        data: JSON.stringify(mypost),
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            data = JSON.parse(data);
            if (data == 0)
                alert("Post creation was unsuccessful");
            else {
                mypost.id = data;
                focuseduser.innerHTML = me.name;
                focusedtext.innerHTML = mypost.text;
                focusedpost = mypost;
                posts.push(mypost);
                buildFeed(mypost);
            }
        }
    })
}
setpostdist.onclick = function () {
    let latitude = currentlatitude;
    let longitude = currentlongitude;
    if (me.constantloc) {
        latitude = me.latitude;
        longitude = me.longitude;
    }
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/feed/nearby/" + sid + "/" + latitude + "/" + longitude + "/" + postdistance.value,
        success: function (data) {
            posts = data;
            buildFeed(null);
        }
    })
}
setpostrecency.onclick = function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/feed/recent/" + new Date(postrecency.value).getTime().toString() + "/" + sid,
        success: function (data) {
            posts = data;
            buildFeed(null);
        }
    })
}

document.getElementById("feed").onclick = function () {
    buildFeed(null);
}

function buildFeed(postin) {
    hideall();
    feed.hidden = false;
    setPostsPin(posts);
    if(postin!=null){
    focusedtext.innerHTML = postin.text;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/user/" + postin.userid,
        success: function (data) {
            focuseduser.innerHTML = data.name;
        }
    })
}
}

function setPostsPin(inposts) {
    var gposts = [];
    for (var post of inposts) {
        gposts.push({
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [post.latitude, post.longitude]
            },
            "properties": {
                "post": post
            }
        });
    }
    data.features = gposts;
}