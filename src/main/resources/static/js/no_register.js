// scripts.js
document.getElementById('signup-form').addEventListener('submit', function(event) {
    event.preventDefault();

    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirm-password').value;

    var emailError = document.getElementById('email-error');
    var passwordError = document.getElementById('password-error');

    emailError.style.display = 'none';
    passwordError.style.display = 'none';

    // 이메일 유효성 검사
    var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailPattern.test(email)) {
        emailError.textContent = '유효한 이메일 주소를 입력하세요.';
        emailError.style.display = 'block';
        return;
    }

    // 비밀번호 확인 검사
    if (password !== confirmPassword) {
        passwordError.textContent = '비밀번호가 일치하지 않습니다.';
        passwordError.style.display = 'block';
        return;
    }

    // 약관 동의 확인
    if (!terms) {
        termsError.textContent = '필수 약관에 동의해야 합니다.';
        termsError.style.display = 'block';
        return;
    }

    alert('회원가입이 완료되었습니다!');
    // 실제로는 여기서 서버로 데이터를 보내는 코드를 추가합니다.
});