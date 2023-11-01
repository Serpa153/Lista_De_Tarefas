// ELEMENTOS
const todoForm = document.querySelector("#todo-form");
const todoInput = document.querySelector("#todo-input");
const todoList = document.querySelector("#todo-list");
const editForm = document.querySelector("#edit-form");
const editInput = document.querySelector("#edit-input");
const cancelEditBtn = document.querySelector("#cancel-edit-btn");


let oldInputValue;

//FUNCOES DA API
const getTarefas = async () => {
  var response = await fetch("http://localhost:8080/tarefas/listarTarefas", {
    method: "GET",
    headers: {
      Accept: "application/json",
    },
  });

  var tarefas = await response.json();
  return tarefas;
};

const addTarefa = async (descricao) => {
   await fetch("http://localhost:8080/tarefas/adicionarTarefa", {
    method: "POST",
    headers: {
      Accept: "application/json",
    },
    body: descricao
  });
};

const updateTarefa = async (tarefa) => {
  await fetch("http://localhost:8080/tarefas/atualizarTarefa", {
   method: "PUT",
   headers: {
     Accept: "application/json",
   },
   body: tarefa
 });
};

const deleteTarefa = async (id) => {
  await fetch("http://localhost:8080/tarefas/deletarTarefa/" + id, {
   method: "DELETE",
   headers: {
     Accept: "application/json",
   }
 });
};

//FUNCAO DE INICIALIZACAO ASYNC 


const startup = async () => {
  var tarefas = await getTarefas(); // {id, descricao, finalizado}

  //EXIBIR TAREFAS API NA TELA
  tarefas.forEach(tarefa => console.log(tarefa))
}

// FUNÇÕES FINISH/ EDIT/REMOVE

  const saveTodo = (text, done = 0, save = 1) => {
  const todo = document.createElement("div");
  todo.classList.add("todo");

  const todoTitle = document.createElement("h3");
  todoTitle.innerText = text;
  todo.appendChild(todoTitle);

  const doneBtn = document.createElement("button");
  doneBtn.classList.add("finish-todo");
  doneBtn.innerHTML = '<i class="fa-solid fa-check"></i>';
  todo.appendChild(doneBtn);

  const editBtn = document.createElement("button");
  editBtn.classList.add("edit-todo");
  editBtn.innerHTML = '<i class="fa-solid fa-pen"></i>';
  todo.appendChild(editBtn);

  const deleteBtn = document.createElement("button");
  deleteBtn.classList.add("remove-todo");
  deleteBtn.innerHTML = '<i class="fa-solid fa-xmark"></i>';
  todo.appendChild(deleteBtn);
  todoList.appendChild(todo);

  todoInput.value = "";
};

const toggleForms = () => {
  editForm.classList.toggle("hide");
  todoForm.classList.toggle("hide");
  todoList.classList.toggle("hide");
};

const updateTodo = (text) => {
  const todos = document.querySelectorAll(".todo");

  todos.forEach((todo) => {
    let todoTitle = todo.querySelector("h3");

    if (todoTitle.innerText === oldInputValue) {
      todoTitle.innerText = text;
    }
  });
};

const getSearchedTodos = (search) => {
  const todos = document.querySelectorAll(".todo");

  todos.forEach((todo) => {
    const todoTitle = todo.querySelector("h3").innerText.toLowerCase();

    todo.style.display = "flex";

    console.log(todoTitle);

    if (!todoTitle.includes(search)) {
      todo.style.display = "none";
    }
  });
};


// EVENTOS
todoForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const inputValue = todoInput.value;

  if (inputValue) {
    saveTodo(inputValue);
  }
});

document.addEventListener("click", (e) => {
  const targetEl = e.target;
  const parentEl = targetEl.closest("div");
  let todoTitle;

  if (parentEl && parentEl.querySelector("h3")) {
    todoTitle = parentEl.querySelector("h3").innerText || "";
  }

  if (targetEl.classList.contains("finish-todo")) {
    parentEl.classList.toggle("done");
  }

  if (targetEl.classList.contains("remove-todo")) {
    parentEl.remove();
  }

  if (targetEl.classList.contains("edit-todo")) {
    toggleForms();

    editInput.value = todoTitle;
    oldInputValue = todoTitle;
  }
});

/*esse cancelEdiBtn apenas na parte if... é um teste caso dê errao apague apenas a linha 163 e 169*/
if  (cancelEditBtn) {

cancelEditBtn.addEventListener("click", (e) => {
  e.preventDefault();
  toggleForms();
});
} 

editForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const editInputValue = editInput.value;

  if (editInputValue) {
    updateTodo(editInputValue);
  }

  toggleForms();
});

