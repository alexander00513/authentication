var stompClient = null;

$(document).ready(function () {
    connect();
});

function connect() {
    var socket = new SockJS("/authenticate");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("Connected: " + frame);
        stompClient.subscribe("/user/topic/authenticate", function (authentication) {
            showLog(authentication.body);
        });
    });
}

function reconnect() {
    if (!stompClient.connected) {
        console.log("Reconnect");
        connect();
        return true;
    }
}

$(window).unload(function () {
    disconnect();
});

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function authenticate() {
    var reconnecting = reconnect();

    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var LoginRequest = {
        type: "LOGIN_CUSTOMER",
        sequence_id: UUIDjs.create().hex,
        data: {
            email: email,
            password: password
        }
    };
    setTimeout(function () {
        stompClient.send("/app/authenticate", {}, JSON.stringify(LoginRequest));
    }, reconnecting ? 2000 : 0);
}

function showLog(message) {
    var authentication = JSON.parse(message);
    var messageType = authentication.type;
    message = JSON.stringify(authentication, null, 2);

    var pre = document.createElement("pre");
    pre.appendChild(document.createTextNode(message));

    var color = messageType == "CUSTOMER_API_TOKEN" ? "green" : "red";
    pre.setAttribute("style", "color:" + color);

    $("#log").prepend(pre);
}