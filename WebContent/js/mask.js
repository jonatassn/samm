function applyZipCodeMask(compId){
   applyMask(compId, "99999-999")
}

function applyPhoneMask(compId){
   applyMask(compId, "(99)9999-9999");
}

function applyCelMask(compId){
   applyMask(compId, "(99)99999-9999");
}

function applyPhoneAndCelMask(compId){
    compId = '#' + compId;
    jQuery(compId).mask("(99)9999-9999?9").
        focusout(function (event) {  
            var target, phone, element;  
            target = (event.currentTarget) ? event.currentTarget : event.srcElement;  
            phone = target.value.replace(/\D/g, '');
            element = $(target);  
            element.unmask();
            if(phone.length > 10) {  
                element.mask("(99)99999-999?9"); 
            } else {  
                element.mask("(99)9999-9999?9");
            }  
        });
}

function applyDotacaoMask(compId){
   applyMask(compId, "9.999.9999");
}

function applyEmpenhoMask(compId){
   applyMask(compId, "9/99999-9");
   //applyMask(compId, "999999-9/99");
}

function applySetorMask(compId){
   applyMask(compId, "99.99.99.9");
}

function applyCpfMask(compId){
   applyMask(compId, "999.999.999-99");
}

function applyMask(compId, mask){
   compId = '#' + compId;
   jQuery(compId).mask(mask);
}

