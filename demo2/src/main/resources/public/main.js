"use strict";
var map;
mapboxgl.accessToken = 'pk.eyJ1IjoidHVwaXgiLCJhIjoiY2pwZDB1emEyM2Q4MzNxb2JibXdxb2VzZCJ9.Rss9u6xlRoHQQ5G2lTyOKA';

var data = {"type": "FeatureCollection",
"features": [{
    "type": "Feature",
    "geometry": {
        "type": "Point",
        "coordinates": [19, 48]
    }}]};

function newMap(lat, long) {
    map = new mapboxgl.Map({
        container: 'mapid',
        style: 'mapbox://styles/mapbox/streets-v9',
        center: [lat, long],
        zoom: 13
    });
    map.on('load', function () {
        map.loadImage('/piros.png', function (error, image) {
            if (error) throw error;
            map.addImage('pin', image);
            map.addLayer({
                "id": "points",
                "type": "symbol",
                "source": {
                    "type": "geojson",
                    "data": {
                        "type": "FeatureCollection",
                        "features": [{
                            "type": "Feature",
                            "geometry": {
                                "type": "Point",
                                "coordinates": [lat, long]
                            }
                        }]
                    }
                },
                "layout": {
                    "icon-image": "pin",
                    "icon-size": 0.1
                }
            });
        });
        map.addSource('trace', { type: 'geojson', data: data });
        map.loadImage('/kek.png', function (error, image) {
            if (error) throw error;
            map.addImage('postpin', image);
            map.addLayer({
                "id": "postpoints",
                "type": "symbol",
                "source": "trace",
                "layout": {
                    "icon-image": "postpin",
                    "icon-size": 0.1
                }
            });
        });
        var timer = window.setInterval(function() {
                map.getSource('trace').setData(data);
        }, 10);

    });
    map.on('click', function (e) {        
        var features = map.queryRenderedFeatures(e.point, { layers: ['postpoints'] });
        if (features.length) {
            focusedpost = features[0];
            focusedevent = features[0];
            if(focusedpost.properties.post!=null)
                buildFeed(focusedpost.properties.post);
            if(focusedevent.properties.event!=null)
                buildEvents(focusedpost.properties.event);
        }
    });
}

navigator.geolocation.getCurrentPosition(setLocation);

function setLocation(position) {
    currentlatitude = position.coords.longitude;
    currentlongitude = position.coords.latitude;
    newMap(currentlatitude, currentlongitude);
}

class Geometry {
    constructor(type, coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
}



var nearbyusers = document.getElementById("nearbyactions");
var getnearbyusers = document.getElementById("getnearbyusers");
var searchdistance = document.getElementById("searchdistance");
var setdistance = document.getElementById("setdistance");
var onlyfollowing = document.getElementById("onlyfollowing");

var profile = document.getElementById("profileactions");
var myname = document.getElementById("myname");
var mybio = document.getElementById("mybio");
var mybirthday = document.getElementById("mybirthday");
var mypassword = document.getElementById("mypassword");
var editprofile = document.getElementById("editprofile");
var saveprofile = document.getElementById("saveprofile");
var followers = document.getElementById("followers");
var following = document.getElementById("following");
var myevents = document.getElementById("myevents");

function hideall() {
    feed.hidden = true;
    events.hidden = true;
    nearbyusers.hidden = true;
    profile.hidden = true;
}

var logout = document.getElementById("logout").onclick = function () {
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/logout/" + sid,
        success: function () {
            sid = (Math.random() - 0.5) * 9223372036854775807;
            main.hidden = true;
            loginscreen.hidden = false;
            document.getElementById("loginpassword").value = '';
        }
    })
}



document.getElementById("nearbyusers").onclick = function () {
    buildNearbyUsers();
}
function buildNearbyUsers() {
    hideall();
    nearbyusers.hidden = false;
}

