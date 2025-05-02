<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Attraction API 테스트</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        select, button { margin: 5px; padding: 5px; }
        table { border-collapse: collapse; width: 100%; margin-top: 15px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background: #f4f4f4; }
    </style>
    <script>
    $(document).ready(function(){
        // 1) 초기 데이터 로드
        $.getJSON('${pageContext.request.contextPath}/api/v1/attraction/init', function(resp) {
            // 시도 드롭다운
            resp.sidos.forEach(function(s){
                $('#sido').append('<option value="'+s.code+'">'+s.name+'</option>');
            });
            // 콘텐츠 타입 드롭다운
            resp.contentTypes.forEach(function(ct){
                $('#contentType').append('<option value="'+ct.id+'">'+ct.name+'</option>');
            });
        });

        // 2) 시도 선택 시 구군 로드
        $('#sido').change(function(){
            var sidoCode = $(this).val();
            $('#gugun').empty().append('<option value="">-- 구군 선택 --</option>');
            if(!sidoCode) return;
            $.getJSON('${pageContext.request.contextPath}/api/v1/attraction/gugun/'+sidoCode, function(list){
                list.forEach(function(g){
                    $('#gugun').append('<option value="'+g.code+'">'+g.name+'</option>');
                });
            });
        });

        // 3) 검색 버튼 클릭
        $('#searchBtn').click(function(){
            var sidoCode    = $('#sido').val();
            var gugunCode   = $('#gugun').val();
            var contentType = $('#contentType').val();
            if(!sidoCode || !gugunCode || !contentType){
                alert('모든 항목을 선택하세요.');
                return;
            }
            $.getJSON('${pageContext.request.contextPath}/api/v1/attraction/search', {
                sidoCode: sidoCode,
                gugunCode: gugunCode,
                contentType: contentType
            }, function(attractions){
                var $tbody = $('#resultTable tbody').empty();
                if(attractions.length === 0){
                    $tbody.append('<tr><td colspan="4">조회된 관광지가 없습니다.</td></tr>');
                } else {
                    attractions.forEach(function(a){
                        $tbody.append(
                            '<tr>'
                          + '<td>'+a.title+'</td>'
                          + '<td>'+a.addr+'</td>'
                          + '<td>'+a.tel+'</td>'
                          + '<td>'+a.firstImage+'</td>'
                          + '</tr>'
                        );
                    });
                }
            });
        });
    });
    </script>
</head>
<body>
    <h2>관광지 검색 테스트</h2>
    <div>
        <label>시도: 
            <select id="sido">
                <option value="">-- 시도 선택 --</option>
            </select>
        </label>
        <label>구군: 
            <select id="gugun">
                <option value="">-- 구군 선택 --</option>
            </select>
        </label>
        <label>콘텐츠 타입: 
            <select id="contentType">
                <option value="">-- 타입 선택 --</option>
            </select>
        </label>
        <button id="searchBtn">검색</button>
    </div>

    <table id="resultTable">
        <thead>
            <tr>
                <th>제목</th>
                <th>주소</th>
                <th>전화번호</th>
                <th>이미지 URL</th>
            </tr>
        </thead>
        <tbody>
            <tr><td colspan="4">검색 버튼을 눌러주세요.</td></tr>
        </tbody>
    </table>
</body>
</html>
