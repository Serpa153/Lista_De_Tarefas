var promise = fetch('http://localhost:8080/tarefas/listarTarefas', {
    method: 'GET',
    headers: {
        'Accept': 'application/json',
    }   
})
promise.then(response => console.log(response))