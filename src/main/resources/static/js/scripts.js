document.addEventListener('DOMContentLoaded', function () {
    const navLinks = document.querySelectorAll('#mainNavbar .nav-link[href^="#"]');
    navLinks.forEach(link => {
        link.addEventListener('click', function (e) {
            e.preventDefault();
            let targetId = this.getAttribute('href');
            let targetElement = document.querySelector(targetId);

            if (targetElement) {
                let navbarHeight = document.querySelector('#mainNavbar').offsetHeight;
                let targetPosition = targetElement.offsetTop - navbarHeight;

                window.scrollTo({
                    top: targetPosition,
                    behavior: 'smooth'
                });

                const navbarToggler = document.querySelector('.navbar-toggler');
                const navbarCollapse = document.querySelector('.navbar-collapse');
                if (navbarToggler && navbarCollapse.classList.contains('show')) {
                    navbarToggler.click();
                }
            }
        });
    });

    const navbar = document.getElementById('mainNavbar');
    if (navbar) {
        window.addEventListener('scroll', function() {
            if (window.scrollY > 50) {
                navbar.classList.add('bg-white', 'shadow');
                navbar.classList.remove('bg-light');
            } else {
                navbar.classList.remove('bg-white', 'shadow');
                navbar.classList.add('bg-light');
            }
        });
    }
});

const modalEliminar = document.getElementById('modalEliminarProducto');
modalEliminar.addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget;
    const productoId = button.getAttribute('data-id');
    const productoNombre = button.getAttribute('data-nombre');

    // Actualiza el nombre en el texto del modal
    modalEliminar.querySelector('#productoNombre').textContent = productoNombre;

    // Actualiza la acción del formulario
    const form = modalEliminar.querySelector('#formEliminar');
    form.action = `/admin/eliminar/${productoId}`;
});

