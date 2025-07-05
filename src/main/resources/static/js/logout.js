document.addEventListener('DOMContentLoaded', () => {
  const logoutBtn = document.querySelector('#logoutForm button');
  if (logoutBtn) {
    logoutBtn.addEventListener('click', (event) => {
      event.preventDefault();
      localStorage.removeItem('carrito');
      const contador = document.getElementById('contador-carrito');
      if (contador) contador.textContent = '0';
      document.getElementById('logoutForm').submit();
    });
  }
});
