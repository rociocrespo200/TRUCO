
let eventosRegistrados = [];
var eventosRegistradosConRespuestas = [];
let eventosIniciales = ['TRUCO', 'ENVIDO', 'REAL_ENVIDO', 'FALTA_ENVIDO', 'IRSE_AL_MAZO'];
generarEventos(eventosIniciales);

let eventoEnProceso = false;
function registrarEvento(evento) {

    eventoEnProceso = true;
    administrarBotones(evento);
    generarMensaje(evento, "emisor")
    let eventoParaEnviar = null;

    if (evento == "QUIERO" || evento == "NO_QUIERO" || evento == "IRSE_AL_MAZO") {
        eventosRegistrados.push(evento)
        let nombreCompuestoDelEvento = obtenerUltimoEvento();

        eventoEnProceso = false;
        eventoParaEnviar = {
            tipo: "evento",
            obj: {
                usuario: sessionStorage.getItem('usuarioSession'),
                nombre: nombreCompuestoDelEvento,
                finalizado: true
            }
        };
        eventosRegistrados = [];
    } else {
        eventoParaEnviar = {
            tipo: "evento",
            obj: {
                usuario: sessionStorage.getItem('usuarioSession'),
                nombre: evento,
                finalizado: false
            }

        };
        eventosRegistrados.push(evento)
    }

    if(eventoEnProceso == false){
        //ocultame el div que bloquea la pantalla;
    }
    mandarEventoConAJAX(eventoParaEnviar);
}

function obtenerUltimoEvento() {
    let nombreCompuestoDelEvento = "";
    for (let i = 0; i < eventosRegistrados.length; i++) {
        if(i !== eventosRegistrados.length -1){
            nombreCompuestoDelEvento += eventosRegistrados[i] + " ";
        }else{
            nombreCompuestoDelEvento += eventosRegistrados[i];
        }
    }
    return nombreCompuestoDelEvento;
}

function administrarBotones(evento){
    let proximosEventos
    switch(evento){
        case "TRUCO": proximosEventos =['RETRUCO', 'QUIERO', 'NO_QUIERO', 'IRSE_AL_MAZO']; break;
        case "RETRUCO": proximosEventos =['VALE_CUATRO', 'QUIERO', 'NO_QUIERO', 'IRSE_AL_MAZO']; break;
        case "VALE_CUATRO": proximosEventos =['QUIERO', 'NO_QUIERO', 'IRSE_AL_MAZO']; break;
        case "ENVIDO": proximosEventos =['ENVIDO_ENVIDO', 'REAL_ENVIDO', 'FALTA_ENVIDO', 'QUIERO', 'NO_QUIERO', 'IRSE_AL_MAZO']; break;
        case "ENVIDO_ENVIDO": proximosEventos =['REAL_ENVIDO', 'FALTA_ENVIDO', 'QUIERO', 'NO_QUIERO', 'IRSE_AL_MAZO']; break;
        case "REAL_ENVIDO": proximosEventos =['FALTA_ENVIDO', 'QUIERO', 'NO_QUIERO', 'IRSE_AL_MAZO']; break;
        case "FALTA_ENVIDO": proximosEventos =['QUIERO', 'NO_QUIERO', 'IRSE_AL_MAZO']; break;
        default:
            if(eventosRegistrados[0] === "TRUCO") proximosEventos = ['ENVIDO', 'REAL_ENVIDO', 'FALTA_ENVIDO', 'IRSE_AL_MAZO'];
            else if(eventosRegistrados[0] === "IRSE_AL_MAZO")  proximosEventos = eventosIniciales;
            else proximosEventos = ['TRUCO', 'IRSE_AL_MAZO']
            break;
    }
    generarEventos(proximosEventos);
}

function generarEventos(eventos) {
    let formulario = document.getElementById('eventos');

    formulario.innerHTML = ``


    for (let i = 0; i < eventos.length; i++) {
        let nombre = decodificarEvento(eventos[i]);
        let button = document.createElement('button');
        button.className = 'boton_eventos option-button';
        button.id = eventos[i];
        button.setAttribute('onclick', `registrarEvento('${eventos[i]}')`);
        button.appendChild(document.createTextNode(nombre));
        formulario.appendChild(button);
    }
}

function decodificarEvento(evento){
    return evento.replace(/_/g, " ");
}

function mandarEventoConAJAX(evento){

    // Crear una solicitud POST utilizando AJAX - JUGADA
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/spring/partida"; // La URL a la que estás haciendo la solicitud POST
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.onreadystatechange = function() {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            let evento = this.responseText;

            stompClient.publish({
                destination: "/app/chat",
                body: JSON.stringify({message: evento, usuarioId: sessionStorage.getItem('usuarioSession')})
            });

            console.log(evento);
        }
    };

    var data = JSON.stringify(evento);
    xhr.send(data);
}


function generarMensaje(evento, id) {
    var divReceptor = document.getElementById(id);
    divReceptor.style.display = "block";
    divReceptor.textContent = 'Quiero '+ evento +' !!';

    setTimeout(function() {
        divReceptor.style.display = "none";
    }, 5000);

}


