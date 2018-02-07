function lateSliderChange(minsLate) {
    document.getElementById('lateSliderStatus').innerHTML = minsLate;
}

function togElement(x) {
    var sl = document.getElementById(x);
    var ds = sl.style.display;
    if (ds === 'block') {
        //is visible
        sl.style.display = 'none';
    } else {
        sl.style.display = 'block';
    }
}

function elementOn(x) {
    var e = document.getElementById(x);
    e.style.display = 'block';
}

function elementOff(x) {
    var e = document.getElementById(x);
    e.style.display = 'none';
}



function validate_user_text() {
    var swear_words_arr = new Array("bad", "evil", "freak");
    var regex = new RegExp('\\b(' + swear_words_arr.join('|') + ')\\b', 'i');
    var text = $('#otherComments').text();

    if (regex.test(text)) {
        alert("Please refrain from using offensive words"); /* + alert_text */
        $('#otherComments').focus();
        return false;
    }

}

