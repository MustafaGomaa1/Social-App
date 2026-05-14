// document.addEventListener('DOMContentLoaded', () => {
//     const togglePassword = document.querySelector('#togglePassword');
//     const password = document.querySelector('#password');
//     const loginForm = document.querySelector('#loginForm');

//     // Toggle Password Visibility
//     togglePassword.addEventListener('click', () => {
//         const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
//         password.setAttribute('type', type);
//         togglePassword.textContent = type === 'password' ? '👁️' : '🙈';
//     });

//     // Handle Form Submission
//     loginForm.addEventListener('submit', (e) => {
//         e.preventDefault();
//         const email = document.getElementById('email').value;

//         console.log('Attempting login with:', email);
//         alert('Logging in...');
//         // Here you would typically call your backend API (e.g., Spring Boot)
//     });
// });

// document.addEventListener('DOMContentLoaded', () => {
//     const registerForm = document.querySelector('#registerForm');
//     const togglePassword = document.querySelector('#togglePassword');
//     const passwordField = document.querySelector('#password');

//     // Password visibility toggle
//     // togglePassword.addEventListener('click', () => {
//     //     const isPassword = passwordField.type === 'password';
//     //     passwordField.type = isPassword ? 'text' : 'password';
//     //     togglePassword.textContent = isPassword ? '🙈' : '👁️';
//     // });

//     // Form Submission Logic
//     registerForm.addEventListener('submit', (e) => {
//         e.preventDefault();

//         const userData = {
//             username: document.getElementById('username').value,
//             email: document.getElementById('email').value,
//             password: passwordField.value
//         };

//         console.log('Registration Data:', userData);

//         // Example of where you'd connect to your API:
//         // fetch('/api/auth/register', {
//         //     method: 'POST',
//         //     headers: { 'Content-Type': 'application/json' },
//         //     body: JSON.stringify(userData)
//         // });

//         alert(`Welcome, ${userData.username}! Registration successful.`);
//     });
// });