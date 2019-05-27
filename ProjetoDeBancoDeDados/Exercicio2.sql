SELECT datanasc, endereco FROM funcionario WHERE pnome = 'John' AND minicial = 'B' AND unome = 'Smith';
SELECT pnome, minicial,unome,endereco FROM funcionario, departamento WHERE  dnome = 'Pesquisa' AND dnumero = dnr;
SELECT projnumero, dnome FROM projeto, departamento WHERE projlocal = 'Stafford' AND dnum = dnumero;
SELECT F1.pnome,F1.unome,F2.pnome,F2.unome FROM funcionario AS F1, funcionario AS F2 WHERE F1.cpf_supervisor = F2.cpf;
SELECT salario FROM funcionario;
SELECT DISTINCT salario FROM funcionario; 
SELECT pnome,minicial,unome FROM funcionario WHERE endereco LIKE '%Houston,TX';
SELECT pnome,minicial,unome FROM funcionario WHERE (datanasc BETWEEN '1960-01-01' AND '1969-12-31');
SELECT pnome,minicial,unome FROM funcionario WHERE (salario BETWEEN 3000 AND 4000) AND dnr = 5;
SELECT pnome,minicial,unome FROM funcionario ORDER BY pnome ASC;
SELECT pnome,minicial,unome FROM funcionario WHERE cpf_supervisor IS null;
SELECT pnome,minicial,unome FROM funcionario,dependente WHERE cpf = fcpf;
DELETE FROM dependente WHERE nome_dependente = 'Theodore';
UPDATE funcionario SET salario = salario * 1.15 WHERE dnr = 4;
UPDATE departamento SET cpf_gerente = '33344555587' WHERE dnome = 'Pesquisa';
UPDATE departamento SET data_inicio_gerente = '1988-05-22' WHERE dnome = 'Pesquisa';

