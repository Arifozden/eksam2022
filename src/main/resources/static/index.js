function validerNavn(navn){
    let regex=/^[A-ZÆØÅa-zæøå. -]{2,50}$/;
    if(regex.test(navn)){
        $("#feilNavn").html("");
        return true;
    }else {
        $("#feilNavn").html("Bruk kun store og små bokstaver, og ., - og\n" +
            "mellomrom");
        return false;
    }
}

function validerMobil(mobil){
    let regex=/^[0-9]{8}$/;
    if(regex.test(mobil)){
        $("#feilMobil").html("");
        return true;
    }else {
        $("#feilMobil").html("Må være åtte sifre")
        return false;
    }
}

function validerEpost(epost){
    let regex=/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
    if(regex.test(epost)){
        $("#feilepost").html("");
        return true;
    }else {
        $("#feilepost").html("Skriv inn en gyldig e-postadresse");
        return false;
    }
}

function validerBord(bord){
    if(bord){
        $("#feilBord").html("");
        return true;
    }else {
        $("#feilBord").html("Fyll ut feltet");
        return true;
    }
}

function validerVarer(varer){
    if(varer){
        $("#feilVarer").html("");
        return true;
    }else {
        $("#feilVarer").html("Fyll ut feltet");
        return false;
    }
}

function validerBestilling(bestilling){
    navnOK=validerNavn(bestilling.navn);
    mobilOK=validerMobil(bestilling.mobil);
    epostOK=validerEpost(bestilling.epost);
    bordOK=validerBord(bestilling.bord);
    varerOK=validerVarer(bestilling.varer);

    if(navnOK&&mobilOK&&epostOK&&bordOK&&varerOK){
        return true;
    }else {
        return false;
    }
}

function sendBestilling(){
    bestilling={
        "navn":$("#navn").val(),
        "mobil":$("#mobil").val(),
        "epost":$("#epost").val(),
        "bord":$("#bord").val(),
        "varer":$("#varer").val()
    };
    if(validerBestilling(bestilling)){
        $.post("bestilling",bestilling,function (){
            $("#melding").html("Bestilling sendt inn");

            $("#navn").val("");
            $("#mobil").val("");
            $("#epost").val("");
            $("#bord").val("");
            $("#varer").val("");

        }).fail(function (jqXHR){
            const json=$.parseJSON(jqXHR.responseText);
            $("#melding").html(json.message);
        });
    }else {
        $("#melding").html("Fyll ut alle felter og rett alle feil i\n" +
            "skjemaet før innsending");
    }
}

