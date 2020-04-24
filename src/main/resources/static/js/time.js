function getTime() {
    // Set the date we're counting down to
    var countDownDate = new Date("Jan 5, 2020 00:00:00").getTime();
    // Update the count down every 1 second
    var x = setInterval(function() {

        // Get todays date and time
        var now = new Date().getTime();

        // Find the distance between now an the count down date
        var distance =  now - countDownDate;
        // Time calculations for days, hours, minutes and seconds
        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        // Display the result in an element with id="demo"
        // document.getElementById("demo").innerHTML = days + "Days " + hours + "Hours "
        // + minutes + "Minutes " + seconds + "Seconds ";

        // Display the result in an element with id="demo"
        document.getElementById("days").innerHTML = days +" <small>天</small>";
        document.getElementById("hours").innerHTML = hours + " <small>时</small> ";
        document.getElementById("minutes").innerHTML = minutes + " <small>分</small> ";
        document.getElementById("seconds").innerHTML = seconds + " <small>秒</small> ";

    }, 1000);
}

$(function () {
    getTime();
})