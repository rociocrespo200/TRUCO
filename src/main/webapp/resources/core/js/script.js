const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/spring/partidaBrocker'
});




stompClient.debug = function(str) {
    console.log(str)
};

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/messages', (m) => {


        if(m.body == null){
            console.log("No es tu turno")
        }else{
            var mensajeEnviado = JSON.parse(m.body)
            let imageUrl = JSON.parse(m.body).content;
            if(imageUrl === "RELOAD"){
                mostrarPopUp("popup3");
                setTimeout(function() {
                    ocultarPopUp("popup3")
                    location.reload();
                }, 3000);
            }else if(imageUrl.includes("png")){
                console.log("----------------Contenido --------------")
                console.log(mensajeEnviado);
                console.log("Usuario que envio = " + mensajeEnviado.idUsuario);

                const usuarioGuardado = sessionStorage.getItem('usuarioSession');


                // Convierte la cadena JSON de vuelta a un objeto.
                const usuario = JSON.parse(usuarioGuardado);

                console.log('Usuario actual:', usuario);

                if(usuario !== mensajeEnviado.idUsuario){
                    // Agrega la imagen al div deseado
                    const tirada_del_jugador = document.getElementById("jugada_oponente");
                    tirada_del_jugador.setAttribute("src", imageUrl);
                }
            }else{
                const usuarioGuardado = sessionStorage.getItem('usuarioSession');
                const usuario = JSON.parse(usuarioGuardado);
                if(usuario !== mensajeEnviado.idUsuario){
                    console.log("---------------- EVENTOS --------------")
                    console.log(imageUrl);
                    console.log("Usuario que envio = " + mensajeEnviado.idUsuario);

                    generarMensaje(decodificarEvento(imageUrl), "receptor");
                }
            }


        }

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


function mostrarPopUp(id) {
    var popup = document.getElementById(id); // Reemplaza "container" con el id del contenedor deseado
    popup.style.display = "block";
}

function ocultarPopUp(id) {
    var popup = document.getElementById(id); // Reemplaza "container" con el id del contenedor deseado
    popup.style.display = "none";
}

function moveImage(contenedor, imageUrl, idUsuario, imageNumber) {
    sessionStorage.setItem('usuarioSession', JSON.stringify(idUsuario));


        var contenedor = document.getElementById(contenedor);
        var jugadaMia = document.getElementById("jugada_mia");


        let jugada = {
            tipo: "jugada",
            obj: {
                carta: imageNumber,
                jugador: idUsuario
            }
        };


        // Crear una solicitud POST utilizando AJAX - JUGADA
        var xhr = new XMLHttpRequest();
        var url = "http://localhost:8080/spring/partida"; // La URL a la que estás haciendo la solicitud POST
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

        var data = JSON.stringify(jugada);

        xhr.onload = function () {
            console.log("XHR status:", xhr.status);
            console.log("Response:", xhr.responseText); // Verifica la respuesta exacta del servidor

            if (xhr.status === 200) {
                try {

                    if(xhr.responseText === ""){
                        console.log("No es tu turno");
                    }else{
                        jugadaMia.setAttribute("src", imageUrl);


                        stompClient.publish({
                            destination: "/app/chat",
                            body: JSON.stringify({message: imageUrl, usuarioId: idUsuario})
                        });

                        contenedor.style.display = "none";

                        var response = JSON.parse(xhr.responseText);
                        console.log("Respuesta:", response);
                        console.log(typeof response);
                    }
                // console.log("AJAX recibio la jugada")
                //     console.log(xhr.responseText)
                //     console.log(typeof xhr.responseText)
                    // Realiza acciones adicionales según la respuesta recibida
                } catch (e) {
                    console.log(typeof xhr.responseText);
                    if(xhr.responseText.includes("<!DOCTYPE HTML>")){
                        stompClient.publish({
                            destination: "/app/chat",
                            body: JSON.stringify({message: "RELOAD", usuarioId: idUsuario})
                        });
                    }
                    console.error("No se pudo analizar la respuesta como JSON. Error:" + e);
                }
            } else {
                console.error("Hubo un problema al enviar la jugada. Estado de la respuesta:", xhr.status);
            }
        };

        xhr.onerror = function () {
            console.error("Error de red al enviar la solicitud.");
        };

        xhr.send(data);

}

//EVENTOS
