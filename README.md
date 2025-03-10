<img width=100% src="https://capsule-render.vercel.app/api?type=waving&color=00BFFF&height=120&section=header"/>

[![Typing SVG](https://readme-typing-svg.herokuapp.com/?color=fff&size=35&center=true&vCenter=true&width=1000&lines=Acadêmico+Plus)](https://git.io/typing-svg)

# Sobre o projeto
Este projeto informatiza o processo de matrículas da universidade, facilitando a gestão de cursos, disciplinas, alunos e professores. Os alunos podem se inscrever em até quatro disciplinas obrigatórias e duas optativas dentro do período de matrículas, podendo também cancelar inscrições. Para que uma disciplina seja oferecida, é necessário um mínimo de três alunos matriculados, com um limite máximo de 60 por turma. O sistema também integra notificações para cobrança das disciplinas e permite que os professores consultem as listas de alunos matriculados. O acesso é protegido por login e senha, garantindo a segurança das informações.

# Diagrama de Caso de Uso
<img width="2992" alt="Image" src="https://github.com/user-attachments/assets/52d1a4e8-3aef-439e-b93d-17a22b5db1c5" />

# Histórias de Usuário
| ID  | História de Usuário |
|----|--------------------|
| UC1  | Como aluno, quero me matricular em até 4 disciplinas para o semestre. |
| UC2  | Como aluno, quero receber um boleto com o valor correspondente às disciplinas nas quais estou matriculado. |
| UC3  | Como aluno, quero cancelar minha matrícula em uma disciplina antes do fim do período de matrículas. |
| UC4  | Como aluno, quero visualizar todas as disciplinas nas quais estou matriculado. |
| UC5  | Como aluno, quero acessar o sistema com meu login e senha. |
| UC6  | Como professor, quero visualizar a lista de alunos matriculados em minhas disciplinas. |
| UC7  | Como professor, quero visualizar as disciplinas do curso. |
| UC8  | Como professor, quero acessar o sistema com meu login e senha. |
| UC9  | Como secretário acadêmico, quero definir e atualizar o currículo do semestre. |
| UC10 | Como secretário acadêmico, quero criar turmas para cada curso. |
| UC11 | Como secretário acadêmico, quero visualizar matrículas feitas pelos alunos. |
| UC12 | Como Administrador, quero definir os períodos de matrícula no sistema. |
| UC13 | Como Administrador, quero gerenciar a quantidade de alunos por disciplina. |
| UC14 | Como Administrador, quero criar usuários para que alunos e professores tenham acesso ao sistema. |
| UC15 | Como Administrador, quero acessar o sistema com meu login e senha. |

# Diagrama de Classes
<img width="2512" alt="Image" src="https://github.com/user-attachments/assets/86030f0b-c1fd-4263-8edc-b13143220989" />

# Estrutura dos Diretórios
```
src/  
│  
├── controller/  
│   └── AdminController.java  
│   └── AlunoController.java  
│   └── ProfessorController.java  
│  
├── data/  
│   └── Matriculas.txt  
│   └── Turmas.txt  
│   └── Usuarios.txt  
│  
├── model/  
│   └── Matricula.java  
│   └── Turma.java  
│   └── Usuario.java  
│  
├── view/  
│   └── AdminView.java  
│   └── AlunoView.java  
│   └── ProfessorView.java  
│  
└── Main.java
```

# Apresentação com Slides
Link do slide: https://www.canva.com/design/DAGhSVI4STk/doOaoOcbPhIT0nP091tQvA/edit?utm_content=DAGhSVI4STk&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton
  
<img width=100% src="https://capsule-render.vercel.app/api?type=waving&color=00BFFF&height=120&section=footer"/>
