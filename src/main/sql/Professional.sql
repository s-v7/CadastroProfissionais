/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  sv7
 * Created: 8 May 2024
 */
CREATE DATABASE cadastro_profissionais IF NOT EXISTS;

CREATE TABLE Professional (
    id SERIAL PRIMARY KEY,
    registration_type VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    phone_number VARCHAR(20)
);