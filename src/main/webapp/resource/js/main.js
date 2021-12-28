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

function scrollTop(){
    $('html, body').animate({scrollTop:0}, 'slow');
}

$("#bt-filter").on("click", function () {
    if ($("#filter-popap").hasClass("animate__slideOutLeft")) {
        $("#filter-popap").removeClass('animate__animated animate__slideOutLeft hidden');
        $("#filter-popap").addClass('animate__animated animate__fadeInLeft show');
    } else {
        $("#filter-popap").removeClass('animate__animated animate__fadeInLeft show');
        $("#filter-popap").addClass('animate__animated animate__slideOutLeft');
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

//Filter Acordes
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

//Filter Sexo
    var valuesSexo = (function () {
        var a = [];
        $(".chk-sexo:checked").each(function () {
            console.log(this);
            a.push(this.id);
        });
        return a;
    })();
    if (valuesSexo.length == 0) {
        cleanDataStorage('filterSexo');
    } else {
        setDataStorage('filterSexo', valuesSexo);
    }

    cargarDatos();
    $("#filter-popap").removeClass('animate__animated animate__fadeInLeft show');
    $("#filter-popap").addClass('animate__animated animate__slideOutLeft');
});
$('#bt-clean-filter').on('click', function () {
    cleanDataStorage('textFilter');
    cleanDataStorage('filterAcordes');
    cleanDataStorage('filterSexo');
    $('#textFilter').val('');
    $(".chk-acordes").each(function () {
        $(this).prop('checked', false);
    });
    $(".chk-sexo").each(function () {
        $(this).prop('checked', false);
    });
    cargarDatos();
    $("#filter-popap").removeClass('animate__animated animate__fadeInLeft show');
    $("#filter-popap").addClass('animate__animated animate__slideOutLeft');
})

function cargarDatos() {
    var urlT = './productoController?op=all';
    if (getDataStorage('textFilter') !== null || getDataStorage('filterAcordes') !== null || getDataStorage('filterSexo') !== null) {
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

        var filterSexo = getDataStorage('filterSexo');
        if (filterSexo) {
            var values = filterSexo.split(',');
            $(".chk-sexo").each(function () {
                if (values.includes(this.id)) {
                    $(this).prop('checked', true);
                }
            });
        }

        urlT = './productoController?op=search&nombre=' + (textFilter ? textFilter : '') + '&acordes=' + (filterAcordes ? filterAcordes : '') + '&sexo=' + (filterSexo ? filterSexo : '');
    } else {
        $('#bt-clean-filter').hide();
    }

    $("#idGallery").empty();
    $.ajax({
        type: 'GET',
        url: urlT,
    }).done(function (data) {
        if (data.length > 0) {
            data.forEach(function (valor, indice) {
                //console.log(valor);
                var html = `<div class="item animate__animated animate__fadeIn">
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
                                <div class="item-price not-visible"> 
                                    Gs. <span>${valor.precio}</span>    
                                </div>
                                <button class="view-btn"><a href="producto.jsp?id=${valor.idProducto}">Detalles</a></button>
                         </div>
                    </div>
                </div>`;
                $("#idGallery").append(html);
            });
        } else {
            var html = `<div class="no_item animate__animated animate__fadeIn">
                       <p>Sin Registros obtenidos en la busqueda.</p>
                </div>`;
            $("#idGallery").append(html);
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
         var html = `<div class="no_item animate__animated animate__fadeIn">
                       <p>Existe un error en la comunicación.</p>
                </div>`;
            $("#idGallery").append(html);
    });
    scrollTop();
}