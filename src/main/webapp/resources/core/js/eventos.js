
let eventosRegistrados = [];
var eventosRegistradosConRespuestas = [];
let eventosIniciales = ['TRUCO', 'ENVIDO', 'REAL_ENVIDO', 'FALTA_ENVIDO', 'IRSE_AL_MAZO'];
generarEventos(eventosIniciales);

function registrarEvento(evento) {

    administrarBotones(evento);


    if (evento == "QUIERO" || evento == "NO_QUIERO" || evento == "IRSE_AL_MAZO") {
        let nombreCompuestoDelEvento = obtenerUltimoEvento();
        let respuesta = (evento == "QUIERO") ? true : false;


        let eventoParaEnviar = {
            tipo: "evento",
            obj: {
                usuario: sessionStorage.getItem('usuarioSession'),
                nombre: nombreCompuestoDelEvento,
                quiero: respuesta
            }

        };
        mandarEventoCompletoConAJAX(eventoParaEnviar);
        eventosRegistrados = [];
    } else {
        eventosRegistrados.push(evento)
    }
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

function mandarEventoCompletoConAJAX(evento){

    // Crear una solicitud POST utilizando AJAX - JUGADA
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/spring/partida"; // La URL a la que estás haciendo la solicitud POST
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.onreadystatechange = function() {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            // La solicitud ha sido completada y la respuesta del servidor está lista
            //var respuesta = JSON.parse(this.responseText);
            // Manejar la respuesta aquí
            console.log(this.responseText);
        }
    };

    var data = JSON.stringify(evento);
    xhr.send(data);
}




