/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(cargarDatos());

function getDataStorage(key) {
    return  localStorage.getItem(key);
}

function setDataStorage(key, value) {
    localStorage.setItem(key, value);
}

function cleanDataStorage(key) {
    localStorage.removeItem(key);
}

$('.bt-title').on('click', function () {
    window.location.href = '/catalogo';
})

$("#bt-filter").on("click", function () {
    if ($("#filter-popap").is(":hidden")) {
        $("#filter-popap").show();
    } else {
        $("#filter-popap").hide();
    }
});

$("#bt-filter-text").on("click", function () {
    var valTextFilter = $('#textFilter').val();
    if (valTextFilter.length > 0) {
        setDataStorage('textFilter', valTextFilter);
    } else {
        cleanDataStorage('textFilter');
    }
    cargarDatos();
})

$("#bt-filter-content").on("click", function () {
    var values = (function () {
        var a = [];
        $(".chk-acordes:checked").each(function () {
            console.log(this);
            a.push(this.id);
        });
        return a;
    })();
    if (values.length == 0) {
        cleanDataStorage('filterAcordes');
    } else {
        setDataStorage('filterAcordes', values);
    }
    cargarDatos();
    $("#filter-popap").hide();
});

$('#bt-clean-filter').on('click', function () {
    cleanDataStorage('textFilter');
    cleanDataStorage('filterAcordes');
    $('#textFilter').val('');
    $(".chk-acordes").each(function () {
        $(this).prop('checked', false);
    });
    cargarDatos();
    $("#filter-popap").hide();
})

function cargarDatos() {
    var urlT = './productoController?op=all';

    if (getDataStorage('textFilter') !== null || getDataStorage('filterAcordes') !== null) {
        $('#bt-clean-filter').show();

        var textFilter = getDataStorage('textFilter');
        if (textFilter) {
            $('#textFilter').val(textFilter);
        }

        var filterAcordes = getDataStorage('filterAcordes');
        if (filterAcordes) {
            var values = filterAcordes.split(',');
            $(".chk-acordes").each(function () {
                if (values.includes(this.id)) {
                    $(this).prop('checked', true);
                }
            });
        }
        urlT = './productoController?op=search&nombre=' + (textFilter ? textFilter : '') + '&acordes=' + (filterAcordes ? filterAcordes : '');
    } else {
        $('#bt-clean-filter').hide();
    }

    $("#idGallery").empty();

    $.ajax({
        type: 'GET',
        url: urlT,
    }).done(function (data) {
        data.forEach(function (valor, indice) {
            //console.log(valor);
            var html = `<div class="item">
                    <div class="item-content">
                        <img  class="item-img" src="${valor.imagen}" alt="green apple slice">
                        <div class="item-detail">
                            <div class="item-title">${valor.nombre}</div>
                            <spam class="item-code">COD: ${valor.idProducto}</spam>
                            <div class="descripcion">
                                ${valor.descripcion}
                            </div>
                        </div>
                        <div class="item-footer">
                                <div class="item-price"> 
                                    Gs. <span>${valor.precio}</span>    
                                </div>
                                <button class="view-btn"><a href="producto.jsp?id=${valor.idProducto}">Detalles</a></button>
                         </div>
                    </div>
                </div>`;
            $("#idGallery").append(html);
        });
    }).fail(function (jqXHR, textStatus, errorThrown) {
        //$("#consola").html("The following error occured: " + textStatus + " " + errorThrown);
    });
}