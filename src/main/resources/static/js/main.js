//헤더
fetch('/header')
    .then((response) => response.text())
    .then((data) => {
        document.getElementById('headerAreaId').innerHTML = data;
    })
    .catch((err) => console.error('헤더 불러오기 실패:', err));

//nav
fetch('/side')
    .then((response) => response.text())
    .then((data) => {
        document.getElementById('navAreaId').innerHTML = data;
    })
    .catch((err) => console.error('사이드바 불러오기 실패:', err));


// 카카오 지도 앱키 73f1566eba44aed31a32a520b4fa1a1f
window.onload = function () {
    // 지도 생성
    var mapContainer = document.getElementById('map');
    var mapOption = {
        center: new kakao.maps.LatLng(37.5665, 126.9780), // 서울 중심
        level: 3
    };

    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 마커 추가
    var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(37.5665, 126.9780)
    });

    marker.setMap(map);
};