$(document).ready(function () {
        var socket = new WebSocket("ws://localhost:8908/react");
        socket.onopen = function (event) {
            var newMessage = document.createElement('p');
            newMessage.textContent = "--- CONVERSATION READY";
            document.getElementById('conversations').appendChild(newMessage);
            socket.onmessage = function (e) {
                var newMessage = document.createElement('p');
                newMessage.textContent = "--- PORTAL: " + e.data;
                document.getElementById('conversations').appendChild(newMessage);
            }
            $("#send").click(function (e) {
                e.preventDefault();
                var message = $("#name").val();
                socket.send(message);
                var newMessage = document.createElement('p');
                newMessage.textContent = "--- GUEST: " + message;
                document.getElementById('conversations').appendChild(newMessage);
            });
        }
    });