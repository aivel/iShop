var _ = {};
_.getXmlHttp = function(){
    var xmlhttp;
    try {
      xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
      try {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (E) {
        xmlhttp = false;
      }
    }
    if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
      xmlhttp = new XMLHttpRequest();
    }
    return xmlhttp;
};
_.xmlhttp = _.getXmlHttp();
_.ajaxQuery = function (method, path, onReadyCallback, params) {
    if (_.xmlhttp === null)
        _.ajaxInit();
    if (_.xmlhttp === null)
        return;

    _.xmlhttp.open(method, path, true);
    _.xmlhttp.onreadystatechange = onReadyCallback;
    _.xmlhttp.send(params);
};
_.ajaxGet = function (path, onReadyCallback) {
    _.ajaxQuery('GET', path, onReadyCallback, null);
};
_.ajaxPost = function (path, onReadyCallback, params) {
    _.ajaxQuery('POST', path, onReadyCallback, params)
};
_.getQueryParams = function  () {
  function identity (e) { return e; }
  function toKeyValue (params, param) {
    var keyValue = param.split('=');
    var key = keyValue[0], value = keyValue[1];

    params[key] = params[key]?[value].concat(params[key]):value;
    return params;
  }
  return decodeURIComponent(window.location.search).
    replace(/^\?/, '').split('&').
    filter(identity).
    reduce(toKeyValue, {});
};
function setContent(type) {
    var queryParams = _.getQueryParams();
    var id = queryParams.id;
    _.ajaxGet('/ajax?req=' + type + '&id=' + id, function () {
        if (_.xmlhttp.readyState !== 4) return;
        if (_.xmlhttp.status === 200)
            document.getElementById("content").innerHTML = _.xmlhttp.responseText;
    });
}
function setLocale(type) {
    _.ajaxGet('/ajax?req=locale&type=' + type, function () {
        if (_.xmlhttp.readyState !== 4) return;
        if (_.xmlhttp.status === 200)
            window.location.reload();
    });
}
function filterProducts(by) {
    _.ajaxGet('/ajax?req=filter&by=' + by, function () {
        if (_.xmlhttp.readyState !== 4) return;
        if (_.xmlhttp.status === 200)
            document.getElementById("productsList").innerHTML = _.xmlhttp.responseText;
    });
}
function addToCart(amount, productId) {
    var queryParams = _.getQueryParams();
    var queryId = this.id = queryParams.id;

    _.ajaxGet('/ajax?req=order&id=' + (typeof productId == "undefined" ? queryId : productId)
                + '&amount=' + amount, function () {
        if (_.xmlhttp.readyState !== 4) return;
        if (_.xmlhttp.status === 200)
                ; // everything is ok
    });
}