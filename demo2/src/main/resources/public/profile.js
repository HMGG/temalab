"use strict";
document.getElementById("profile").onclick = function () {
    buildProfile();
}
function buildProfile(userid) {
    hideall();
    profile.hidden = false;
    if (userid != me.id) {
        editprofile.hidden = true;
        saveprofile.hidden = true;
        mypassword.hidden = true;
        document.getElementById("passwordlabel").hidden = true;
    }
}