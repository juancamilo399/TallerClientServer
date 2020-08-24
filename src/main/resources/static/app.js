var buttonimg = document.getElementById('btn1');
console.log(buttonimg);
buttonimg.addEventListener('click', function () {
    $('#myImg').toggle('slow');
});
var button = document.getElementById('btn2');
console.log(button);

button.addEventListener('click', function () {
    alert("Hiii!");
});