/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var data = {"total": 0, "rows": []};
var totalCost = 0;
(function($)
{

    //----Add to cart----//
   

//--------- Converts a form serializedArray to a JSON object---------------

    $.fn.toJson = function()
    {

        var jsonObj = {};
        var formArray = this.serializeArray();
        for (var i = 0; i < formArray.length; i++)
        {
            var ff = formArray[i];
            if (jsonObj[ff.name])
            {
                if (!jsonObj.push) //if not array
                {
                    jsonObj[ff.name] = [jsonObj[ff.name]];
                    jsonObj[ff.name].push(ff.value);
                }
                else //if push is available
                {
                    jsonObj[ff.name].push(ff.value);
                }
            }
            else
            {
                jsonObj[ff.name] = ff.value;
            }
        }
        return jsonObj;
    };

}(jQuery));


//------SENDING TO SERVLET---------
(function($) {
    $.fn.toAjax = function(url, success, fail, data, contentType)
    {
        //do ajax call
        var curr_contentType = contentType || "application/x-www-form-urlencoded";

        $.ajax({
            contentType: curr_contentType,
            type: "POST",
            data: data,
            url: url,
        }).done(success).fail(fail);

        //async o
        return this;
    };

}(jQuery));
$(function()
{

    $('#cartcontent').datagrid({
        singleSelect: true
    });

    $("#confirm").click(function() {
        var cart = $("#cartcontent").toJson();
        function success()
        {
            //do something
        }
        ;
        function fail()
        {
            //do something
        }
        $(this).toAjax("fbapp/User/cart", success, fail, JSON.stringify(cart), "application/json");// send to servlet



        alert("Order Placed");
////            $("#ca").find("tr:gt(0)").remove();
//            var name = Null;
//            var price = Null;
//            var quantity = Null;
//            data.rows.push({
//                name: name,
//                quantity: quantity,
//                price: price,
//                remove: Null
//            });
    });
    $('.cart').droppable({
        onDragEnter: function(e, source) {
            $(source).draggable('options').cursor = 'auto';
        },
        onDragLeave: function(e, source) {
            $(source).draggable('options').cursor = 'not-allowed';
        },
        onDrop: function(e, source) {
            var name = $(source).find('p:eq(0)').html();
            var price = $(source).find('p:eq(1)').html();
            addProduct(name, parseInt(price));
        }
    });
}
);
function addProduct(name, price) {
    function add() {
        for (var i = 0; i < data.total; i++) {
            var row = data.rows[i];
            if (row.name === name) {
                row.quantity += 1;
                return;
            }
        }
        data.total += 1;
        data.rows.push({
            name: name,
            quantity: 1,
            price: price,
            remove: '<a href="#" class="remove" onclick="removeProduct(this, event)">Remove</a>'
        });
    }
    add();
    totalCost += price;
    $('#cartcontent').datagrid('loadData', data);
    $('div.cart .total').html('Total: $' + totalCost);

}
function removeProduct(el, event) {
    var tr = $(el).closest('tr');
    var name = tr.find('td[field=name]').text();
    var price = tr.find('td[field=price]').text();
    var quantity = tr.find('td[field=quantity]').text();
    for (var i = 0; i < data.total; i++) {
        var row = data.rows[i];
        if (row.name === name) {
            data.rows.splice(i, 1);
            data.total--;
            break;
        }
    }
    totalCost -= price * quantity;
    $('#cartcontent').datagrid('loadData', data);
    $('div.cart .total').html('Total: $' + totalCost);
}


