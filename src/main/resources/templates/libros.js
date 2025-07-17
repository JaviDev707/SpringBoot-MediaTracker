document.addEventListener("DOMContentLoaded", function() {
    cargarLibros();
});
// ========================== Carga ============================
/**
 * Funcion que carga todos los libros en la tabla
 */
function cargarLibros() {
    fetch("http://localhost:8080/libro/todos")
        .then(response => response.json())
        .then(libros => cargarTabla(libros))
        .catch(error => console.error("Error al cargar libros:", error));
}
/**
 * Funcion que carga libros en la tabla
 */
function cargarTabla(libros){
    const tbody = document.getElementById("tabla-libros");
            tbody.innerHTML = ""; // Limpia la tabla

            libros.forEach(libro => {
                const fila = document.createElement("tr");
                fila.className = "align-middle";

                fila.innerHTML = `
                    <td>
                      <div class="d-flex align-items-center">
                        <img src="https://images.vexels.com/media/users/3/242555/isolated/preview/3236dda9ad7dc9905f870dae152ca56a-elementos-de-pegatinas-de-regreso-a-la-escuela-9.png" 
                             class="avatar sm rounded-pill me-3 flex-shrink-0" alt="Libro">
                        <div class="h6 mb-0 lh-1">${libro.id}</div>
                      </div>
                    </td>
                    <td>${libro.titulo}</td>
                    <td>${libro.autor}</td>
                    <td>${libro.genero}</td>
                    <td>${libro.año}</td>
                    <td>${libro.finalizacion}</td>
                    <td class="text-end">
                        <button class="btn btn-primary btn-sm" onclick="activarEdicion(this)(${libro.id})"><i class="bi bi-pencil"></i></button>
                        <button class="btn btn-danger btn-sm" onclick="borrarLibro(${libro.id})"><i class="bi bi-trash"></i></button>
                    </td>
                `;

                tbody.appendChild(fila);
            });
}
// ========================== Borrar ============================
/**
 * Funcion que borra un libro enviando su id y llamando a http://localhost:8080/libro/borrar/${id}
 */
function borrarLibro(id) {
    if (!confirm("¿Estás seguro de que quieres borrar este libro?")) {
        return;
    }

    fetch(`http://localhost:8080/libro/borrar/${id}`, {
        method: "DELETE"
    })
    .then(response => {
        if (response.status === 204) {
            alert("Libro eliminado correctamente.");
            cargarLibros();
        } else {
            alert("Error al borrar el libro.");
        }
    });
}
// ========================== Edicion ============================
/**
 * Funcion que activa el modo edición
 */
function activarEdicion(boton) {
    const fila = boton.closest("tr");
    const celdas = fila.querySelectorAll("td");

    // Guarda el ID en un atributo 
    const id = celdas[0].innerText;

    // Convierte las celdas en inputs (excepto el ID y los botones)
    for (let i = 1; i <= 5; i++) {
        const valor = celdas[i].innerText;
        celdas[i].innerHTML = `<input type="text" value="${valor}" class="form-control form-control-sm">`;
    }

    // Cambia el botón por Guardar y Cancelar
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

    const dto = {
        titulo: inputs[0].value,
        autor: inputs[1].value,
        genero: inputs[2].value,
        año: parseInt(inputs[3].value),
        finalizacion: inputs[4].value
    };

    fetch(`http://localhost:8080/libro/actualizar/${id}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dto)
    })
    .then(response => response.json())
    .then(data => {
        alert("Libro actualizado correctamente");
        cargarLibros();
    })
    .catch(error => console.error("Error al actualizar:", error));
}
/**
 * Boton de Cancelar, recarga la tabla para deshacer los cambios
 */
function cancelar() {
    cargarLibros(); 
}
// ========================== Registro ============================
/**
 * Funcion que activa el modo registro al principio de la tabla
 */
function activarRegistro() {
    const tbody = document.getElementById("tabla-libros");

    const fila = document.createElement("tr");
    fila.id = "fila-registro";

    fila.innerHTML = `
        <td>-</td>
        <td><input type="text" id="nuevoTitulo" placeholder="Título" class="form-control form-control-sm"></td>
        <td><input type="text" id="nuevoAutor" placeholder="Autor" class="form-control form-control-sm"></td>
        <td><input type="text" id="nuevoGenero" placeholder="Género" class="form-control form-control-sm"></td>
        <td><input type="number" id="nuevoAño" placeholder="Año" class="form-control form-control-sm"></td>
        <td><input type="date" id="nuevaFinalizacion" class="form-control form-control-sm"></td>
        <td class="text-end">
            <button class="btn btn-success btn-sm rounded-circle" onclick="guardarRegistro()"><i class="bi bi-check-circle"></i></button>
            <button class="btn btn-sm btn-danger rounded-circle" onclick="cancelar()"><i class="bi bi-x-circle"></i></button>
        </td>
    `;

    tbody.prepend(fila); // Añade la fila al principio del tbody
}
/**
 * Boton de guardar, envia los datos del registro
 */
function guardarRegistro() {
    const dto = {
        titulo: document.getElementById("nuevoTitulo").value,
        autor: document.getElementById("nuevoAutor").value,
        genero: document.getElementById("nuevoGenero").value,
        año: parseInt(document.getElementById("nuevoAño").value),
        finalizacion: document.getElementById("nuevaFinalizacion").value
    };

    fetch("http://localhost:8080/libro/registro", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dto)
    })
    .then(response => {
        if (response.ok) {
            alert("Libro registrado correctamente");
            cargarLibros();
        } else {
            alert("Error al registrar el libro");
        }
    })
    .catch(error => console.error("Error al registrar:", error));
}
// ========================== Filtros ============================
document.getElementById("btnFiltrar").addEventListener("click", function() {
    const tipo = document.getElementById("tipoFiltro").value;
    const valor = document.getElementById("valorFiltro").value.trim();

    if (valor === "") {
        cargarLibros(); // Mostrar todo si no escribe nada
        return;
    }
    
    switch(tipo) {
        case "titulo":
            filtrarPorTitulo(valor);
            break;
        case "autor":
            filtrarPorAutor(valor);
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
    fetch(`http://localhost:8080/libro/titulo/${titulo}`)
        .then(response => response.json())
        .then(libros => cargarTabla(libros))
        .catch(error => console.error("Error al cargar libros:", error));
}

function filtrarPorAutor(autor){
    fetch(`http://localhost:8080/libro/autor/${autor}`)
        .then(response => response.json())
        .then(libros => cargarTabla(libros))
        .catch(error => console.error("Error al cargar libros:", error));
}

function filtrarPorGenero(genero){
    fetch(`http://localhost:8080/libro/genero/${genero}`)
        .then(response => response.json())
        .then(libros => cargarTabla(libros))
        .catch(error => console.error("Error al cargar libros:", error));
}

function filtrarPorMes(valor){
    const partes = valor.split("-");

    if (partes.length !== 2) {
        alert("Formato inválido. Usa mes-año");
        return;
    }

    const mes = partes[0];
    const año = partes[1];

    fetch(`http://localhost:8080/libro/mes_año/${mes}/${año}`)
        .then(response => response.json())
        .then(libros => cargarTabla(libros))
        .catch(error => console.error("Error al cargar libros:", error));
}

function filtrarPorAño(año){
    fetch(`http://localhost:8080/libro/año/${año}`)
        .then(response => response.json())
        .then(libros => cargarTabla(libros))
        .catch(error => console.error("Error al cargar libros:", error));
}