'use strict';

let valueInput;
let serveurEtat;
let regex = /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/gm;

window.onload = () => {
    // Tested with Tyrus 1.15 WebSockets Java library
    let service = new WebSocket("ws://localhost:1963/arnaudetraph/WebSockets_illustration");
    service.onmessage = (event) => {
        console.log("Message from Java: " + event.data);
        var input = document.getElementById("inputId");
        var dns = document.getElementById('dns');
        dns.innerHTML += event.data + "<br />";
        input.addEventListener("change", function () {
            dns.innerHTML = "";
        })
    }

    service.onopen = () => {
        serveurEtat = 1;
        console.log("service.onopen...");
        var input = document.getElementById("inputId");
        input.addEventListener("keyup", function (ele) {
            if (ele.keyCode == 13) {
                if (input.value.length != 0) {
                    const found = input.value.match(regex);
                    if (found == null) {
                        Swal.fire(
                            'Error: Invalid URL',
                            'Please type valid URL',
                            'error')
                    } else {
                        valueInput = input.value.replace('www.', '');;
                        var parser = document.createElement('a');
                        parser.href = valueInput;
                        console.log("")
                        if (parser.hostname.length != 0) {
                            service.send(JSON.stringify(parser.hostname));
                        } else {
                            service.send(JSON.stringify(valueInput));
                        }
                    }
                } else {
                    console.log("Error, empty input");
                }
            }
        })
    }

    service.onclose = (event /*:CloseEvent*/ ) => {
        serveurEtat = 0;
        console.log("service.onclose... " + event.code);
        Swal.fire(
            'Error: server disconnected',
            'Please reload websocket server',
            'error'
        )
        // '1011': the server is terminating the connection because it encountered an unexpected condition that prevented it from fulfilling the request.
    };
    service.onerror = () => {
        Swal.fire(
            'Error: server disconnected',
            'Please reload websocket server',
            'error'
        )
    };
};

/* Vérifie si l'input est vide et si le serveur fonctionne */
function checkIfEmpty(ele) {
    var input2 = document.getElementById("inputId");
    if (ele.keyCode == 13) {
        var dns = document.getElementById('dns');
        dns.innerHTML = "";
        if (serveurEtat == 0) {
            Swal.fire(
                'Error: server disconnected',
                'Please reload websocket server',
                'error'
            )
        } else if (input2.value.length == 0 && serveurEtat == 1) {
            Swal.fire(
                'Error: empty input',
                'Please enter a domain name',
                'error'
            )
        }
    }
}