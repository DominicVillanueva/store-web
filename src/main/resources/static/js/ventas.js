document.querySelector('.btn.btn-primary').addEventListener('click', () => {
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    if (carrito.length === 0) {
        mostrarModalMensaje("El carrito está vacío.", "Atención");
        return;
    }

    let total = parseFloat(carrito.reduce((sum, p) => sum + (p.precio * p.cantidad), 0).toFixed(2));
    const token = document.querySelector('meta[name="_csrf"]').content;
    const header = document.querySelector('meta[name="_csrf_header"]').content;

    fetch('/ventas/procesar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            [header]: token
        },
        body: JSON.stringify({ productos: carrito, total })
    }).then(async (res) => {
        const msg = await res.text();
        if (!res.ok) throw new Error(msg);
        return msg;
    }).then(() => {
        localStorage.removeItem('carrito');
        mostrarModalMensaje("Compra realizada con éxito", "Éxito");
        setTimeout(() => {
            window.location.href = "/catalogo";
        }, 2000);
    }).catch((err) => {
        mostrarModalMensaje(err.message, "Error");
    });
});


function mostrarModalMensaje(mensaje, titulo = "Mensaje") {
    const modalLabel = document.getElementById("modalMensajeLabel");
    const modalBody = document.getElementById("modalMensajeBody");

    modalLabel.textContent = titulo;
    modalBody.textContent = mensaje;

    const modal = new bootstrap.Modal(document.getElementById("modalMensaje"));
    modal.show();
}
