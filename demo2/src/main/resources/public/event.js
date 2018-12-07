"use strict";
var events = document.getElementById("eventactions");
var newevent = document.getElementById("newevent");
var eventtitle = document.getElementById("eventtitle");
var submitevent = document.getElementById("submitevent");
var nearbyevents = document.getElementById("nearbyevents");
var eventdistance = document.getElementById("eventdistance");
var seteventdist = document.getElementById("seteventdist");
var upcomingevents = document.getElementById("upcomingevents");
var focusedevent = document.getElementById("focusedevent");
var focusedhost = document.getElementById("focusedhost");
var focusedtitle = document.getElementById("focusedtitle");
var attend = document.getElementById("attend");
var unattend = document.getElementById("unattend");
var focusedattendees = document.getElementById("focusedattendees");

class Event {
    constructor(id, title, time, latitude, longitude, creatorid) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.creatorid = creatorid;
    }
}

var eventsa = [];
var focusedevent;

submitevent.onclick = function () {
    var myevent = new Event(0, eventtitle.value, new Date().getTime(), currentlatitude, currentlongitude, me.id);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/events/" + sid,
        data: JSON.stringify(myevent),
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            data = JSON.parse(data);
            if (data == 0)
                alert("Event creation was unsuccessful");
            else {
                myevent.id = data;
                focusedhost.innerHTML = me.name;
                focusedtitle.innerHTML = myevent.title;
                focusedevent = myevent;
                eventsa.push(myevent);
                buildEvents(myevent);
            }
        }
    })
}
seteventdist.onclick = function () {
    let latitude = currentlatitude;
    let longitude = currentlongitude;
    if (me.constantloc) {
        latitude = me.latitude;
        longitude = me.longitude;
    }
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/events/nearby/" + latitude + "/" + longitude + "/" + eventdistance.value,
        success: function (data) {
            eventsa = data;
            buildEvents(null);
        }
    })
}
upcomingevents.onclick = function () {
    let latitude = currentlatitude;
    let longitude = currentlongitude;
    if (me.constantloc) {
        latitude = me.latitude;
        longitude = me.longitude;
    }
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/events/upcoming/" + new Date().getTime().toString() + "/" + latitude + "/" + longitude + "/" + eventdistance.value,
        success: function (data) {
            eventsa = data;
            buildEvents(null);
        }
    })
}

document.getElementById("events").onclick = function () {
    buildEvents();
}

function buildEvents(eventin) {
    hideall();
    events.hidden = false;
    setEventsPin(eventsa);
    if(eventin!=null){
    focusedtitle.innerHTML = eventin.title;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/user/" + eventin.creatorid,
        success: function (data) {
            focusedhost.innerHTML = data.name;
        }
    })
}
}

function setEventsPin(inevents) {
    var gevents = [];
    for (var event of inevents) {
        gevents.push({
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [event.latitude, event.longitude]
            },
            "properties": {
                "event": event
            }
        });
    }
    data.features = gevents;
}