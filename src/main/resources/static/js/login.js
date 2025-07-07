//헤더
fetch('/header')
    .then((response) => response.text())
    .then((data) => {
        document.getElementById('headerAreaId').innerHTML = data;
    })
    .catch((err) => console.error('헤더 불러오기 실패:', err));