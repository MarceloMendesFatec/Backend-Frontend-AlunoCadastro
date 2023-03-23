$('#inputPhone').mask('(00)00000-0000');//mascara para telefone

var students = []; // array vazio para guardar os objetos de novos estudantes

var courses = [];


loadCourses();
loadStudents();

function loadCourses(){
  $.ajax({
    url: "http://localhost:8080/courses",
    type: "GET",
    async: "false",
    success: (response) => {
      courses = response; // Atribui a lista de cursos da resposta à variável 'courses'
      for (let selectedCourse of courses) {
        document.getElementById("inputCourse").innerHTML += `<option value= ${selectedCourse.id}>${selectedCourse.name}</option>`;
      } // Percorre a lista de cursos e adiciona cada um como uma opção no campo 'inputCourse'
    }
  })
}


function loadStudents(){
  $.getJSON("http://localhost:8080/students", response => {
    students = response
    for (let stu of students) {
      newRow(stu);
    }
  }); /*A resposta JSON é armazenada na variável "response" e, em seguida, é iterada usando um loop "for-of".
Para cada objeto "student" na resposta, 
a função "newRow" é chamada para adicionar 
uma nova linha à tabela HTML exibindo as informações do estudante.*/ 
}


function saveStudent() {
  var newStudent = {
    id: students.length + 1,
    name: document.getElementById("inputName").value,
    email: document.getElementById("inputEmail").value,
    phone: document.getElementById("inputPhone").value,
    course: document.getElementById("inputCourse").value,
    turn: document.querySelector('input[name="RadioDefault"]:checked').value,
  };  // objeto que recebe um novo aluno

  $.ajax({
    url: "http://localhost:8080/students",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(newStudent), // converte o objeto para JSON
    success: (newStudent) => {
      newRow(newStudent);// adiciona uma nova linha na tabela com o objeto 
      students.push(newStudent);// acrescenta o novo objeto ao array 
      document.getElementById("formStudent").reset();
    }
  })
  

   
}


function newRow(students) {
  var table = document.getElementById("table"); // selecionar a tabela
  let newRow = table.insertRow(); // inserir nova linha na tabela selecionada

  var idNode = document.createTextNode(students.id);
  newRow.insertCell().appendChild(idNode);// inserir o ID na tabela 

  var nameNode = document.createTextNode(students.name);
  newRow.insertCell().appendChild(nameNode);// inserir o nome na tabela 

  var emailNode = document.createTextNode(students.email);
  var cell = newRow.insertCell();// inserir o email na tabela com a condicao de esconder a celula quando menor que MD
  cell.className = "d-none d-md-table-cell";
  cell.appendChild(emailNode);
  

  var phoneNode = document.createTextNode(students.phone);
  var cell2 = newRow.insertCell();// inserir o telefone na tabela  com a condicao de esconder a celula quando menor que MD
  cell2.className = "d-none d-md-table-cell";
  cell2.appendChild(phoneNode);

  var courseNode = document.createTextNode(students.course);
  newRow.insertCell().appendChild(courseNode);// inserir o curso na tabela 

  var turnNode = document.createTextNode(students.turn);
  newRow.insertCell().appendChild(turnNode);// inserir o turno na tabela 
};