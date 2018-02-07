<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/starRating.css" type="text/css" rel="stylesheet">
<link href="css/index.css" type="text/css" rel="stylesheet">
<script src="javascript/formScripts.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>


        <title>UWE Feedback</title>
    <h1> Student Feedback Survery Short</h1>


</head>
<body>
    <form>
        <p>
            <b>Module: </b><br>
            <select name="module">
                <option>Example Module</option>
                </select>
            </p>
            <p>
                <b>Lecturer:</b><br>
                <select name="lecturer">
                    <option value="2">Tom Jones</option>
                </select>
            </p>
            <p>
                <b>Was the lecturer on time?</b><br>
                <label style="margin-right: 20px">Yes</label> No <br>
                <input type="radio" name="onTime" value="Yes" checked onclick="elementOff('late')">
                <input type="radio" name="onTime" value="No" onclick="elementOn('late')">

            <div id="late" hidden="true">
                Lecturer was <span id="lateSliderStatus">5</span> minutes late<br>
                <input type="range" min="5" max="60" value="5" step="5" oninput="lateSliderChange(this.value)">

            </div>
        </p>

        <p>
            <b>Was the lecture engaging?</b> <br>
            <label style="margin-right: 20px">Yes</label> No <br>
            <input type="radio" name="engaging" value="Yes" checked>
            <input type="radio" name="engaging" value="No">
        </p>
        
        <p>
            <b>Were the lecture's materials well prepared?</b> <br>
            <label style="margin-right: 20px">Yes</label> No <br>
            <input type="radio" name="prepared" value="Yes" checked>
            <input type="radio" name="prepared" value="No">
        </p
        
        <p>
            <b>Did the lecturer add value to their material?</b> <br>
            <label style="margin-right: 20px">Yes</label> No <br>
            <input type="radio" name="value" value="Yes" checked>
            <input type="radio" name="value" value="No">
        </p>

        <p>
            <b>How would you rate the lecture as a whole?</b>
        <div class="stars">
            <input class="star star-5" id="star-5" type="radio" name="star"/>
            <label class="star star-5" for="star-5"></label>
            <input class="star star-4" id="star-4" type="radio" name="star"/>
            <label class="star star-4" for="star-4"></label>
            <input class="star star-3" id="star-3" type="radio" name="star"/>
            <label class="star star-3" for="star-3"></label>
            <input class="star star-2" id="star-2" type="radio" name="star"/>
            <label class="star star-2" for="star-2"></label>
            <input class="star star-1" id="star-1" type="radio" name="star"/>
            <label class="star star-1" for="star-1"></label>
        </div>
    </p>

    <p>
        <b>How would you rate the lecturer's performance as a whole?</b>
    <div class="stars">
        <input class="star istar-5" id="istar-5" type="radio" name="star1"/>
        <label class="star istar-5" for="istar-5"></label>
        <input class="star istar-4" id="istar-4" type="radio" name="star1"/>
        <label class="star istar-4" for="istar-4"></label>
        <input class="star istar-3" id="istar-3" type="radio" name="star1"/>
        <label class="star istar-3" for="istar-3"></label>
        <input class="star istar-2" id="istar-2" type="radio" name="star1"/>
        <label class="star istar-2" for="istar-2"></label>
        <input class="star istar-1" id="istar-1" type="radio" name="star1"/>
        <label class="star istar-1" for="istar-1"></label>
    </div>
</p>
<p>
    <b>Please enter any other notable comments</b><br><br>
    <input type="text" name="otherComments">
</p>


<input type="submit"/>
</form>

</body>
</html>
