const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/spring/partidaBrocker'
});




stompClient.debug = function(str) {
    console.log(str)
};

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/messages', (m) => {
        window.location.href = "/spring/partida"

    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

stompClient.activate();

function iniciarPartida() {
    stompClient.publish({
        destination: "/app/inicarPartidaWebSocket",
        body: JSON.stringify(true)
    });

}
