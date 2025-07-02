//헤더
fetch('/header')
    .then((response) => response.text())
    .then((data) => {
        document.getElementById('headerAreaId').innerHTML = data;
    })
    .catch((err) => console.error('헤더 불러오기 실패:', err));

//모두 동의
document.addEventListener('DOMContentLoaded', () => {
    const allAgreeCheckbox = document.getElementById('allAgree');
    const agreeCheckboxes = document.querySelectorAll('.agreeChk');

    allAgreeCheckbox.addEventListener('change', () => {
        agreeCheckboxes.forEach(chk => {
            chk.checked = allAgreeCheckbox.checked;
        });
    });

    // 하위 체크박스 중 하나라도 체크 해제하면 전체 체크박스 해제
    agreeCheckboxes.forEach(chk => {
        chk.addEventListener('change', () => {
            if (!chk.checked) {
                allAgreeCheckbox.checked = false;
            } else {
                // 모두 체크되었을 때만 전체 체크박스 체크
                const allChecked = Array.from(agreeCheckboxes).every(c => c.checked);
                allAgreeCheckbox.checked = allChecked;
            }
        });
    });
});