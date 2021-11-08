function addRowTable(){
    let bodyTable = document.getElementById("bodytable");

    for(let i=0;i<10;i++){
        let tr = document.createElement('tr');
        tr.id = 'row' + i;
        
    }
}
addRowTable();