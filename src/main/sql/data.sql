/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  sv7
 * Created: 8 May 2024
 */

-- Inserir um profissional de exemplo
INSERT INTO Professional (registration_type, name, cpf, email, password, birth_date, phone_number) 
VALUES ('REGISTRO', 'João Silva', '12345678900', 'joao@example.com', 'senha123', '1990-01-01', '123456789');

-- Inserir títulos para o profissional de exemplo
INSERT INTO ProfessionalTitle (professional_id, title) 
VALUES (1, 'Engenheiro Civil'), (1, 'Engenheiro Eletricista');
