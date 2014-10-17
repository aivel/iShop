<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 10/12/2014
  Time: 12:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="root.i18n.lang" var="lang"/>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="<c:url value="resources/css/style.css"/>" rel="stylesheet">
  <script src="<c:url value="resources/js/ajax.js" />"></script>
  <script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
  <script type="text/javascript">
    ymaps.ready(init);
    var theMap,
        shop1,
        shop2;
    var courier = false;

    function init(){
      theMap = new ymaps.Map("map", {
        center: [59.971613, 30.320293],
        zoom: 17
      });

      shop1 = new ymaps.Placemark([59.971613, 30.320293], {
        iconContent: 'Магазин #1',
        hintContent: 'Главный офис',
        balloonContent: 'Офис продаж № 1'
      }, {
        // Опции.
        // Иконка метки будет растягиваться под размер ее содержимого.
        preset: 'islands#blueStretchyIcon',
        // Метку можно перемещать.
        draggable: false
      });

      shop2 = new ymaps.Placemark([59.97134, 30.318949], {
        iconContent: 'Магазин #2',
        hintContent: 'Второй офис',
        balloonContent: 'Офис продаж № 2',
        address: 'my address'
      }, {
        // Опции.
        // Иконка метки будет растягиваться под размер ее содержимого.
        preset: 'islands#blueStretchyIcon',
        // Метку можно перемещать.
        draggable: false
      });

      theMap.geoObjects.add(shop1);
      theMap.geoObjects.add(shop2);

      shop1.events.add('click', function(e) {
        document.getElementById("deliverySelf").innerHTML = 'Адрес магазина: ул. Профессора Попова д. 5 к. 1';
      });

      shop2.events.add('click', function(e) {
        document.getElementById("deliverySelf").innerHTML = 'Адрес магазина: ул. Профессора Попова д. 8';
      });
    }

    function textChanged(value) {
      ymaps.geocode(value, {
        /**
         * Опции запроса
         * @see http://api.yandex.ru/maps/doc/jsapi/2.1/ref/reference/geocode.xml
         */
        // boundedBy: myMap.getBounds(), // Сортировка результатов от центра окна карты
        // strictBounds: true, // Вместе с опцией boundedBy будет искать строго внутри области, указанной в boundedBy
        results: 1 // Если нужен только один результат, экономим трафик пользователей
      }).then(function (res) {
        // Выбираем первый результат геокодирования.
        var firstGeoObject = res.geoObjects.get(0),
        // Координаты геообъекта.
                coords = firstGeoObject.geometry.getCoordinates(),
        // Область видимости геообъекта.
                bounds = firstGeoObject.properties.get('boundedBy');

        // Добавляем первый найденный геообъект на карту.
        theMap.geoObjects.add(firstGeoObject);
        // Масштабируем карту на область видимости геообъекта.
        theMap.setBounds(bounds, {
          checkZoomRange: true // проверяем наличие тайлов на данном масштабе.
        });
      });
    }

    function radioOnclick(index) {
      switch (index) {
        case 0: // courier
          document.getElementById("deliveryByCourier").classList.remove("hidden");
          document.getElementById("deliverySelf").classList.add("hidden");
          courier = true;
          break;
        case 1: // self
          document.getElementById("deliveryByCourier").classList.add("hidden");
          document.getElementById("deliverySelf").classList.remove("hidden");
          courier = false;
          break;
      }
    }
  </script>
  <title>Bookstore - Order</title>
</head>
<body>
<%@include file="parts/commonHeader.jsp"%>
<main class="container">
  <div class="floating-block-left" id="map" style="width: 400px; height: 400px">
  </div>
  <div class="margin-left-5 floating-block-left">
    <div>
      Способ доставки:
      <div id="deliveryRadio">
        <label><input type="radio" name="delivery" checked="checked" onclick="radioOnclick(0)"/> Курьером</label>
        <label><input type="radio" name="delivery" onclick="radioOnclick(1)"/> Самовывоз</label>
      </div>
    </div>
    <br/>
    <div id="deliveryByCourier" class="margin-top-5">
      <label for="addr">Адрес доставки: </label>
      <br/>
      <input id="addr" type="text" onchange="textChanged(this.value);"/>
    </div>
    <br/>
    <div id="deliverySelf" class="margin-top-5 hidden">
      Выберите магазин на карте
    </div>
    <br/>
    <div>
      <a class="button" href="javascript: makeOrder(courier, document.getElementById(courier ? 'addr' : 'deliverySelf').innerHTML);">
        <fmt:message key="ORDER" bundle="${lang}"/>
      </a>
    </div>
  </div>
</main>
<%@include file="parts/commonFooter.jsp"%>
</body>
</html>