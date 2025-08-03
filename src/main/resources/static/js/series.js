document.addEventListener("DOMContentLoaded", function() {
    cargarSeries();
});

// ========================== Carga ============================ 
/**
 * Funcion que carga todas los series en la tabla
 */
function cargarSeries() {
    fetch("http://localhost:8080/serie/todos")
        .then(response => response.json())
        .then(series => cargarTabla(series))
        .catch(error => console.error("Error al cargar series:", error));
}
/**
 * Funcion que carga series en la tabla
 */
function cargarTabla(series){
    const tbody = document.getElementById("tabla-series");
            tbody.innerHTML = ""; // Limpio la tabla

            series.forEach(serie => {
                const fila = document.createElement("tr");
                fila.className = "align-middle";

                fila.innerHTML = `
                    <td>
                      <div class="d-flex align-items-center">
                        <img src="https://cdn-icons-png.flaticon.com/512/6209/6209965.png" 
                             class="avatar sm rounded-pill me-3 flex-shrink-0" alt="serie">
                        <div class="h6 mb-0 lh-1">${serie.titulo}</div>
                      </div>
                    </td>
                    <td>${serie.genero}</td>
                    <td>${serie.plataforma}</td>
                    <td>${serie.año}</td>
                    <td>${serie.finalizacion}</td>
                    <td>
                    <div class="star-rating disabled">
                        <i class="bi bi-star-fill" data-value="1"></i>
                        <i class="bi bi-star-fill" data-value="2"></i>
                        <i class="bi bi-star-fill" data-value="3"></i>
                        <i class="bi bi-star-fill" data-value="4"></i>
                        <i class="bi bi-star-fill" data-value="5"></i>
                    </div>
                    </td>
                    <td class="text-end">
                        <button class="btn btn-primary btn-sm" onclick="activarEdicion(this, ${serie.id})"><i class="bi bi-pencil"></i></button>
                        <button class="btn btn-danger btn-sm" onclick="borrarSerie(${serie.id})"><i class="bi bi-trash"></i></button>
                    </td>
                `;

                tbody.appendChild(fila);
                activarRating(fila, serie.rating);
            });
}

// ========================== Estrellas ============================

function activarRating(contenedor, valorInicial) {
    const stars = contenedor.querySelectorAll('.star-rating i');
    const ratingDiv = contenedor.querySelector('.star-rating');
    
    ratingDiv.dataset.rating = valorInicial; 
    pintarEstrellas(stars, valorInicial); //Pinto las estrellas al cargar la tabla

    let valorSeleccionado = 0;

    stars.forEach(star => {
        star.addEventListener('mousemove', (e) => {
            if (ratingDiv.classList.contains('disabled')) return;
            const rect = star.getBoundingClientRect();
            const x = e.clientX - rect.left;
            const width = rect.width;

            const isHalf = x < width / 2;
            const currentValue = parseInt(star.dataset.value);
            pintarEstrellas(stars, currentValue - (isHalf ? 0.5 : 0));
        });

        star.addEventListener('click', (e) => {
            if (ratingDiv.classList.contains('disabled')) return;
            const rect = star.getBoundingClientRect();
            const x = e.clientX - rect.left;
            const width = rect.width;

            const isHalf = x < width / 2;
            valorSeleccionado = parseFloat(star.dataset.value) - (isHalf ? 0.5 : 0);

            ratingDiv.dataset.rating = valorSeleccionado; // Guardo el valor en un atributo de la estrella o del div contenedor

            pintarEstrellas(stars, valorSeleccionado);
        });
        
        if (ratingDiv.classList.contains('disabled')) return;
        star.addEventListener('mouseout', () => pintarEstrellas(stars, valorSeleccionado));
    });
}

function pintarEstrellas(stars, valor) {
    stars.forEach(star => {
        const starValue = parseInt(star.dataset.value);
        if (valor >= starValue) {
            star.className = 'bi bi-star-fill full';
        } else if (valor >= (starValue - 0.5)) {
            star.className = 'bi bi-star-fill half';
        } else {
            star.className = 'bi bi-star-fill';
        }
    });
}

// ========================== Registro ============================
/**
 * Funcion que activa el modo registro al principio de la tabla
 */
function activarRegistro() {
    const tbody = document.getElementById("tabla-series");

    const fila = document.createElement("tr");
    fila.id = "fila-registro";

    fila.innerHTML = `
        <td><input type="text" id="nuevoTitulo" placeholder="Título" class="form-control form-control-sm"></td>
        <td><input type="text" id="nuevoGenero" placeholder="Género" class="form-control form-control-sm"></td>
        <td><input type="text" id="nuevaPlataforma" placeholder="Plataforma" class="form-control form-control-sm"></td>
        <td><input type="number" id="nuevoAño" placeholder="Año" class="form-control form-control-sm"></td>
        <td><input type="date" id="nuevaFinalizacion" class="form-control form-control-sm"></td>
        <td>
            <div class="star-rating nuevoRating">
                            <i class="bi bi-star-fill" data-value="1"></i>
                            <i class="bi bi-star-fill" data-value="2"></i>
                            <i class="bi bi-star-fill" data-value="3"></i>
                            <i class="bi bi-star-fill" data-value="4"></i>
                            <i class="bi bi-star-fill" data-value="5"></i>
            </div>
        </td>
        <td class="text-end">
            <button class="btn btn-success btn-sm rounded-circle" onclick="guardarRegistro()"><i class="bi bi-check-circle"></i></button>
            <button class="btn btn-sm btn-danger rounded-circle" onclick="cancelar()"><i class="bi bi-x-circle"></i></button>
        </td>
    `;
    activarRating(fila);
    tbody.prepend(fila); // Añade la fila al principio del tbody
}
/**
 * Boton de guardar, envia los datos del registro
 */
function guardarRegistro() {

    const ratingDiv = document.querySelector(".nuevoRating");
    const rating = parseFloat(ratingDiv.dataset.rating) || 0;

    const dto = {
        titulo: document.getElementById("nuevoTitulo").value,
        genero: document.getElementById("nuevoGenero").value,
        plataforma: document.getElementById("nuevaPlataforma").value,
        año: parseInt(document.getElementById("nuevoAño").value),
        finalizacion: document.getElementById("nuevaFinalizacion").value,
        rating: rating
    };

    fetch("http://localhost:8080/serie/registro", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dto)
    })
    .then(response => {
        if (response.ok) {
            alert("Serie registrado correctamente");
            cargarSeries();
        } else {
            alert("Error al registrar la serie");
        }
    })
    .catch(error => console.error("Error al registrar:", error));
}

// ========================== Edicion ============================
/**
 * Funcion que activa el modo edición
 */
function activarEdicion(boton, id) {
    const fila = boton.closest("tr");
    const celdas = fila.querySelectorAll("td");

    // Valor actual del rating 
    const ratingDivOriginal = fila.querySelector('.star-rating');
    const valorInicial = parseFloat(ratingDivOriginal.dataset.rating) || 0;

    // Convierto las celdas en inputs 
    for (let i = 0; i <= 4; i++) {
        const valor = celdas[i].innerText.trim();

        if (i === 4) {
            celdas[i].innerHTML = `<input type="date" value="${valor}" class="form-control form-control-sm">`;
        } else {
            celdas[i].innerHTML = `<input type="text" value="${valor}" class="form-control form-control-sm">`;
        }
    }

    // Activo el control interactivo de estrellas
    const ratingDiv = fila.querySelector('.star-rating');
    ratingDiv.classList.remove('disabled'); //Quito el disabled para poder editar las estrellas
    activarRating(fila, valorInicial);

    // Cambio los botones por Guardar y Cancelar
    celdas[6].innerHTML = `
        <button class="btn btn-success btn-sm rounded-circle" onclick="guardarEdicion(this, ${id})"><i class="bi bi-check-circle"></i></button>
        <button class="btn btn-sm btn-danger rounded-circle" onclick="cancelar()"><i class="bi bi-x-circle"></i></button>
    `;
}
/**
 * Boton de guardar, envia los datos
 */
function guardarEdicion(boton, id) {
    const fila = boton.closest("tr");
    const inputs = fila.querySelectorAll("input");

    // Rating seleccionado
    const ratingDiv = fila.querySelector(".star-rating");
    const rating = parseFloat(ratingDiv.dataset.rating);

    const dto = {
        titulo: inputs[0].value,
        genero: inputs[1].value,
        plataforma: inputs[2].value,
        año: parseInt(inputs[3].value),
        finalizacion: inputs[4].value,
        rating: rating
    };

    fetch(`http://localhost:8080/serie/actualizar/${id}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dto)
    })
    .then(response => response.json())
    .then(data => {
        alert("Serie actualizada correctamente");
        cargarSeries();
    })
    .catch(error => console.error("Error al actualizar:", error));
}
/**
 * Boton de Cancelar, recarga la tabla para deshacer los cambios
 */
function cancelar() {
    cargarSeries(); 
}

// ========================== Borrar ============================
/**
 * Funcion que borra una serie enviando su id y llamando a http://localhost:8080/serie/borrar/${id}
 */
function borrarSerie(id) {
    if (!confirm("¿Estás seguro de que quieres borrar esta serie?")) {
        return;
    }

    fetch(`http://localhost:8080/serie/borrar/${id}`, {
        method: "DELETE"
    })
    .then(response => {
        if (response.status === 204) {
            alert("Serie eliminada correctamente.");
            cargarSeries();
        } else {
            alert("Error al borrar la serie.");
        }
    });
}

// ========================== Filtros ============================

document.getElementById("btnFiltrar").addEventListener("click", function() {
    const tipo = document.getElementById("tipoFiltro").value;
    const valor = document.getElementById("valorFiltro").value.trim();

    if (valor === "") {
        cargarSeries(); // Mostrar todo si no escribe nada
        return;
    }
    
    switch(tipo) {
        case "titulo":
            filtrarPorTitulo(valor);
            break;
        case "plataforma":
            filtrarPorPlataforma(valor);
            break;
        case "genero":
            filtrarPorGenero(valor);
            break;
        case "mes":
            filtrarPorMes(valor);
            break;
        case "año":
            filtrarPorAño(valor);
            break;
    }
});

function filtrarPorTitulo(titulo){
    fetch(`http://localhost:8080/serie/titulo/${titulo}`)
        .then(response => response.json())
        .then(series => cargarTabla(series))
        .catch(error => console.error("Error al cargar series:", error));
}

function filtrarPorPlataforma(plataforma){
    fetch(`http://localhost:8080/serie/plataforma/${plataforma}`)
        .then(response => response.json())
        .then(series => cargarTabla(series))
        .catch(error => console.error("Error al cargar series:", error));
}

function filtrarPorGenero(genero){
    fetch(`http://localhost:8080/serie/genero/${genero}`)
        .then(response => response.json())
        .then(series => cargarTabla(series))
        .catch(error => console.error("Error al cargar series:", error));
}

function filtrarPorMes(valor){
    const partes = valor.split("-");

    if (partes.length !== 2) {
        alert("Formato inválido. Usa mes-año");
        return;
    }

    const mes = partes[0];
    const año = partes[1];

    fetch(`http://localhost:8080/serie/mes_año/${mes}/${año}`)
        .then(response => response.json())
        .then(series => cargarTabla(series))
        .catch(error => console.error("Error al cargar series:", error));
}

function filtrarPorAño(año){
    fetch(`http://localhost:8080/serie/año/${año}`)
        .then(response => response.json())
        .then(series => cargarTabla(series))
        .catch(error => console.error("Error al cargar series:", error));
}