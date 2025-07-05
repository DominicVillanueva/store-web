document.addEventListener('DOMContentLoaded', () => {
    actualizarContadorCarrito();

    const botones = document.querySelectorAll('.btn-agregar-carrito');
    const usuarioLogueado = document.body.getAttribute('data-usuario-logueado') === 'true';
    
    botones.forEach(boton => {
        boton.addEventListener('click', () => {
            
            if (!usuarioLogueado) {
                const modal = new bootstrap.Modal(document.getElementById('loginRequiredModal'));
                modal.show();
                return;
            }
            
            const id = boton.dataset.id;
            const nombre = boton.dataset.nombre;
            const precio = parseFloat(boton.dataset.precio);
            const imagen = boton.dataset.imagen;

            let carrito = JSON.parse(localStorage.getItem('carrito')) || [];

            // Verificar si ya existe
            const index = carrito.findIndex(p => p.id === id);
            if (index !== -1) {
                carrito[index].cantidad += 1;
            } else {
                carrito.push({ id, nombre, precio, imagen, cantidad: 1 });
            }

            localStorage.setItem('carrito', JSON.stringify(carrito));
            actualizarContadorCarrito();
        });
    });

    function actualizarContadorCarrito() {
        const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
        const totalItems = carrito.reduce((sum, item) => sum + item.cantidad, 0);
        document.getElementById('contador-carrito').textContent = totalItems;
    }
});